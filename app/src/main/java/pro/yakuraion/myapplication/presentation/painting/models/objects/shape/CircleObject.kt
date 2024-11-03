package pro.yakuraion.myapplication.presentation.painting.models.objects.shape

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes

data class CircleObject(private val attributes: FrameObjectAttributes) : FrameObject {

    override fun plus(attributes: FrameObjectAttributes): CircleObject {
        return CircleObject(this.attributes + attributes)
    }

    override fun getAttributes(): FrameObjectAttributes = attributes

    override fun DrawScope.draw(attributes: FrameObjectAttributes) {
        val positionAttrs = requireNotNull(attributes.positionAttributes)
        val colorAttrs = requireNotNull(attributes.colorAttributes)

        drawCircle(
            color = colorAttrs.color,
            radius = positionAttrs.size.width / 2,
            center = positionAttrs.centerOffset,
            style = Stroke(width = positionAttrs.strokeWidth),
        )
    }
}
