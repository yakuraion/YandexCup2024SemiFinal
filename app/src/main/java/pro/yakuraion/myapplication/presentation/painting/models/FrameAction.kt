package pro.yakuraion.myapplication.presentation.painting.models

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

sealed class FrameAction {

    data class CreateAction(val obj: FrameObject) : FrameAction()

    data class ModifyAction(val newAttrs: FrameObjectAttributes) : FrameAction()
}
