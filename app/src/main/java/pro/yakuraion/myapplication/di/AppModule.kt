package pro.yakuraion.myapplication.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pro.yakuraion.myapplication.core.Dispatchers

val appModule = module {
    installDispatchers()
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}

private fun Module.installDispatchers() {
    single { Dispatchers.original() }
}
