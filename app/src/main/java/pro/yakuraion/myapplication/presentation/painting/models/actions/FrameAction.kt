package pro.yakuraion.myapplication.presentation.painting.models.actions

import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs

abstract class FrameAction {

    abstract fun applyTo(objectAttrs: FrameObjectAttrs): FrameObjectAttrs
}
