package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.drawscope.DrawScope

data class FrameObject(
    val shape: FrameObjectShape,
    val attrs: FrameObjectAttrs,
) {

    fun DrawScope.draw() {
        with(shape) {
            draw(attrs)
        }
    }
}
