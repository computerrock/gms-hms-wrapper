package com.computerrock.vision.barcode

import android.content.Context
import android.util.SparseArray
import com.computerrock.vision.Detector
import com.computerrock.vision.Frame
import com.computerrock.vision.map
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzer
import com.huawei.hms.mlsdk.common.MLAnalyzer

class BarcodeDetector internal constructor(private val hmsScanAnalyzer: HmsScanAnalyzer) : Detector<Barcode>() {
    override fun detect(frame: Frame): SparseArray<Barcode> {
        return hmsScanAnalyzer.analyseFrame(frame.mlFrame).map { Barcode(it) }
    }

    override fun release() {
        hmsScanAnalyzer.destory()
    }

    override fun setFocus(focus: Int) {
        hmsScanAnalyzer.traceItem(focus)
    }

    override fun receiveFrame(frame: Frame) {
        hmsScanAnalyzer.obtainPicture(frame.mlFrame)
    }

    override fun setProcessor(processor: Processor<Barcode>) {
        hmsScanAnalyzer.setTransactor(object : MLAnalyzer.MLTransactor<HmsScan> {
            override fun destroy() {
                processor.release()
            }

            override fun transactResult(result: MLAnalyzer.Result<HmsScan>) {
                processor.receiveDetections(
                    Detector.Detections(
                        result.analyseList.map { Barcode(it) },
                        Frame.Metadata(result.frameProperty),
                        result.isAnalyzerAvaliable
                    )
                )
            }

        })
    }

    override fun isOperational(): Boolean {
        return hmsScanAnalyzer.isAvailable
    }

    class Builder(context: Context) {

        private val hmsCreator: HmsScanAnalyzer.Creator = HmsScanAnalyzer.Creator(context)

        fun setBarcodeFormats(formats: Int): Builder {
            hmsCreator.setHmsScanTypes(formats)
            return this
        }

        fun build(): BarcodeDetector {
            return BarcodeDetector(hmsCreator.create())
        }
    }
}