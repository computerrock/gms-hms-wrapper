package com.computerrock.vision


open class Tracker<T> {
    open fun onNewItem(id: Int, item: T) {}

    open fun onUpdate(detectionResults: Detector.Detections<T>?, item: T) {}

    open fun onMissing(detectionResults: Detector.Detections<T>?) {}

    open fun onDone() {}
}