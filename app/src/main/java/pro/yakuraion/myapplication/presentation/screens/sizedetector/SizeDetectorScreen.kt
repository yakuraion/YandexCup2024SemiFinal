package pro.yakuraion.myapplication.presentation.screens.sizedetector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold

@Composable
fun SizeDetectorScreen(
    onSizeDetected: (size: Size) -> Unit,
) {
    DrawingContentScaffold(
        topBar = {},
        canvas = {
            Canvas(modifier = Modifier.fillMaxSize()) {
                onSizeDetected(size)
            }
        },
        bottomBar = { },
        bottomBarMenu = {},
    )
}
