package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

data class FrameObjectAttributes(
    val positionAttributes: PositionAttributes? = null,
    val pathAttributes: PathAttributes? = null,
    val colorAttributes: ColorAttributes? = null,
) {

    operator fun plus(attributes: FrameObjectAttributes): FrameObjectAttributes {
        return copy(
            positionAttributes = attributes.positionAttributes ?: positionAttributes,
            pathAttributes = attributes.pathAttributes ?: pathAttributes,
            colorAttributes = attributes.colorAttributes ?: colorAttributes,
        )
    }

    operator fun plus(attributes: PositionAttributes): FrameObjectAttributes {
        return copy(
            positionAttributes = attributes,
            pathAttributes = pathAttributes,
            colorAttributes = colorAttributes,
        )
    }

    operator fun plus(attributes: PathAttributes): FrameObjectAttributes {
        return copy(
            positionAttributes = positionAttributes,
            pathAttributes = attributes,
            colorAttributes = colorAttributes,
        )
    }

    operator fun plus(attributes: ColorAttributes): FrameObjectAttributes {
        return copy(
            positionAttributes = positionAttributes,
            pathAttributes = pathAttributes,
            colorAttributes = attributes,
        )
    }

    data class PositionAttributes(
        val centerOffset: Offset,
        val size: Size,
    )

    data class PathAttributes(
        val path: List<Offset>,
        val radius: Float,
    )

    data class ColorAttributes(
        val color: Color,
    )
}
