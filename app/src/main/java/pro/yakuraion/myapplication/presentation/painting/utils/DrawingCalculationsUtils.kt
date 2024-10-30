package pro.yakuraion.myapplication.presentation.painting.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.abs

object DrawingCalculationsUtils {

    fun isGestureEventInside(
        gestureOffset: Offset,
        objectSize: Size,
        objectCenterOffset: Offset,
    ): Boolean {
        val delta = gestureOffset - objectCenterOffset
        return abs(delta.x) < objectSize.width / 2 && abs(delta.y) < objectSize.height / 2
    }
}
