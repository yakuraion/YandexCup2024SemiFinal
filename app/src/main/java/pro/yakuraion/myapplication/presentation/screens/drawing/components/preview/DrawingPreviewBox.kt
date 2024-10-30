package pro.yakuraion.myapplication.presentation.screens.drawing.components.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.delay
import pro.yakuraion.myapplication.presentation.painting.models.Document

@Composable
fun DrawingPreviewBox(
    document: Document,
    size: Size,
    modifier: Modifier = Modifier,
) {
    var currentFrameIndex by remember { mutableIntStateOf(0) }
    val bitmap = document.frames[currentFrameIndex].render(size)
    Box(modifier = modifier) {
        Image(
            bitmap = bitmap,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            while (currentFrameIndex < document.frames.lastIndex) {
                delay(50L)
                currentFrameIndex++
            }
            delay(50L)
            currentFrameIndex = 0
        }
    }
}
