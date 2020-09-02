package com.diegobckn.fluxit.api

sealed class Request<out T> {
    class Loading<out T>: Request<T>()
    data class Success<out T>(val data:T): Request<T>()
    data class Failure<out T>(val exception: Exception):Request<T>()
}