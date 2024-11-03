package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.moveShapeInteractor
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.penDrawInteractor
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction

@Composable
fun DrawingWorkingCanvas(
    frame: ActiveFrame,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var acquiredBy: AcquiredBy? by remember { mutableStateOf(null) }
    var isActiveObjectAcquired: Boolean by remember { mutableStateOf(false) }

    val tryAcquireAction: (by: AcquiredBy, withActiveObject: Boolean) -> Unit = { by, withActiveObject ->
        if (acquiredBy == null) {
            acquiredBy = by
            if (withActiveObject) {
                isActiveObjectAcquired = true
            }
        }
    }

    val releaseAcquireAction: () -> Unit = {
        acquiredBy = null
        isActiveObjectAcquired = false
    }

    Canvas(
        modifier = modifier
            .moveShapeInteractor(
                active = false,
                frame = frame,
                isAcquired = acquiredBy == AcquiredBy.MOVE_SHAPE_DETECTOR,
                onAcquireRequest = { tryAcquireAction(AcquiredBy.MOVE_SHAPE_DETECTOR, true) },
                onFinishAction = { action ->
                    releaseAcquireAction()
                    onNewAction(action)
                },
            )
            .penDrawInteractor(
                active = true,
                frame = frame,
                isAcquired = acquiredBy == AcquiredBy.PEN_DRAW,
                radius = 10f,
                color = Color.Blue,
                onAcquireRequest = { tryAcquireAction(AcquiredBy.PEN_DRAW, false) },
                onFinishAction = { action ->
                    releaseAcquireAction()
                    onNewAction(action)
                }
            )
    ) {
        drawImage(frame.toStaticFrame(withActiveObject = !isActiveObjectAcquired).bitmap)
    }
}

private enum class AcquiredBy {
    MOVE_SHAPE_DETECTOR,
    PEN_DRAW,
}
