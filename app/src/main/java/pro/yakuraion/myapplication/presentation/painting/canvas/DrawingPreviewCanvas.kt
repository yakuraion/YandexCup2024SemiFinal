package pro.yakuraion.myapplication.presentation.painting.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

@Composable
fun DrawingPreviousCanvas(
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
