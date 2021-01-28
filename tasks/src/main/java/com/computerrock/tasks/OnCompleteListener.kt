package com.computerrock.tasks

fun interface OnCompleteListener<TResult> {
    fun onComplete(task: Task<TResult>)
}