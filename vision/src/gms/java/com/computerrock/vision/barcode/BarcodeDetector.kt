package com.computerrock.vision.barcode

import android.content.Context
import android.util.SparseArray
import com.computerrock.vision.Detector
import com.computerrock.vision.Frame
import com.computerrock.vision.map

class BarcodeDetector internal constructor(private val gmsBarcodeDetector: com.google.android.gms.vision.barcode.BarcodeDetector) : Detector<Barcode>() {
    override fun detect(frame: Frame): SparseArray<Barcode> {
        return gmsBarcodeDetector.detect(frame.gmsFrame).map { Barcode(it) }
    }

    override fun release() {
        gmsBarcodeDetector.release()
    }

    override fun setFocus(focus: Int) {
        gmsBarcodeDetector.setFocus(focus)
    }

    override fun receiveFrame(frame: Frame) {
        gmsBarcodeDetector.receiveFrame(frame.gmsFrame)
    }

    override fun setProcessor(processor: Detector.Processor<Barcode>) {
        gmsBarcodeDetector.setProcessor(object :
            com.google.android.gms.vision.Detector.Processor<com.google.android.gms.vision.barcode.Barcode> {
            override fun release() {
                processor.release()
            }

            override fun receiveDetections(detectionResults: com.google.android.gms.vision.Detector.Detections<com.google.android.gms.vision.barcode.Barcode>) {
                processor.receiveDetections(
                    Detector.Detections(
                        detectionResults.detectedItems.map { Barcode(it) },
                        Frame.Metadata(detectionResults.frameMetadata),
                        detectionResults.detectorIsOperational()
                    )
                )
            }

        })
    }

    override fun isOperational(): Boolean {
        return gmsBarcodeDetector.isOperational
    }

    class Builder(context: Context) {

        private val gmsBuilder: com.google.android.gms.vision.barcode.BarcodeDetector.Builder = com.google.android.gms.vision.barcode.BarcodeDetector.Builder(context)

        fun setBarcodeFormats(formats: Int): Builder {
            gmsBuilder.setBarcodeFormats(formats)
            return this
        }

        fun build(): BarcodeDetector {
            return BarcodeDetector(gmsBuilder.build())
        }
    }
}