package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput

@Composable
fun PaintingCanvas(
    frame: ActiveFrame,
    previousFrame: StaticFrame?,
    drawingInput: DrawingInput,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        if (previousFrame != null) {
            PaintingPreviousCanvas(
                frame = previousFrame,
                modifier = Modifier.fillMaxSize(),
            )
        }

        PaintingCurrentCanvas(
            frame = frame,
            drawingInput = drawingInput,
            onNewAction = onNewAction,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
