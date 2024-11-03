package pro.yakuraion.myapplication.presentation.painting.canvas.interactors

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
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.painting.utils.DrawingCalculationsUtils

@Composable
fun Modifier.moveShapeInteractor(
    active: Boolean,
    frame: ActiveFrame,
    isAcquired: Boolean,
    onAcquireRequest: () -> Unit,
    onFinishAction: (action: FrameAction) -> Unit,
): Modifier {
    if (!active) return this

    val activeObject = frame.activeObject ?: return this

    var activeObjectAttrs by remember(frame) { mutableStateOf(activeObject.getAttributes()) }

    if (activeObjectAttrs.positionAttributes == null || activeObjectAttrs.colorAttributes == null) return this

    return this
        .pointerInput(frame) {
            awaitPointerEventScope {
                while (true) {
                    val downEventChange = awaitFirstDown()

                    val isObjectAffected = DrawingCalculationsUtils.isGestureEventInside(
                        gestureOffset = downEventChange.position,
                        objectSize = activeObjectAttrs.positionAttributes!!.size,
                        objectCenterOffset = activeObjectAttrs.positionAttributes!!.centerOffset,
                    )
                    if (!isObjectAffected) continue
                    onAcquireRequest()

                    var newCenterOffset = activeObjectAttrs.positionAttributes!!.centerOffset
                    var drag: PointerInputChange?
                    do {
                        drag = awaitDragOrCancellation(downEventChange.id)
                            ?.also { notNullDrag ->
                                val delta = (notNullDrag.position - notNullDrag.previousPosition)
                                newCenterOffset += delta
                                activeObjectAttrs += FrameObjectAttributes.PositionAttributes(
                                    centerOffset = newCenterOffset,
                                    size = activeObjectAttrs.positionAttributes!!.size
                                )
                            }
                    } while (drag != null)
                    val action = FrameAction.ModifyAction(activeObjectAttrs)
                    onFinishAction(action)
                }
            }
        }
        .drawWithContent {
            drawContent()

            if (isAcquired) {
                with(activeObject) {
                    draw(activeObjectAttrs)
                }
            }
        }
}
