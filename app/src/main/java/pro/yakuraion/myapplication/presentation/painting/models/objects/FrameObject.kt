package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.drawscope.DrawScope

interface FrameObject {

    operator fun plus(attributes: FrameObjectAttributes): FrameObject

    fun getAttributes(): FrameObjectAttributes

    fun DrawScope.draw(attributes: FrameObjectAttributes = getAttributes())
}
