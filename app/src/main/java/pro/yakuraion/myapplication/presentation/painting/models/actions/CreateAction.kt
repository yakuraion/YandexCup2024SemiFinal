package pro.yakuraion.myapplication.presentation.painting.models.actions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs

class CreateAction(
    val size: Size,
    val centerOffset: Offset,
    val color: Color,
) : FrameAction() {

    override fun applyTo(objectAttrs: FrameObjectAttrs): FrameObjectAttrs {
        return FrameObjectAttrs(
            enabled = true,
            size = size,
            centerOffset = centerOffset,
            color = color,
        )
    }
}
