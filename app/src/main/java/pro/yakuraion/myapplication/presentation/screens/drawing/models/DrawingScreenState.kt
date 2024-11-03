package pro.yakuraion.myapplication.presentation.screens.drawing.models

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
        val isPenActive: StateFlow<Boolean>,
        val isEraserActive: StateFlow<Boolean>,
        val isInstrumentsActive: StateFlow<Boolean>,
    ) : DrawingScreenState()

    data class Preview(
        val frameIndex: Long,
        val staticFrame: StaticFrame,
    ) : DrawingScreenState()
}
