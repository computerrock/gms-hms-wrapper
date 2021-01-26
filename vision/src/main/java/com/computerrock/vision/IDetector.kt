package com.computerrock.vision

import android.util.SparseArray

internal interface IDetector<T> {
    fun release()

    fun setFocus(focus: Int)

    fun detect(frame: Frame): SparseArray<T>

    fun receiveFrame(frame: Frame)

    fun setProcessor(processor: Detector.Processor<T>)

    fun isOperational(): Boolean
}