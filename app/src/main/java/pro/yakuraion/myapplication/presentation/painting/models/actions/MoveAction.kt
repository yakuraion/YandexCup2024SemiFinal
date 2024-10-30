package pro.yakuraion.myapplication.presentation.painting.models.actions

import androidx.compose.ui.geometry.Offset
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot

data class MoveAction(
    val objId: Int,
    val newCenterOffset: Offset,
) : FrameAction() {

    override fun applyTo(snapshot: FrameSnapshot): FrameSnapshot {
        val obj = snapshot.map.keys.first { it.id == objId }
        val attrs = snapshot.map.getValue(obj)
        val newAttrs = attrs.copy(
            centerOffset = newCenterOffset
        )
        val newMap = snapshot.map + mapOf(obj to newAttrs)
        return FrameSnapshot(newMap)
    }
}
