package com.computerrock.vision

class MultiProcessor<T> internal constructor(
    private val gmsMultiProcessor: com.google.android.gms.vision.MultiProcessor<T>
) : Detector.Processor<T>{

    override fun release() {
        gmsMultiProcessor.release()
    }

    override fun receiveDetections(detectionResults: Detector.Detections<T>) {
        gmsMultiProcessor.receiveDetections(detectionResults.toGmsDetectionResults())
    }

    private fun Detector.Detections<T>.toGmsDetectionResults(): com.google.android.gms.vision.Detector.Detections<T> {
        return com.google.android.gms.vision.Detector.Detections<T>(
            detectedItems.map { it },
            frameMetadata.gmsMetadata,
            detectorIsOperational
        )
    }

    interface Factory<T> {
        fun create(item: T): Tracker<T>
    }

    class Builder<T>(factory: Factory<T>) {
        private val gmsBuilder = com.google.android.gms.vision.MultiProcessor.Builder<T> { item ->
            com.google.android.gms.vision.MultiProcessor.Factory<T> {
                val tracker = factory.create(it)
                object : com.google.android.gms.vision.Tracker<T>() {
                override fun onNewItem(id: Int, item: T) {
                    tracker.onNewItem(id, item)
                }

                override fun onUpdate(
                    detectionResults: com.google.android.gms.vision.Detector.Detections<T>,
                    item: T
                ) {
                    tracker.onUpdate(Detector.Detections(
                        detectionResults.detectedItems.map { it },
                        Frame.Metadata(detectionResults.frameMetadata),
                        detectionResults.detectorIsOperational()
                    ), item)
                }

                override fun onMissing(detectionResults: com.google.android.gms.vision.Detector.Detections<T>) {
                    tracker.onMissing(Detector.Detections(
                        detectionResults.detectedItems.map { it },
                        Frame.Metadata(detectionResults.frameMetadata),
                        detectionResults.detectorIsOperational()
                    ))
                }

                override fun onDone() {
                    tracker.onDone()
                }
            } }.create(item)
        }

        fun setMaxGapFrames(maxGapFrames: Int): Builder<T> {
            gmsBuilder.setMaxGapFrames(maxGapFrames)
            return this
        }

        fun build(): MultiProcessor<T> {
            return MultiProcessor(gmsBuilder.build())
        }

    }
}
