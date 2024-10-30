package pro.yakuraion.myapplication.presentation.screens.drawing.models

import pro.yakuraion.myapplication.presentation.painting.models.Frame
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot

data class ActiveFrame(
    val frame: Frame = Frame(),
    val appliedActions: Int = 0,
) {

    val snapshot: FrameSnapshot = frame.goToAction(appliedActions).snapshot
}
