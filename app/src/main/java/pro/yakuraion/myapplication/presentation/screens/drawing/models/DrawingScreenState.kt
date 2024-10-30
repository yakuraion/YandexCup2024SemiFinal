package pro.yakuraion.myapplication.presentation.screens.drawing.models

import androidx.compose.ui.geometry.Size
import pro.yakuraion.myapplication.presentation.painting.models.Document
import pro.yakuraion.myapplication.presentation.painting.models.Frame

sealed class DrawingScreenState {

    data class Drawing(
        val previousFrame: Frame?,
        val activeFrame: ActiveFrame,
    ) : DrawingScreenState() {

        val deleteFrameAvailable: Boolean = previousFrame != null

        val goToPreviousActionAvailable: Boolean = activeFrame.appliedActions > 0

        val goToNextActionAvailable: Boolean = activeFrame.appliedActions < activeFrame.frame.actions.count()
    }

    data class Preview(
        val document: Document,
        val size: Size,
    ) : DrawingScreenState()
}
