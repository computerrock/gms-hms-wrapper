package com.computerrock.tasks

import android.app.Activity
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import java.lang.Exception
import java.util.concurrent.Executor

class Task<TResult> constructor(private val gmsTask: com.google.android.gms.tasks.Task<*>, val convert: (result: Any) -> TResult = { it as TResult }): ITask<TResult> {
    override fun isComplete() = gmsTask.isComplete
    override fun isSuccessful() = gmsTask.isSuccessful
    override fun isCanceled() = gmsTask.isCanceled
    override fun getResult() = convert(gmsTask.result)

    override fun <X : Throwable?> getResult(aClass: Class<X>): TResult? {
        return convert(gmsTask.getResult(aClass))
    }

    override fun getException(): Exception? = gmsTask.exception

    override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in TResult>): Task<TResult> {
        gmsTask.addOnSuccessListener {
            onSuccessListener.onSuccess(convert(it))
        }
        return this
    }

    override fun addOnSuccessListener(
        activity: Activity,
        onSuccessListener: OnSuccessListener<in TResult>
    ): Task<TResult> {
        gmsTask.addOnSuccessListener(activity) {
            onSuccessListener.onSuccess(convert(it))
        }
        return this
    }

    override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<TResult> {
        gmsTask.addOnFailureListener {
            onFailureListener.onFailure(it)
        }
        return this
    }

    override fun addOnFailureListener(
        activity: Activity,
        onFailureListener: OnFailureListener
    ): Task<TResult> {
        gmsTask.addOnFailureListener(activity) {
            onFailureListener.onFailure(it)
        }
        return this
    }

    override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<TResult>): Task<TResult> {
        gmsTask.addOnCompleteListener {
            onCompleteListener.onComplete(this)
        }
        return this
    }

    override fun addOnCompleteListener(
        activity: Activity,
        onCompleteListener: OnCompleteListener<TResult>
    ): Task<TResult> {
        gmsTask.addOnCompleteListener(activity) {
            onCompleteListener.onComplete(this)
        }
        return this
    }

    override fun addOnCanceledListener(onCanceledListener: OnCanceledListener): Task<TResult> {
        gmsTask.addOnCanceledListener {
            onCanceledListener.onCanceled()
        }
        return this
    }

    override fun addOnCanceledListener(
        activity: Activity,
        onCanceledListener: OnCanceledListener
    ): Task<TResult> {
        gmsTask.addOnCanceledListener(activity) {
            onCanceledListener.onCanceled()
        }
        return this
    }
}