package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.myapplication.presentation.painting.canvas.DrawingCanvas
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.components.bottombar.DrawingBottomBar
import pro.yakuraion.myapplication.presentation.screens.drawing.components.preview.DrawingPreviewBox
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

@Composable
fun DrawingScreen(
    viewModel: DrawingViewModel = koinViewModel(),
) {
    DrawingScreen(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAddFrameClick = viewModel::onAddFrameClick,
        onDeleteFrameClick = viewModel::onDeleteFrameClick,
        onAddPenMove = viewModel::onAddPenMove,
        onAddRectClick = viewModel::onAddRectClick,
        onPreviousActionClick = viewModel::onPreviousActionClick,
        onNextActionClick = viewModel::onNextActionClick,
        onPreviewClick = viewModel::onPreviewClick,
        onCancelPreviewClick = viewModel::onCancelPreviewClick,
        onNewAction = viewModel::onNewAction,
        onNewPreviewFrameRequest = viewModel::onNewPreviewFrameRequest,
    )
}

@Composable
private fun DrawingScreen(
    state: DrawingScreenState,
    onAddFrameClick: () -> Unit,
    onDeleteFrameClick: () -> Unit,
    onAddPenMove: () -> Unit,
    onAddRectClick: () -> Unit,
    onPreviousActionClick: () -> Unit,
    onNextActionClick: () -> Unit,
    onPreviewClick: () -> Unit,
    onCancelPreviewClick: () -> Unit,
    onNewAction: (action: FrameAction) -> Unit,
    onNewPreviewFrameRequest: (oldIndex: Long) -> Unit,
) {
    when (state) {
        is DrawingScreenState.Drawing -> {
            Column(modifier = Modifier.fillMaxSize()) {
                DrawingCanvas(
                    frame = state.activeFrame.collectAsStateWithLifecycle().value,
                    previousFrame = state.previousFrame.collectAsStateWithLifecycle().value,
                    onNewAction = onNewAction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
                DrawingBottomBar(
                    deleteFrameEnabled = state.deleteFrameAvailable.collectAsStateWithLifecycle().value,
                    previousEnabled = state.canGoBack.collectAsStateWithLifecycle().value,
                    nextEnabled = state.canGoForward.collectAsStateWithLifecycle().value,
                    onAddFrameClick = onAddFrameClick,
                    onDeleteFrameClick = onDeleteFrameClick,
                    onAddPenMove = onAddPenMove,
                    onAddRectClick = onAddRectClick,
                    onPreviousActionClick = onPreviousActionClick,
                    onNextActionClick = onNextActionClick,
                    onPreviewClick = onPreviewClick,
                    onCancelPreviewClick = onCancelPreviewClick,
                )
            }
        }

        is DrawingScreenState.Preview -> {
            Column(modifier = Modifier.fillMaxSize()) {
                DrawingPreviewBox(
                    frameIndex = state.frameIndex,
                    staticFrame = state.staticFrame,
                    onNewPreviewFrameRequest = onNewPreviewFrameRequest,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
                DrawingBottomBar(
                    deleteFrameEnabled = false,
                    previousEnabled = false,
                    nextEnabled = false,
                    onAddFrameClick = onAddFrameClick,
                    onDeleteFrameClick = onDeleteFrameClick,
                    onAddPenMove = onAddPenMove,
                    onAddRectClick = onAddRectClick,
                    onPreviousActionClick = onPreviousActionClick,
                    onNextActionClick = onNextActionClick,
                    onPreviewClick = onPreviewClick,
                    onCancelPreviewClick = onCancelPreviewClick,
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    MyApplicationTheme {
//        DrawingScreen(
//            state = DrawingScreenState.Drawing(
//                activeFrame = MutableStateFlow(StaticFrame(Size.Zero)),
//                previousFrame = MutableStateFlow(null),
//                canGoBack = MutableStateFlow(true),
//                canGoForward = MutableStateFlow(true),
//                isPenEnabled = MutableStateFlow(false),
//            ),
//            onAddFrameClick = {},
//            onDeleteFrameClick = {},
//            onAddPenMove = {},
//            onAddRectClick = {},
//            onPreviousActionClick = {},
//            onNextActionClick = {},
//            onPreviewClick = {},
//            onCancelPreviewClick = {},
//            onNewAction = {},
//        )
//    }
//}
