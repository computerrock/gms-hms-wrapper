package com.computerrock.vision

import android.util.SparseArray

inline fun <T, R> SparseArray<out T>.map(transform: (T) -> R): SparseArray<R> {
    val out = SparseArray<R>()
    for(i in 0 until this.size()) {
        val key = keyAt(i)
        out.put(key, transform(get(key)))
    }
    return out
}
