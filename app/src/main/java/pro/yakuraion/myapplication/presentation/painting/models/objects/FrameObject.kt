package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.drawscope.DrawScope
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs

abstract class FrameObject(val id: Int) {

    abstract fun DrawScope.draw(attrs: FrameObjectAttrs)
}
