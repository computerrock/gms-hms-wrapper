package com.computerrock.tasks

interface OnCompleteListener<TResult> {
    fun onComplete(task: Task<TResult>)
}