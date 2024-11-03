package pro.yakuraion.myapplication.presentation.screens.drawing.components.working

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pro.yakuraion.myapplication.presentation.painting.canvas.PaintingCanvas
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu.ColorsBottomMenu
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu.InstrumentsBottomMenu
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput
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
    onInstrumentsMenuSquareClick: () -> Unit,
    onInstrumentsMenuTriangleClick: () -> Unit,
    onInstrumentsMenuCircleClick: () -> Unit,
    onColorsMenuColorClick: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isInstrumentsBottomMenuVisible by remember { mutableStateOf(false) }
    var isColorsBottomMenuVisible by remember { mutableStateOf(false) }

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
                drawingInput = state.drawingInput.collectAsStateWithLifecycle().value,
                onNewAction = onNewAction,
            )
        },
        bottomBar = {
            val drawingInput = state.drawingInput.collectAsStateWithLifecycle().value
            DrawingWorkingBottomBar(
                isPenActive = drawingInput is DrawingInput.Pen,
                isEraserActive = drawingInput is DrawingInput.Eraser,
                isInstrumentsActive = drawingInput is DrawingInput.Shape,
                currentColor = state.lastColor.collectAsStateWithLifecycle().value,
                onPenClick = onPenClick,
                onEraserClick = onEraserClick,
                onInstrumentsClick = {
                    isInstrumentsBottomMenuVisible = !isInstrumentsBottomMenuVisible
                    isColorsBottomMenuVisible = false
                },
                onColorPickerClick = {
                    isColorsBottomMenuVisible = !isColorsBottomMenuVisible
                    isInstrumentsBottomMenuVisible = false
                }
            )
        },
        bottomBarMenu = {
            if (isInstrumentsBottomMenuVisible) {
                val drawingInput = state.drawingInput.collectAsStateWithLifecycle().value
                val shapeType = drawingInput as? DrawingInput.Shape
                InstrumentsBottomMenu(
                    isSquareActive = shapeType?.type == DrawingInput.Shape.Type.SQUARE,
                    isTriangleActive = shapeType?.type == DrawingInput.Shape.Type.TRIANGLE,
                    isCircleActive = shapeType?.type == DrawingInput.Shape.Type.CIRCLE,
                    onSquareClick = {
                        isInstrumentsBottomMenuVisible = false
                        onInstrumentsMenuSquareClick()
                    },
                    onTriangleClick = {
                        isInstrumentsBottomMenuVisible = false
                        onInstrumentsMenuTriangleClick()
                    },
                    onCircleClick = {
                        isInstrumentsBottomMenuVisible = false
                        onInstrumentsMenuCircleClick()
                    }
                )
            }

            if (isColorsBottomMenuVisible) {
                ColorsBottomMenu(
                    currentColor = state.lastColor.collectAsStateWithLifecycle().value,
                    onColorClick = { color ->
                        isColorsBottomMenuVisible = false
                        onColorsMenuColorClick(color)
                    }
                )
            }
        },
        modifier = modifier,
    )
}


