package pro.yakuraion.myapplication.presentation.painting.models.objects.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes

data class RectObject(private val attributes: FrameObjectAttributes) : FrameObject {

    override fun plus(attributes: FrameObjectAttributes): RectObject {
        return RectObject(this.attributes + attributes)
    }

    override fun getAttributes(): FrameObjectAttributes = attributes

    override fun DrawScope.draw(attributes: FrameObjectAttributes) {
        val positionAttrs = requireNotNull(attributes.positionAttributes)
        val colorAttrs = requireNotNull(attributes.colorAttributes)
        val topLeft = positionAttrs.centerOffset - Offset(positionAttrs.size.width / 2, positionAttrs.size.height / 2)
        drawRect(
            color = colorAttrs.color,
            topLeft = topLeft,
            size = positionAttrs.size,
            style = Stroke(width = positionAttrs.radius),
        )
    }
}
