package com.computerrock.tasks

fun interface OnSuccessListener<TResult> {
    fun onSuccess(result: TResult?)
}