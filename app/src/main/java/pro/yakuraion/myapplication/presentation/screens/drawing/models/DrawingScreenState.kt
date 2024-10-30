package pro.yakuraion.myapplication.presentation.screens.drawing.models

import pro.yakuraion.myapplication.presentation.painting.models.Frame

data class DrawingScreenState(
    val previousFrame: Frame?,
    val activeFrame: ActiveFrame,
) {

    val deleteFrameAvailable: Boolean = previousFrame != null

    val goToPreviousActionAvailable: Boolean = activeFrame.appliedActions > 0

    val goToNextActionAvailable: Boolean = activeFrame.appliedActions < activeFrame.frame.actions.count()
}
