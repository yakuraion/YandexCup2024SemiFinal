package pro.yakuraion.myapplication.presentation.painting.models.objects

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

data class FrameObjectAttrs(
    val size: Size,
    val centerOffset: Offset,
    val color: Color,
)
