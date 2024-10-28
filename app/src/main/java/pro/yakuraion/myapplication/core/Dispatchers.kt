package pro.yakuraion.myapplication.core

import kotlinx.coroutines.CoroutineDispatcher

class Dispatchers(
    val ioDispatcher: CoroutineDispatcher,
    val computeDispatcher: CoroutineDispatcher,
    val mainDispatcher: CoroutineDispatcher,
    val unconfinedDispatcher: CoroutineDispatcher,
) {

    companion object {

        fun original(): Dispatchers = Dispatchers(
            ioDispatcher = kotlinx.coroutines.Dispatchers.IO,
            computeDispatcher = kotlinx.coroutines.Dispatchers.Default,
            mainDispatcher = kotlinx.coroutines.Dispatchers.Main,
            unconfinedDispatcher = kotlinx.coroutines.Dispatchers.Unconfined,
        )
    }
}
