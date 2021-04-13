package com.computerrock.pushServices

import android.os.Parcelable

abstract class RemoteMassage {
    abstract fun getData(): Map<String, String>

    abstract fun <T:Parcelable> getMessageOriginal(): T
}