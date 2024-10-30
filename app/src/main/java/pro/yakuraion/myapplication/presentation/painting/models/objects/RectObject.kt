package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs

class RectObject(id: Int) : FrameObject(id) {

    override fun DrawScope.draw(attrs: FrameObjectAttrs) {
        val topLeft = attrs.centerOffset - Offset(attrs.size.width / 2, attrs.size.height / 2)
        drawRect(
            color = attrs.color,
            topLeft = topLeft,
            size = attrs.size,
        )
    }
}
