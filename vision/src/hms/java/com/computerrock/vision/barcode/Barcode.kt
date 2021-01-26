package com.computerrock.vision.barcode

import android.graphics.Rect

class Barcode internal constructor(private val hmsScan: com.huawei.hms.ml.scan.HmsScan) {
    val boundingBox: Rect
        get() = hmsScan.borderRect
    val rawValue: String
        get() = hmsScan.originalValue
    val rawBytes: ByteArray
        get() = hmsScan.originValueByte
}