package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.drawscope.DrawScope

abstract class FrameObjectShape {

    abstract val modifiable: Boolean

    abstract fun DrawScope.draw(attrs: FrameObjectAttrs)
}
