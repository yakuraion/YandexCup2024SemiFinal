package pro.yakuraion.myapplication.presentation.painting.canvas.interactors

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.painting.models.objects.shape.CircleObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.shape.RectObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.shape.TriangleObject
import pro.yakuraion.myapplication.presentation.painting.utils.DrawingCalculationsUtils
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput

private const val DEFAULT_SIZE = 300f

@Composable
fun Modifier.shapeDrawInteractor(
    frame: ActiveFrame,
    drawingInput: DrawingInput,
    onFinishAction: (action: FrameAction) -> Unit,
): Modifier {
    if (drawingInput !is DrawingInput.Shape) return this

    val activeObject = frame.activeObject

    return this
        .pointerInput(frame, drawingInput) {
            awaitPointerEventScope {
                while (true) {
                    val downEventChange = awaitFirstDown()

                    val isActiveObjectModifiable = activeObject?.getAttributes()?.positionAttributes != null
                    val isActiveObjectAffected = if (isActiveObjectModifiable) {
                        val positionAttributes = activeObject?.getAttributes()?.positionAttributes!!
                        DrawingCalculationsUtils.isGestureEventInside(
                            gestureOffset = downEventChange.position,
                            objectSize = positionAttributes.size,
                            objectCenterOffset = positionAttributes.centerOffset,
                        )
                    } else {
                        false
                    }

                    if (isActiveObjectAffected) continue

                    val frameObjectAttributes = FrameObjectAttributes(
                        positionAttributes = FrameObjectAttributes.PositionAttributes(
                            centerOffset = downEventChange.position,
                            size = Size(DEFAULT_SIZE, DEFAULT_SIZE),
                            radius = drawingInput.radius,
                        ),
                        colorAttributes = FrameObjectAttributes.ColorAttributes(
                            color = drawingInput.color,
                        )
                    )
                    val frameObject = when (drawingInput.type) {
                        DrawingInput.Shape.Type.SQUARE -> {
                            RectObject(frameObjectAttributes)
                        }

                        DrawingInput.Shape.Type.TRIANGLE -> {
                            TriangleObject(frameObjectAttributes)
                        }

                        DrawingInput.Shape.Type.CIRCLE -> {
                            CircleObject(frameObjectAttributes)
                        }
                    }

                    val action = FrameAction.CreateAction(frameObject)
                    onFinishAction(action)
                }
            }
        }
}
