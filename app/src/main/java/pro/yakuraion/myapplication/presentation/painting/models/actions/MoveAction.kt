package pro.yakuraion.myapplication.presentation.painting.models.actions

import androidx.compose.ui.geometry.Offset
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttrs

data class MoveAction(val newCenterOffset: Offset) : FrameAction.ModifyAction() {

    override fun modify(attrs: FrameObjectAttrs): FrameObjectAttrs {
        return attrs.copy(
            centerOffset = newCenterOffset
        )
    }
}
