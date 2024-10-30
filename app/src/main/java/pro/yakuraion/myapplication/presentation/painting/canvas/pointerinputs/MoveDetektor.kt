package pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs

import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot
import pro.yakuraion.myapplication.presentation.painting.models.actions.MoveAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.utils.DrawingCalculationsUtils

fun Modifier.moveDetector(
    snapshot: FrameSnapshot,
    onAction: (obj: FrameObject, newAttrs: FrameObjectAttrs) -> Unit,
    onFinishAction: (action: MoveAction) -> Unit,
): Modifier {
    return pointerInput(snapshot) {
        awaitPointerEventScope {
            while (true) {
                val downEventChange = awaitFirstDown()
                val obj = getObjByPosition(snapshot, downEventChange.position) ?: continue
                val attrs = snapshot.map[obj] ?: continue

                var newCenterOffset = attrs.centerOffset
                var drag: PointerInputChange?
                do {
                    drag = awaitDragOrCancellation(downEventChange.id)
                        ?.also { notNullDrag ->
                            val delta = (notNullDrag.position - notNullDrag.previousPosition)
                            newCenterOffset += delta
                            val newAttrs = attrs.copy(centerOffset = newCenterOffset)
                            onAction.invoke(obj, newAttrs)
                        }
                } while (drag != null)
                val action = MoveAction(obj.id, newCenterOffset)
                onFinishAction(action)
            }
        }
    }
}

private fun getObjByPosition(snapshot: FrameSnapshot, position: Offset): FrameObject? {
    return snapshot.map.entries
        .reversed()
        .firstOrNull { DrawingCalculationsUtils.isGestureEventInside(position, it.value.size, it.value.centerOffset) }
        ?.key
}
