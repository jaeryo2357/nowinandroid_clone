package com.example.nowinandroid_clone.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

interface NiaDispatchers {
    val IO: CoroutineDispatcher
    val Default: CoroutineDispatcher
    val Main: MainCoroutineDispatcher
    val Unconfined: CoroutineDispatcher
}

class DefaultNiaDispatchers @Inject constructor() :
        NiaDispatchers {
    override val IO: CoroutineDispatcher =
        Dispatchers.IO
    override val Default: CoroutineDispatcher =
        Dispatchers.Default
    override val Main: MainCoroutineDispatcher =
        Dispatchers.Main
    override val Unconfined: CoroutineDispatcher =
        Dispatchers.Unconfined
}