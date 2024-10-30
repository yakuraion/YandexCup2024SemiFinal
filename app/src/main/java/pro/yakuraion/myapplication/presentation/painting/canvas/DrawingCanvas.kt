package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs.moveDetector
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

@Composable
fun DrawingCanvas(
    snapshot: FrameSnapshot,
    onCanvasSizeAvailable: (size: Size) -> Unit,
    onNewAction: (action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var activeObject: ActiveObject? by remember { mutableStateOf(null) }

    Canvas(
        modifier = modifier
            .moveDetector(
                snapshot = snapshot,
                onAction = { obj, newAttrs ->
                    activeObject = ActiveObject(obj, newAttrs)
                },
                onFinishAction = {
                    activeObject = null
                    onNewAction(it)
                },
            )
    ) {
        draw(snapshot, activeObject)
        onCanvasSizeAvailable.invoke(size)
    }
}

private fun DrawScope.draw(snapshot: FrameSnapshot, activeObject: ActiveObject?) {
    for ((obj, attrs) in snapshot.map) {
        with(obj) {
            if (activeObject?.obj == obj) {
                draw(activeObject.attrs)
            } else {
                draw(attrs)
            }
        }
    }
}

private class ActiveObject(val obj: FrameObject, val attrs: FrameObjectAttrs)