package pro.yakuraion.myapplication.presentation.painting.models.actions

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectShape

sealed class FrameAction {

    abstract class CreateAction : FrameAction() {

        abstract val shape: FrameObjectShape

        abstract val attrs: FrameObjectAttrs
    }

    abstract class ModifyAction : FrameAction() {

        abstract fun modify(attrs: FrameObjectAttrs): FrameObjectAttrs
    }
}
