package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope

data object RectObjectShape : FrameObjectShape() {

    override val modifiable: Boolean = true

    override fun DrawScope.draw(attrs: FrameObjectAttrs) {
        val topLeft = attrs.centerOffset - Offset(attrs.size.width / 2, attrs.size.height / 2)
        drawRect(
            color = attrs.color,
            topLeft = topLeft,
            size = attrs.size,
        )
    }
}
