package pro.yakuraion.myapplication.presentation.screens.drawing.components.preview

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

@Composable
fun DrawingPreviewBox(
    frameIndex: Long,
    staticFrame: StaticFrame,
    onNewPreviewFrameRequest: (oldIndex: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        drawImage(staticFrame.bitmap)
    }

    LaunchedEffect(frameIndex) {
        delay(50L)
        onNewPreviewFrameRequest(frameIndex)
    }
}
