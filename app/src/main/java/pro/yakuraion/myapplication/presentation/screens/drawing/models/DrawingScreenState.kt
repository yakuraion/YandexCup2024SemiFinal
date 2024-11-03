package pro.yakuraion.myapplication.presentation.screens.drawing.models

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

sealed class DrawingScreenState {

    data class Working(
        val activeFrame: StateFlow<ActiveFrame>,
        val previousFrame: StateFlow<StaticFrame?>,
        val canGoBack: StateFlow<Boolean>,
        val canGoForward: StateFlow<Boolean>,
        val canDeleteFrame: StateFlow<Boolean>,
        val drawingInput: StateFlow<DrawingInput>,
        val lastStrokeWidth: StateFlow<Float>,
        val lastColor: StateFlow<Color>,
    ) : DrawingScreenState()

    data class Preview(
        val frameIndex: Long,
        val staticFrame: StaticFrame,
    ) : DrawingScreenState()
}
