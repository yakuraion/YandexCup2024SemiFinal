package pro.yakuraion.myapplication.presentation.screens.drawing

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.components.preview.DrawingPreviewContent
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.DrawingWorkingContent
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState
import pro.yakuraion.myapplication.presentation.theme.MyApplicationTheme

@Composable
fun DrawingScreen(
    viewModel: DrawingViewModel = koinViewModel(),
) {
    DrawingScreen(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onWorkingGoBackClick = viewModel::onWorkingGoBackClick,
        onWorkingGoForwardClick = viewModel::onWorkingGoForwardClick,
        onWorkingDeleteFrameClick = viewModel::onWorkingDeleteFrameClick,
        onWorkingAddNewFrameClick = viewModel::onWorkingAddNewFrameClick,
        onWorkingShowFramesClick = viewModel::onWorkingShowFramesClick,
        onWorkingStartPreviewClick = viewModel::onWorkingStartPreviewClick,
        onWorkingNewAction = viewModel::onWorkingNewAction,
        onWorkingPenClick = viewModel::onWorkingPenClick,
        onWorkingEraserClick = viewModel::onWorkingEraserClick,
        onWorkingInstrumentsClick = viewModel::onWorkingInstrumentsClick,
        onPreviewNewFrameRequest = viewModel::onPreviewNewFrameRequest,
        onPreviewStopClick = viewModel::onPreviewStopClick,
    )
}

@Composable
private fun DrawingScreen(
    state: DrawingScreenState,
    onWorkingGoBackClick: () -> Unit,
    onWorkingGoForwardClick: () -> Unit,
    onWorkingDeleteFrameClick: () -> Unit,
    onWorkingAddNewFrameClick: () -> Unit,
    onWorkingShowFramesClick: () -> Unit,
    onWorkingStartPreviewClick: () -> Unit,
    onWorkingNewAction: (action: FrameAction) -> Unit,
    onWorkingPenClick: () -> Unit,
    onWorkingEraserClick: () -> Unit,
    onWorkingInstrumentsClick: () -> Unit,
    onPreviewNewFrameRequest: (oldIndex: Long) -> Unit,
    onPreviewStopClick: () -> Unit,
) {
    when (state) {
        is DrawingScreenState.Working -> {
            DrawingWorkingContent(
                state = state,
                onGoBackClick = onWorkingGoBackClick,
                onGoForwardClick = onWorkingGoForwardClick,
                onDeleteFrameClick = onWorkingDeleteFrameClick,
                onAddNewFrameClick = onWorkingAddNewFrameClick,
                onShowFramesClick = onWorkingShowFramesClick,
                onStartPreviewClick = onWorkingStartPreviewClick,
                onNewAction = onWorkingNewAction,
                onPenClick = onWorkingPenClick,
                onEraserClick = onWorkingEraserClick,
                onInstrumentsClick = onWorkingInstrumentsClick,
            )
        }

        is DrawingScreenState.Preview -> {
            DrawingPreviewContent(
                state = state,
                onNewFrameRequest = onPreviewNewFrameRequest,
                onStopClick = onPreviewStopClick,
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MyApplicationTheme {
        DrawingScreen(
            state = DrawingScreenState.Working(
                activeFrame = MutableStateFlow(ActiveFrame(Size(1f, 1f))),
                previousFrame = MutableStateFlow(null),
                canGoBack = MutableStateFlow(true),
                canGoForward = MutableStateFlow(true),
                canDeleteFrame = MutableStateFlow(false),
                isPenActive = MutableStateFlow(true),
                isEraserActive = MutableStateFlow(false),
                isInstrumentsActive = MutableStateFlow(false),
            ),
            onWorkingGoBackClick = {},
            onWorkingGoForwardClick = {},
            onWorkingDeleteFrameClick = {},
            onWorkingAddNewFrameClick = {},
            onWorkingShowFramesClick = {},
            onWorkingStartPreviewClick = {},
            onWorkingNewAction = {},
            onWorkingPenClick = {},
            onWorkingEraserClick = {},
            onWorkingInstrumentsClick = {},
            onPreviewNewFrameRequest = {},
            onPreviewStopClick = {},
        )
    }
}
