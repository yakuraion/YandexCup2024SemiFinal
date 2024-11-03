package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope

data class PenObject(private val attributes: FrameObjectAttributes) : FrameObject {

    override fun plus(attributes: FrameObjectAttributes): PenObject {
        return PenObject(this.attributes + attributes)
    }

    override fun getAttributes(): FrameObjectAttributes = attributes

    override fun DrawScope.draw(attributes: FrameObjectAttributes) {
        val pathAttributes = requireNotNull(attributes.pathAttributes)
        val colorAttributes = requireNotNull(attributes.colorAttributes)

        when (pathAttributes.path.size) {
            0 -> Unit

            1 -> {
                drawCircle(
                    color = colorAttributes.color,
                    radius = pathAttributes.strokeWidth / 2,
                    center = pathAttributes.path[0],
                )
            }

            else -> {
                for (i in 0 until pathAttributes.path.lastIndex - 1) {
                    drawLine(
                        color = colorAttributes.color,
                        start = pathAttributes.path[i],
                        end = pathAttributes.path[i + 1],
                        strokeWidth = pathAttributes.strokeWidth,
                        cap = StrokeCap.Round,
                    )
                }
            }
        }
    }
}
