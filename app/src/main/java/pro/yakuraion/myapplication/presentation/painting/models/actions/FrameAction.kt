package pro.yakuraion.myapplication.presentation.painting.models.actions

import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot

abstract class FrameAction {

    abstract fun applyTo(snapshot: FrameSnapshot): FrameSnapshot
}
