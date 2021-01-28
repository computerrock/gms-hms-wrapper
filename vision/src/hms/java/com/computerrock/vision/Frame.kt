package com.computerrock.vision

import android.graphics.Bitmap
import java.nio.ByteBuffer

class Frame internal constructor(val mlFrame: com.huawei.hms.mlsdk.common.MLFrame) {

    val metadata: Metadata
        get() = Metadata(mlFrame.acquireProperty())

    val grayscaleImageData: ByteBuffer?
        get() = mlFrame.acquireGrayByteBuffer()

    val bitmap: Bitmap?
        get() = mlFrame.readBitmap()


    class Metadata(val property: com.huawei.hms.mlsdk.common.MLFrame.Property) {
        val width: Int
            get() = property.width

        val height: Int
            get() = property.height

        val id: Int
            get() = property.itemIdentity

        val timestampMillis: Long
            get() = property.timestamp

        val format: Int
            get() = property.formatType

        val rotation: Int
            get() = property.quadrant
    }

    class Builder {

        private val mlFrameCreator = com.huawei.hms.mlsdk.common.MLFrame.Creator()

        fun setBitmap(bitmap: Bitmap): Builder {
            mlFrameCreator.setBitmap(bitmap)
            return this
        }

        fun setImageData(
            var1: ByteBuffer,
            var2: Int,
            var3: Int,
            var4: Int
        ): Builder {
            mlFrameCreator.writeByteBufferData(var1, var2,var3, var4)
            return this
        }

        fun setId(
            id: Int
        ): Builder {
            mlFrameCreator.setItemIdentity(id)
            return this
        }

        fun setRotation(
            rotation: Int
        ): Builder {
            mlFrameCreator.setQuadrant(rotation)
            return this
        }

        fun setTimestampMillis(
            timestamp: Long
        ): Builder {
            mlFrameCreator.setTimestamp(timestamp)
            return this
        }

        fun build(): Frame {
            return Frame(mlFrameCreator.create())
        }
    }
}