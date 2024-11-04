package pro.yakuraion.myapplication.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pro.yakuraion.myapplication.presentation.screens.drawing.DrawingViewModel

val presentationModule = module {
    viewModel { DrawingViewModel(get()) }
}
