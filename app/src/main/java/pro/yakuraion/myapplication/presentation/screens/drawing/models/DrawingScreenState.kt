package pro.yakuraion.myapplication.presentation.screens.drawing.models

import kotlinx.coroutines.flow.StateFlow
import pro.yakuraion.myapplication.core.mapState
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

sealed class DrawingScreenState {

    data class Drawing(
        val activeFrame: StateFlow<ActiveFrame>,
        val previousFrame: StateFlow<StaticFrame?>,
        val canGoBack: StateFlow<Boolean>,
        val canGoForward: StateFlow<Boolean>,
        val isPenEnabled: StateFlow<Boolean>,
    ) : DrawingScreenState() {

        val deleteFrameAvailable: StateFlow<Boolean> = previousFrame.mapState { it != null }
    }

    data class Preview(
        val frameIndex: Long,
        val staticFrame: StaticFrame,
    ) : DrawingScreenState()
}
