package pro.yakuraion.myapplication.presentation.screens.drawing.models

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
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

    data class AddFramesRange(
        val size: Size,
        val penDrawingInput: DrawingInput.Pen,
    ) : DrawingScreenState()

    data class FramesOverview(
        val originalFrameSize: Size,
        val selectedFrameIndex: Long,
        val lastFrameIndex: Long,
        val getFramePreviewAction: (index: Long) -> ImageBitmap,
    ) : DrawingScreenState()

    data class Preview(
        val frameIndex: Long,
        val staticFrame: StaticFrame,
    ) : DrawingScreenState()
}
