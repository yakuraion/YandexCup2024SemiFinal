package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs.moveShapeDetector
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.frames.ActiveFrame

@Composable
fun DrawingWorkingCanvas(
    frame: ActiveFrame,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var acquiredBy: AcquiredBy? by remember { mutableStateOf(null) }

    val tryAcquireAction: (by: AcquiredBy) -> Unit = { by ->
        if (acquiredBy == null) {
            acquiredBy = by
        }
    }

    val releaseAcquireAction: () -> Unit = {
        acquiredBy = null
    }

    Canvas(
        modifier = modifier
            .moveShapeDetector(
                frame = frame,
                isAcquired = acquiredBy == AcquiredBy.MOVE_SHAPE_DETECTOR,
                onAcquireRequest = { tryAcquireAction(AcquiredBy.MOVE_SHAPE_DETECTOR) },
                onFinishAction = { action ->
                    releaseAcquireAction()
                    onNewAction(action)
                },
            )
    ) {
        frame.staticBitmap?.let { drawImage(it) }

        if (acquiredBy == null && frame.activeObject != null) {
            with(frame.activeObject) {
                draw()
            }
        }
    }
}

private enum class AcquiredBy {
    MOVE_SHAPE_DETECTOR,
}
