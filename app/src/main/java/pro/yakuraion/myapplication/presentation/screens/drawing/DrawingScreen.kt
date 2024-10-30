package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.myapplication.presentation.painting.canvas.DrawingCanvas
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.components.bottombar.DrawingBottomBar
import pro.yakuraion.myapplication.presentation.screens.drawing.state.DrawingScreenState
import pro.yakuraion.myapplication.presentation.ui.theme.MyApplicationTheme

@Composable
fun DrawingScreen(
    viewModel: DrawingViewModel = koinViewModel(),
) {
    DrawingScreen(
        state = viewModel.state,
        onCanvasSizeAvailable = viewModel::onCanvasSizeAvailable,
        onAddRectClick = viewModel::onAddRectClick,
        onPreviousStepClick = viewModel::onPreviousStepClick,
        onNewAction = viewModel::onNewAction,
    )
}

@Composable
private fun DrawingScreen(
    state: DrawingScreenState,
    onCanvasSizeAvailable: (size: Size) -> Unit,
    onAddRectClick: () -> Unit,
    onPreviousStepClick: () -> Unit,
    onNewAction: (action: FrameAction) -> Unit,
) {
    val frame = state.frame.collectAsStateWithLifecycle().value
    Column(modifier = Modifier.fillMaxSize()) {
        DrawingCanvas(
            snapshot = frame.snapshot,
            onCanvasSizeAvailable = onCanvasSizeAvailable,
            onNewAction = onNewAction,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
        DrawingBottomBar(
            previousEnabled = frame.actions.isNotEmpty(),
            onAddRectClick = onAddRectClick,
            onPreviousStepClick = onPreviousStepClick,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        DrawingScreen(
            state = DrawingScreenState(),
            onCanvasSizeAvailable = {},
            onAddRectClick = {},
            onPreviousStepClick = {},
            onNewAction = {},
        )
    }
}
