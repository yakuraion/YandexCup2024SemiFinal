package pro.yakuraion.myapplication.presentation.screens.drawing.models

import androidx.compose.ui.graphics.Color

sealed class DrawingInput(
    open val radius: Float?,
    open val color: Color?,
) {

    data class Pen(
        override val radius: Float,
        override val color: Color,
    ) : DrawingInput(radius, color)

    data class Eraser(
        override val radius: Float,
    ) : DrawingInput(radius, null)

    data class Shape(
        override val radius: Float,
        override val color: Color,
        val type: Type,
    ) : DrawingInput(radius, color) {

        enum class Type {
            SQUARE, TRIANGLE, CIRCLE
        }
    }
}
