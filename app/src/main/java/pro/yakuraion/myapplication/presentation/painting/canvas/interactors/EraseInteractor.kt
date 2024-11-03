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
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.EraserObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput

@Composable
fun Modifier.eraseInteractor(
    frame: ActiveFrame,
    drawingInput: DrawingInput,
    onFinishAction: (action: FrameAction) -> Unit,
): Modifier {
    if (drawingInput !is DrawingInput.Eraser) return this

    var newObject by remember(frame) {
        val defaultAttrs = FrameObjectAttributes(
            pathAttributes = FrameObjectAttributes.PathAttributes(emptyList(), drawingInput.radius),
        )
        mutableStateOf(EraserObject(defaultAttrs))
    }

    val points = remember(frame) { mutableStateListOf<Offset>() }

    val modifier = this
        .pointerInput(frame) {
            awaitPointerEventScope {
                while (true) {
                    val downEventChange = awaitFirstDown()

                    var drag: PointerInputChange?
                    do {
                        drag = awaitDragOrCancellation(downEventChange.id)
                            ?.also { notNullDrag ->
                                points.add(notNullDrag.position)
                                val attrs = FrameObjectAttributes(
                                    pathAttributes = FrameObjectAttributes.PathAttributes(
                                        path = points,
                                        radius = drawingInput.radius,
                                    )
                                )
                                newObject += attrs
                            }
                    } while (drag != null)
                    val finalAttrs = newObject.getAttributes() + FrameObjectAttributes.PathAttributes(
                        path = points.toList(),
                        radius = drawingInput.radius,
                    )
                    val action = FrameAction.CreateAction(newObject + finalAttrs)
                    onFinishAction(action)
                }
            }
        }
        .drawWithContent {
            drawContent()

            with(newObject) {
                draw()
            }
        }
    return modifier
}
