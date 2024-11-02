package pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs

import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.actions.MoveAction
import pro.yakuraion.myapplication.presentation.painting.utils.DrawingCalculationsUtils

fun Modifier.moveDetector(
    key: Any?,
    activeAttrs: FrameObjectAttrs,
    onAction: (newAttrs: FrameObjectAttrs) -> Unit,
    onFinishAction: (action: MoveAction) -> Unit,
): Modifier {
    return pointerInput(key) {
        awaitPointerEventScope {
            while (true) {
                val downEventChange = awaitFirstDown()

                val isObjectAffected = DrawingCalculationsUtils.isGestureEventInside(
                    gestureOffset = downEventChange.position,
                    objectSize = activeAttrs.size,
                    objectCenterOffset = activeAttrs.centerOffset,
                )
                if (!isObjectAffected) continue

                var newCenterOffset = activeAttrs.centerOffset
                var drag: PointerInputChange?
                do {
                    drag = awaitDragOrCancellation(downEventChange.id)
                        ?.also { notNullDrag ->
                            val delta = (notNullDrag.position - notNullDrag.previousPosition)
                            newCenterOffset += delta
                            val newAttrs = activeAttrs.copy(centerOffset = newCenterOffset)
                            onAction.invoke(newAttrs)
                        }
                } while (drag != null)
                val action = MoveAction(newCenterOffset)
                onFinishAction(action)
            }
        }
    }
}
