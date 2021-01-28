package com.computerrock.vision

import android.util.SparseArray
import com.huawei.hms.mlsdk.common.MLAnalyzer
import com.huawei.hms.mlsdk.common.MLFrame

abstract class Detector<T>: IDetector<T>  {

    interface Processor<T> {
        fun release()
        fun receiveDetections(detectionResults: Detections<T>)
    }

    class Detections<T>(
        val detectedItems: SparseArray<T>,
        val frameMetadata: Frame.Metadata,
        val detectorIsOperational: Boolean
    )

    private val mlAnalyzer: MLAnalyzer<T> = object : MLAnalyzer<T>() {
        override fun analyseFrame(frame: MLFrame): SparseArray<T> {
            return detect(Frame(frame))
        }
    }

    override fun release() {
        mlAnalyzer.destroy()
    }

    override fun setFocus(focus: Int) {
        mlAnalyzer.traceItem(focus)
    }

    override fun receiveFrame(frame: Frame) {
        mlAnalyzer.obtainPicture(frame.mlFrame)
    }

    override fun setProcessor(processor: Processor<T>) {
        mlAnalyzer.setTransactor(object : MLAnalyzer.MLTransactor<T> {
            override fun destroy() {
                processor.release()
            }

            override fun transactResult(result: MLAnalyzer.Result<T>) {
                processor.receiveDetections(
                    Detections(
                        result.analyseList,
                        Frame.Metadata(result.frameProperty),
                        result.isAnalyzerAvaliable
                    )
                )
            }

        })
    }

    override fun isOperational(): Boolean {
        return mlAnalyzer.isAvailable
    }
}