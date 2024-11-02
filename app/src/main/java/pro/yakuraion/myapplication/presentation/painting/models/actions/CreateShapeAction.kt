package pro.yakuraion.myapplication.presentation.painting.models.actions

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectShape

data class CreateShapeAction(
    override val shape: FrameObjectShape,
    override val attrs: FrameObjectAttrs,
) : FrameAction.CreateAction()
