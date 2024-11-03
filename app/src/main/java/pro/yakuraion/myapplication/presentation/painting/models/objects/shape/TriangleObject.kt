package pro.yakuraion.myapplication.presentation.painting.models.objects.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes

data class TriangleObject(private val attributes: FrameObjectAttributes) : FrameObject {

    override fun plus(attributes: FrameObjectAttributes): TriangleObject {
        return TriangleObject(this.attributes + attributes)
    }

    override fun getAttributes(): FrameObjectAttributes = attributes

    override fun DrawScope.draw(attributes: FrameObjectAttributes) {
        val positionAttrs = requireNotNull(attributes.positionAttributes)
        val colorAttrs = requireNotNull(attributes.colorAttributes)

        val bottomLeft =
            positionAttrs.centerOffset + Offset(-positionAttrs.size.width / 2, positionAttrs.size.height / 2)
        val bottomRight =
            positionAttrs.centerOffset + Offset(positionAttrs.size.width / 2, positionAttrs.size.height / 2)
        val topCenter =
            positionAttrs.centerOffset + Offset(0f, -positionAttrs.size.height / 2)

        drawLine(
            color = colorAttrs.color,
            start = bottomLeft,
            end = topCenter,
            cap = StrokeCap.Round,
            strokeWidth = positionAttrs.strokeWidth,
        )

        drawLine(
            color = colorAttrs.color,
            start = topCenter,
            end = bottomRight,
            cap = StrokeCap.Round,
            strokeWidth = positionAttrs.strokeWidth,
        )

        drawLine(
            color = colorAttrs.color,
            start = bottomRight,
            end = bottomLeft,
            cap = StrokeCap.Round,
            strokeWidth = positionAttrs.strokeWidth,
        )
    }
}
