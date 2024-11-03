package pro.yakuraion.myapplication.presentation.screens.drawing.models

import androidx.compose.ui.graphics.Color

sealed class DrawingInput(
    open val strokeWidth: Float?,
    open val color: Color?,
) {

    data class Pen(
        override val strokeWidth: Float,
        override val color: Color,
    ) : DrawingInput(strokeWidth, color)

    data class Eraser(
        override val strokeWidth: Float,
    ) : DrawingInput(strokeWidth, null)

    data class Shape(
        override val strokeWidth: Float,
        override val color: Color,
        val type: Type,
    ) : DrawingInput(strokeWidth, color) {

        enum class Type {
            SQUARE, TRIANGLE, CIRCLE
        }
    }
}
