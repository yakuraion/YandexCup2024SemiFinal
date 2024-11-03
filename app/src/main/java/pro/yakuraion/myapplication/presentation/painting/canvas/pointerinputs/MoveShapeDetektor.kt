package pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs

import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.actions.MoveAction
import pro.yakuraion.myapplication.presentation.painting.models.frames.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.utils.DrawingCalculationsUtils

@Composable
fun Modifier.moveShapeDetector(
    frame: ActiveFrame,
    isAcquired: Boolean,
    onAcquireRequest: () -> Unit,
    onFinishAction: (action: MoveAction) -> Unit,
): Modifier {
    val activeObject = frame.activeObject ?: return this
    var activeObjectAttrs by remember(frame) { mutableStateOf(frame.activeObject.attrs) }

    val modifier = this
        .pointerInput(frame) {
            awaitPointerEventScope {
                while (true) {
                    val downEventChange = awaitFirstDown()

                    val isObjectAffected = DrawingCalculationsUtils.isGestureEventInside(
                        gestureOffset = downEventChange.position,
                        objectSize = activeObjectAttrs.size,
                        objectCenterOffset = activeObjectAttrs.centerOffset,
                    )
                    if (!isObjectAffected) continue
                    onAcquireRequest()

                    var newCenterOffset = activeObjectAttrs.centerOffset
                    var drag: PointerInputChange?
                    do {
                        drag = awaitDragOrCancellation(downEventChange.id)
                            ?.also { notNullDrag ->
                                val delta = (notNullDrag.position - notNullDrag.previousPosition)
                                newCenterOffset += delta
                                activeObjectAttrs = activeObjectAttrs.copy(centerOffset = newCenterOffset)
                            }
                    } while (drag != null)
                    val action = MoveAction(newCenterOffset)
                    onFinishAction(action)
                }
            }
        }
        .drawWithContent {
            drawContent()

            if (isAcquired) {
                with(activeObject.shape) {
                    draw(activeObjectAttrs)
                }
            }
        }
    return modifier
}
