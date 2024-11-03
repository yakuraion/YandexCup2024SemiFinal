package pro.yakuraion.myapplication.presentation.screens.drawing.components.working

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pro.yakuraion.myapplication.presentation.painting.canvas.PaintingCanvas
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

@Composable
fun DrawingWorkingContent(
    state: DrawingScreenState.Working,
    onGoBackClick: () -> Unit,
    onGoForwardClick: () -> Unit,
    onDeleteFrameClick: () -> Unit,
    onAddNewFrameClick: () -> Unit,
    onShowFramesClick: () -> Unit,
    onStartPreviewClick: () -> Unit,
    onNewAction: (action: FrameAction) -> Unit,
    onPenClick: () -> Unit,
    onEraserClick: () -> Unit,
    onInstrumentsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DrawingContentScaffold(
        topBar = {
            DrawingWorkingTopBar(
                canGoBack = state.canGoBack.collectAsStateWithLifecycle().value,
                canGoForward = state.canGoForward.collectAsStateWithLifecycle().value,
                canDeleteFrame = state.canDeleteFrame.collectAsStateWithLifecycle().value,
                onGoBackClick = onGoBackClick,
                onGoForwardClick = onGoForwardClick,
                onDeleteFrameClick = onDeleteFrameClick,
                onAddNewFrameClick = onAddNewFrameClick,
                onShowFramesClick = onShowFramesClick,
                onStartPreviewClick = onStartPreviewClick,
            )
        },
        canvas = {
            PaintingCanvas(
                frame = state.activeFrame.collectAsStateWithLifecycle().value,
                previousFrame = state.previousFrame.collectAsStateWithLifecycle().value,
                onNewAction = onNewAction,
            )
        },
        bottomBar = {
            DrawingWorkingBottomBar(
                isPenActive = state.isPenActive.collectAsStateWithLifecycle().value,
                isEraserActive = state.isEraserActive.collectAsStateWithLifecycle().value,
                isInstrumentsActive = state.isInstrumentsActive.collectAsStateWithLifecycle().value,
                onPenClick = onPenClick,
                onEraserClick = onEraserClick,
                onInstrumentsClick = onInstrumentsClick,
            )
        },
        modifier = modifier,
    )
}


