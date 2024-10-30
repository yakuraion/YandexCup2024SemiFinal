package pro.yakuraion.myapplication.presentation.screens.drawing.state

import pro.yakuraion.myapplication.presentation.painting.models.Frame

data class DrawingScreenState(
    val previousFrame: Frame?,
    val activeFrame: Frame,
)
