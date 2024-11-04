package pro.yakuraion.myapplication.presentation.screens.drawing

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.PenObject
import pro.yakuraion.myapplication.presentation.screens.drawing.components.addframesrange.DrawingAddFramesRangeContent
import pro.yakuraion.myapplication.presentation.screens.drawing.components.framesoverview.DrawingFramesOverviewContent
import pro.yakuraion.myapplication.presentation.screens.drawing.components.preview.DrawingPreviewContent
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.DrawingWorkingContent
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput
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
        onWorkingAddNewFramesRangeClick = viewModel::onWorkingAddNewFramesRangeClick,
        onWorkingShowFramesClick = viewModel::onWorkingShowFramesClick,
        onWorkingStartPreviewClick = viewModel::onWorkingStartPreviewClick,
        onWorkingNewAction = viewModel::onWorkingNewAction,
        onWorkingPenClick = viewModel::onWorkingPenClick,
        onWorkingEraserClick = viewModel::onWorkingEraserClick,
        onWorkingAddNewFrameMenuEmptyClick = viewModel::onWorkingAddNewFrameMenuEmptyClick,
        onWorkingAddNewFrameMenuDuplicateClick = viewModel::onWorkingAddNewFrameMenuDuplicateClick,
        onWorkingInstrumentsMenuSquareClick = viewModel::onWorkingInstrumentsMenuSquareClick,
        onWorkingInstrumentsMenuTriangleClick = viewModel::onWorkingInstrumentsMenuTriangleClick,
        onWorkingInstrumentsMenuCircleClick = viewModel::onWorkingInstrumentsMenuCircleClick,
        onWorkingLineWeightMenuStrokeWidthChanged = viewModel::onWorkingLineWeightMenuStrokeWidthChanged,
        onWorkingColorsMenuColorClick = viewModel::onWorkingColorsMenuColorClick,
        onAddFramesRangeBackClick = viewModel::onAddFramesRangeBackClick,
        onAddFramesRangeSuccess = viewModel::onAddFramesRangeSuccess,
        onFramesOverviewBackClick = viewModel::onFramesOverviewBackClick,
        onFramesOverviewGoToFrameClick = viewModel::onFramesOverviewGoToFrameClick,
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
    onWorkingAddNewFramesRangeClick: () -> Unit,
    onWorkingShowFramesClick: () -> Unit,
    onWorkingStartPreviewClick: () -> Unit,
    onWorkingNewAction: (action: FrameAction) -> Unit,
    onWorkingPenClick: () -> Unit,
    onWorkingEraserClick: () -> Unit,
    onWorkingAddNewFrameMenuEmptyClick: () -> Unit,
    onWorkingAddNewFrameMenuDuplicateClick: () -> Unit,
    onWorkingInstrumentsMenuSquareClick: () -> Unit,
    onWorkingInstrumentsMenuTriangleClick: () -> Unit,
    onWorkingInstrumentsMenuCircleClick: () -> Unit,
    onWorkingLineWeightMenuStrokeWidthChanged: (Float) -> Unit,
    onWorkingColorsMenuColorClick: (Color) -> Unit,
    onAddFramesRangeBackClick: () -> Unit,
    onAddFramesRangeSuccess: (obj: PenObject, framesNumber: Int) -> Unit,
    onFramesOverviewBackClick: () -> Unit,
    onFramesOverviewGoToFrameClick: (index: Long) -> Unit,
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
                onAddNewFramesRangeClick = onWorkingAddNewFramesRangeClick,
                onShowFramesClick = onWorkingShowFramesClick,
                onStartPreviewClick = onWorkingStartPreviewClick,
                onNewAction = onWorkingNewAction,
                onPenClick = onWorkingPenClick,
                onEraserClick = onWorkingEraserClick,
                onAddNewFrameMenuEmptyClick = onWorkingAddNewFrameMenuEmptyClick,
                onAddNewFrameMenuDuplicateClick = onWorkingAddNewFrameMenuDuplicateClick,
                onInstrumentsMenuSquareClick = onWorkingInstrumentsMenuSquareClick,
                onInstrumentsMenuTriangleClick = onWorkingInstrumentsMenuTriangleClick,
                onInstrumentsMenuCircleClick = onWorkingInstrumentsMenuCircleClick,
                onLineWeightMenuStrokeWidthChanged = onWorkingLineWeightMenuStrokeWidthChanged,
                onColorsMenuColorClick = onWorkingColorsMenuColorClick,
            )
        }

        is DrawingScreenState.AddFramesRange -> {
            DrawingAddFramesRangeContent(
                state = state,
                onBackClick = onAddFramesRangeBackClick,
                onSuccess = onAddFramesRangeSuccess,
            )
        }

        is DrawingScreenState.FramesOverview -> {
            DrawingFramesOverviewContent(
                state = state,
                onBackClick = onFramesOverviewBackClick,
                onGoToFrameClick = onFramesOverviewGoToFrameClick,
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
                drawingInput = MutableStateFlow(DrawingInput.Pen(10f, Color.Blue)),
                lastStrokeWidth = MutableStateFlow(30f),
                lastColor = MutableStateFlow(Color.Blue),
            ),
            onWorkingGoBackClick = {},
            onWorkingGoForwardClick = {},
            onWorkingDeleteFrameClick = {},
            onWorkingAddNewFramesRangeClick = {},
            onWorkingShowFramesClick = {},
            onWorkingStartPreviewClick = {},
            onWorkingNewAction = {},
            onWorkingPenClick = {},
            onWorkingEraserClick = {},
            onWorkingAddNewFrameMenuEmptyClick = {},
            onWorkingAddNewFrameMenuDuplicateClick = {},
            onWorkingInstrumentsMenuSquareClick = {},
            onWorkingInstrumentsMenuTriangleClick = {},
            onWorkingInstrumentsMenuCircleClick = {},
            onWorkingLineWeightMenuStrokeWidthChanged = {},
            onWorkingColorsMenuColorClick = {},
            onAddFramesRangeBackClick = {},
            onAddFramesRangeSuccess = { _, _ -> },
            onFramesOverviewBackClick = {},
            onFramesOverviewGoToFrameClick = {},
            onPreviewNewFrameRequest = {},
            onPreviewStopClick = {},
        )
    }
}
