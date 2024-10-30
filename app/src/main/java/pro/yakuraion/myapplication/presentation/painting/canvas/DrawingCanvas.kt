package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import pro.yakuraion.myapplication.presentation.painting.canvas.pointerinputs.moveDetector
import pro.yakuraion.myapplication.presentation.painting.models.Frame
import pro.yakuraion.myapplication.presentation.painting.models.FrameObjectAttrs
import pro.yakuraion.myapplication.presentation.painting.models.FrameSnapshot
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

@Composable
fun DrawingCanvas(
    frame: Frame,
    onNewAction: (obj: FrameObject, action: FrameAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var attrsInChange: AttrsInChange? by remember { mutableStateOf(null) }

    Canvas(
        modifier = modifier
            .moveDetector(
                snapshot = frame.snapshot,
                onMove = { obj, newAttrs ->
                    attrsInChange = AttrsInChange(obj, newAttrs)
                },
                onFinishAction = { obj, action ->
                    onNewAction(obj, action)
                }
            )
    ) {
        draw(frame.snapshot, attrsInChange)
    }
}

fun DrawScope.draw(snapshot: FrameSnapshot, attrsInChange: AttrsInChange?) {
    for ((obj, attrs) in snapshot.map) {
        with(obj) {
            if (attrsInChange?.obj == obj) {
                draw(attrsInChange.attrs)
            } else {
                draw(attrs)
            }
        }
    }
}

class AttrsInChange(val obj: FrameObject, val attrs: FrameObjectAttrs)
