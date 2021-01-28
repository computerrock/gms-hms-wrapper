package com.computerrock.vision

import android.util.SparseArray

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

    private val gmsDetector:  com.google.android.gms.vision.Detector<T> = object :  com.google.android.gms.vision.Detector<T>() {
        override fun detect(frame: com.google.android.gms.vision.Frame): SparseArray<T> {
            return this@Detector.detect(Frame(frame))
        }
    }

    override fun release() {
        gmsDetector.release()
    }

    override fun setFocus(focus: Int) {
        gmsDetector.setFocus(focus)
    }

    override fun receiveFrame(frame: Frame) {
        gmsDetector.receiveFrame(frame.gmsFrame)
    }

    override fun setProcessor(processor: Processor<T>) {
        gmsDetector.setProcessor(object :  com.google.android.gms.vision.Detector.Processor<T> {
            override fun release() {
                processor.release()
            }

            override fun receiveDetections(detectionResults:  com.google.android.gms.vision.Detector.Detections<T>) {
                processor.receiveDetections(
                    Detections(
                        detectionResults.detectedItems,
                        Frame.Metadata(detectionResults.frameMetadata),
                        detectionResults.detectorIsOperational()
                    )
                )
            }

        })
    }

    override fun isOperational(): Boolean {
        return gmsDetector.isOperational
    }
}