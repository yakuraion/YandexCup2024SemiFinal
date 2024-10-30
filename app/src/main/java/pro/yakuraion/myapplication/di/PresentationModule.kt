package pro.yakuraion.myapplication.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.myapplication.presentation.screens.drawing.DrawingViewModel

val presentationModule = module {
    viewModelOf(::DrawingViewModel)
}
