package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import pro.yakuraion.myapplication.core.applyIf
import pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs.moveDetector
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
            PreviousFrameCanvas(
                frame = previousFrame,
                modifier = Modifier.fillMaxSize(),
            )
        }

        WorkingCanvas(
            frame = frame,
            onNewAction = onNewAction,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun WorkingCanvas(
    frame: ActiveFrame,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val activeObject = frame.activeObject
    var activeObjectAttrs by remember(frame) { mutableStateOf(frame.activeObject?.attrs) }

    Canvas(
        modifier = modifier
            .applyIf(activeObject?.shape?.modifiable == true && activeObjectAttrs != null) {
                moveDetector(
                    key = frame,
                    activeAttrs = requireNotNull(activeObjectAttrs),
                    onAction = { activeObjectAttrs = it },
                    onFinishAction = onNewAction,
                )
            }
    ) {
        frame.staticBitmap?.let { drawImage(it) }

        if (activeObject != null && activeObjectAttrs != null) {
            with(activeObject.shape) {
                draw(requireNotNull(activeObjectAttrs))
            }
        }
    }
}

@Composable
private fun PreviousFrameCanvas(
    frame: StaticFrame,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .alpha(0.3f)
    ) {
        drawImage(frame.bitmap)
    }
}
