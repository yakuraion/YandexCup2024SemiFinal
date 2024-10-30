package pro.yakuraion.myapplication.presentation.painting.models.actions

import androidx.compose.ui.geometry.Offset
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs

class MoveAction(
    val oldCenterOffset: Offset,
    val newCenterOffset: Offset,
) : FrameAction() {

    override fun applyTo(objectAttrs: FrameObjectAttrs): FrameObjectAttrs {
        return objectAttrs.copy(
            centerOffset = newCenterOffset,
        )
    }
}
