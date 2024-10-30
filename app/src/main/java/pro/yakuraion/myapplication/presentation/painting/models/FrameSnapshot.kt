package pro.yakuraion.myapplication.presentation.painting.models

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class FrameSnapshot(val map: Map<FrameObject, FrameObjectAttrs> = emptyMap())
