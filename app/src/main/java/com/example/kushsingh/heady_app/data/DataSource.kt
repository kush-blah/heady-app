package com.example.kushsingh.heady_app.data.network

import com.example.kushsingh.heady_app.base.ErrorWrapper

interface DataSource {

    interface OnResponseCallback<T> {
        fun onSuccess(obj: T)
        fun onError(error: ErrorWrapper)
    }

}