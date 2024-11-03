package pro.yakuraion.myapplication.presentation.painting.canvas.interactors

import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.painting.models.objects.PenObject

@Composable
fun Modifier.penDrawInteractor(
    active: Boolean,
    frame: ActiveFrame,
    isAcquired: Boolean,
    radius: Float,
    color: Color,
    onAcquireRequest: () -> Unit,
    onFinishAction: (action: FrameAction) -> Unit,
): Modifier {
    if (!active) return this

    var newObject by remember(frame) {
        val defaultAttrs = FrameObjectAttributes(
            pathAttributes = FrameObjectAttributes.PathAttributes(emptyList(), radius),
            colorAttributes = FrameObjectAttributes.ColorAttributes(color),
        )
        mutableStateOf(PenObject(defaultAttrs))
    }

    val points = remember(frame) { mutableStateListOf<Offset>() }

    val modifier = this
        .pointerInput(frame) {
            awaitPointerEventScope {
                while (true) {
                    val downEventChange = awaitFirstDown()
                    onAcquireRequest()

                    var drag: PointerInputChange?
                    do {
                        drag = awaitDragOrCancellation(downEventChange.id)
                            ?.also { notNullDrag ->
                                points.add(notNullDrag.position)
                                val attrs = FrameObjectAttributes(
                                    pathAttributes = FrameObjectAttributes.PathAttributes(
                                        path = points,
                                        radius = radius,
                                    )
                                )
                                newObject += attrs
                            }
                    } while (drag != null)
                    val finalAttrs = newObject.getAttributes() + FrameObjectAttributes.PathAttributes(
                        path = points.toList(),
                        radius = radius,
                    )
                    val action = FrameAction.CreateAction(newObject + finalAttrs)
                    onFinishAction(action)
                }
            }
        }
        .drawWithContent {
            drawContent()

            if (isAcquired) {
                with(newObject) {
                    draw()
                }
            }
        }
    return modifier
}
