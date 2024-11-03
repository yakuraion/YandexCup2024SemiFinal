package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope

data class EraserObject(private val attributes: FrameObjectAttributes) : FrameObject {

    override fun plus(attributes: FrameObjectAttributes): EraserObject {
        return EraserObject(this.attributes + attributes)
    }

    override fun getAttributes(): FrameObjectAttributes = attributes

    override fun DrawScope.draw(attributes: FrameObjectAttributes) {
        val pathAttributes = requireNotNull(attributes.pathAttributes)

        when (pathAttributes.path.size) {
            0 -> Unit

            1 -> {
                drawCircle(
                    color = Color.Black,
                    radius = pathAttributes.radius,
                    center = pathAttributes.path[0],
                    blendMode = BlendMode.DstOut
                )
            }

            else -> {
                for (i in 0 until pathAttributes.path.lastIndex - 1) {
                    drawLine(
                        color = Color.Black,
                        start = pathAttributes.path[i],
                        end = pathAttributes.path[i + 1],
                        strokeWidth = pathAttributes.radius,
                        cap = StrokeCap.Round,
                        blendMode = BlendMode.DstOut
                    )
                }
            }
        }
    }
}
