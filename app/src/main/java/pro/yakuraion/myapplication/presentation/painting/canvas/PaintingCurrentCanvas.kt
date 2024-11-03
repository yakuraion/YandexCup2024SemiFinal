package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.eraseInteractor
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.moveShapeInteractor
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.penDrawInteractor
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.shapeDrawInteractor
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput

@Composable
fun PaintingCurrentCanvas(
    frame: ActiveFrame,
    drawingInput: DrawingInput,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isActiveObjectAcquired: Boolean by remember { mutableStateOf(false) }

    Canvas(
        modifier = modifier
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .moveShapeInteractor(
                active = false,
                frame = frame,
                isAcquired = isActiveObjectAcquired,
                onAcquireRequest = { isActiveObjectAcquired = true },
                onFinishAction = { action ->
                    isActiveObjectAcquired = false
                    onNewAction(action)
                },
            )
            .penDrawInteractor(
                frame = frame,
                drawingInput = drawingInput,
                onFinishAction = onNewAction,
            )
            .eraseInteractor(
                frame = frame,
                drawingInput = drawingInput,
                onFinishAction = onNewAction,
            )
            .shapeDrawInteractor(
                frame = frame,
                drawingInput = drawingInput,
                onFinishAction = onNewAction,
            )
    ) {
        drawImage(frame.toStaticFrame(withActiveObject = !isActiveObjectAcquired).bitmap)
    }
}
