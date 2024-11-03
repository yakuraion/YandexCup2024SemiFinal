package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.frames.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.frames.StaticFrame

@Composable
fun DrawingCanvas(
    frame: ActiveFrame,
    previousFrame: StaticFrame?,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (previousFrame != null) {
            DrawingPreviousCanvas(
                frame = previousFrame,
                modifier = Modifier.fillMaxSize(),
            )
        }

        DrawingWorkingCanvas(
            frame = frame,
            onNewAction = onNewAction,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
