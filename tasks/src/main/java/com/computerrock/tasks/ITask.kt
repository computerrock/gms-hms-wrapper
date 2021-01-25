package com.computerrock.tasks

import android.app.Activity
import java.lang.Exception

interface ITask<TResult> {

    fun isComplete(): Boolean

    fun isSuccessful(): Boolean

    fun isCanceled(): Boolean

    fun getResult(): TResult?

    fun <X : Throwable?> getResult(aClass: Class<X>): TResult?

    fun getException(): Exception?

    fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in TResult>): Task<TResult>

    fun addOnSuccessListener(
        activity: Activity,
        onSuccessListener: OnSuccessListener<in TResult>
    ): Task<TResult>

    fun addOnFailureListener(onFailureListener: OnFailureListener): Task<TResult>

    fun addOnFailureListener(
        activity: Activity,
        onFailureListener: OnFailureListener
    ): Task<TResult>

    fun addOnCompleteListener(onCompleteListener: OnCompleteListener<TResult>): Task<TResult>

    fun addOnCompleteListener(
        activity: Activity,
        onCompleteListener: OnCompleteListener<TResult>
    ): Task<TResult>

    fun addOnCanceledListener(onCanceledListener: OnCanceledListener): Task<TResult>

    fun addOnCanceledListener(
        activity: Activity,
        onCanceledListener: OnCanceledListener
    ): Task<TResult>
}