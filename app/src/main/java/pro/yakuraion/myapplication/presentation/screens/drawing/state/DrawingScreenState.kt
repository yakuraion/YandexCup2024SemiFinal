package pro.yakuraion.myapplication.presentation.screens.drawing.state

import kotlinx.coroutines.flow.MutableStateFlow
import pro.yakuraion.myapplication.presentation.painting.models.Frame

class DrawingScreenState {

    val frame: MutableStateFlow<Frame> = MutableStateFlow(Frame())
}
