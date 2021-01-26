package com.computerrock.vision.barcode

import android.graphics.Rect
import com.google.android.gms.vision.barcode.Barcode

class Barcode internal constructor(private val gmsBarcode: Barcode) {
    val boundingBox: Rect
        get() = gmsBarcode.boundingBox
    val rawValue: String
        get() = gmsBarcode.rawValue
    val rawBytes: ByteArray
        get() = gmsBarcode.rawBytes
}