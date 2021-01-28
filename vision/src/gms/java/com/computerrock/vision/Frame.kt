package com.computerrock.vision

import android.graphics.Bitmap
import java.nio.ByteBuffer

class Frame internal constructor(val gmsFrame: com.google.android.gms.vision.Frame) {

    val metadata: Metadata
        get() = Metadata(gmsFrame.metadata)

    val grayscaleImageData: ByteBuffer?
        get() = gmsFrame.grayscaleImageData

    val bitmap: Bitmap?
        get() = gmsFrame.bitmap


    class Metadata(val gmsMetadata: com.google.android.gms.vision.Frame.Metadata) {
        val width: Int
            get() = gmsMetadata.width

        val height: Int
            get() = gmsMetadata.height

        val id: Int
            get() = gmsMetadata.id

        val timestampMillis: Long
            get() = gmsMetadata.timestampMillis

        val format: Int
            get() = gmsMetadata.format

        val rotation: Int
            get() = gmsMetadata.rotation
    }

    class Builder {

        private val gmsBuilder = com.google.android.gms.vision.Frame.Builder()

        fun setBitmap(bitmap: Bitmap): Builder {
            gmsBuilder.setBitmap(bitmap)
            return this
        }

        fun setImageData(
            var1: ByteBuffer,
            var2: Int,
            var3: Int,
            var4: Int
        ): Builder {
            gmsBuilder.setImageData(var1, var2,var3, var4)
            return this
        }

        fun setId(
            id: Int
        ): Builder {
            gmsBuilder.setId(id)
            return this
        }

        fun setRotation(
            rotation: Int
        ): Builder {
            gmsBuilder.setRotation(rotation)
            return this
        }

        fun setTimestampMillis(
            timestamp: Long
        ): Builder {
            gmsBuilder.setTimestampMillis(timestamp)
            return this
        }

        fun build(): Frame {
            return Frame(gmsBuilder.build())
        }
    }
}