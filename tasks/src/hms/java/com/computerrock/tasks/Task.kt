package com.computerrock.tasks

import android.app.Activity
import java.lang.Exception

class Task<TResult> constructor(private val hmsTask: com.huawei.hmf.tasks.Task<*>, val convert: (result: Any?) -> TResult? = { it as? TResult }): ITask<TResult> {
    override fun isComplete() = hmsTask.isComplete
    override fun isSuccessful() = hmsTask.isSuccessful
    override fun isCanceled() = hmsTask.isCanceled
    override fun getResult() = convert(hmsTask.result)

    override fun <X : Throwable?> getResult(aClass: Class<X>): TResult? {
        return convert(hmsTask.result)
    }

    override fun getException(): Exception? = hmsTask.exception

    override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in TResult>): Task<TResult> {
        hmsTask.addOnSuccessListener {
            onSuccessListener.onSuccess(convert(it))
        }
        return this
    }

    override fun addOnSuccessListener(
        activity: Activity,
        onSuccessListener: OnSuccessListener<in TResult>
    ): Task<TResult> {
        hmsTask.addOnSuccessListener(activity) {
            onSuccessListener.onSuccess(convert(it))
        }
        return this
    }

    override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<TResult> {
        hmsTask.addOnFailureListener {
            onFailureListener.onFailure(it)
        }
        return this
    }

    override fun addOnFailureListener(
        activity: Activity,
        onFailureListener: OnFailureListener
    ): Task<TResult> {
        hmsTask.addOnFailureListener(activity) {
            onFailureListener.onFailure(it)
        }
        return this
    }

    override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<TResult>): Task<TResult> {
        hmsTask.addOnCompleteListener {
            onCompleteListener.onComplete(this)
        }
        return this
    }

    override fun addOnCompleteListener(
        activity: Activity,
        onCompleteListener: OnCompleteListener<TResult>
    ): Task<TResult> {
        hmsTask.addOnCompleteListener(activity) {
            onCompleteListener.onComplete(this)
        }
        return this
    }

    override fun addOnCanceledListener(onCanceledListener: OnCanceledListener): Task<TResult> {
        hmsTask.addOnCanceledListener {
            onCanceledListener.onCanceled()
        }
        return this
    }

    override fun addOnCanceledListener(
        activity: Activity,
        onCanceledListener: OnCanceledListener
    ): Task<TResult> {
        hmsTask.addOnCanceledListener(activity) {
            onCanceledListener.onCanceled()
        }
        return this
    }
}
