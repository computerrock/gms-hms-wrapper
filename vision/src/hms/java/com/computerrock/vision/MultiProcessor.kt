package com.computerrock.vision

import com.huawei.hms.mlsdk.common.MLAnalyzer
import com.huawei.hms.mlsdk.common.MLCompositeTransactor
import com.huawei.hms.mlsdk.common.MLResultTrailer

class MultiProcessor<T> internal constructor(
    private val mlCompositeTransactor: MLCompositeTransactor<T>
) : Detector.Processor<T>{

    override fun release() {
        mlCompositeTransactor.destroy()
    }

    override fun receiveDetections(detectionResults: Detector.Detections<T>) {
        mlCompositeTransactor.transactResult(detectionResults.toGmsDetectionResults())
    }

    private fun Detector.Detections<T>.toGmsDetectionResults(): MLAnalyzer.Result<T> {
        return MLAnalyzer.Result<T>(
            detectedItems.map { it },
            frameMetadata.property,
            detectorIsOperational
        )
    }

    interface Factory<T> {
        fun create(item: T): Tracker<T>
    }

    class Builder<T>(factory: Factory<T>) {
        private val mlCompositeTransactorCreator = MLCompositeTransactor.Creator<T> { item ->
            MLCompositeTransactor.TrailerFactory<T> {
                val tracker = factory.create(it)
                object : MLResultTrailer<T>() {

                    override fun objectCreateCallback(id: Int, item: T) {
                        tracker.onNewItem(id, item)
                    }

                    override fun objectUpdateCallback(detectionResults: MLAnalyzer.Result<T>, p1: T) {
                        tracker.onUpdate(Detector.Detections(
                            detectionResults.analyseList,
                            Frame.Metadata(detectionResults.frameProperty),
                            detectionResults.isAnalyzerAvaliable
                        ), item)
                    }

                    override fun lostCallback(detectionResults: MLAnalyzer.Result<T>) {
                        tracker.onMissing(Detector.Detections(
                            detectionResults.analyseList,
                            Frame.Metadata(detectionResults.frameProperty),
                            detectionResults.isAnalyzerAvaliable
                        ))
                    }

                    override fun completeCallback() {
                        tracker.onDone()
                    }

            } }.create(item)
        }

        fun setMaxGapFrames(maxGapFrames: Int): Builder<T> {
            mlCompositeTransactorCreator.setMaxFrameLostCount(maxGapFrames)
            return this
        }

        fun build(): MultiProcessor<T> {
            return MultiProcessor(mlCompositeTransactorCreator.create())
        }

    }
}
