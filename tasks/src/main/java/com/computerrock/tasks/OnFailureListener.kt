package com.computerrock.tasks

fun interface OnFailureListener {
    fun onFailure(exception: Exception)
}