package pro.yakuraion.myapplication.presentation.painting.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

data class FrameObjectAttrs(
    val enabled: Boolean,
    val size: Size,
    val centerOffset: Offset,
    val color: Color,
) {

    companion object {

        fun notExist(): FrameObjectAttrs {
            return FrameObjectAttrs(
                enabled = false,
                size = Size(0f, 0f),
                centerOffset = Offset(0f, 0f),
                color = Color.Black,
            )
        }
    }
}
