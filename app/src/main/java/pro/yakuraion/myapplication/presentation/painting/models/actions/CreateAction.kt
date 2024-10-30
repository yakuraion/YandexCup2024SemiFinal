package pro.yakuraion.myapplication.presentation.painting.models.actions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class CreateAction(
    val obj: FrameObject,
    val size: Size,
    val centerOffset: Offset,
    val color: Color,
) : FrameAction() {

    override fun applyTo(snapshot: FrameSnapshot): FrameSnapshot {
        val attrs = FrameObjectAttrs(
            size = size,
            centerOffset = centerOffset,
            color = color,
        )
        val newMap = snapshot.map + mapOf(obj to attrs)
        return FrameSnapshot(newMap)
    }
}
