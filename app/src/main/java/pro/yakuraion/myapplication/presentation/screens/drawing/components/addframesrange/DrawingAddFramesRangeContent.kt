package pro.yakuraion.myapplication.presentation.screens.drawing.components.addframesrange

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.components.AskNumberDialog
import pro.yakuraion.myapplication.presentation.components.MyIconButton
import pro.yakuraion.myapplication.presentation.painting.canvas.interactors.penDrawInteractor
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.PenObject
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

@Composable
fun DrawingAddFramesRangeContent(
    state: DrawingScreenState.AddFramesRange,
    onBackClick: () -> Unit,
    onSuccess: (obj: PenObject, framesNumber: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var penObject by remember { mutableStateOf<PenObject?>(null) }
    var showFrameNumberDialog by remember { mutableStateOf(false) }

    DrawingContentScaffold(
        topBar = { TopBar(onBackClick = onBackClick) },
        canvas = {
            Canvas(
                size = state.size,
                drawingInput = state.penDrawingInput,
                onPenObjectDraw = { obj ->
                    penObject = obj
                    showFrameNumberDialog = true
                },
                modifier = Modifier.fillMaxSize()
            )
        },
        bottomBar = { BottomBar() },
        bottomBarMenu = {},
        modifier = modifier,
    )

    if (showFrameNumberDialog) {
        FramesNumberDialog(
            onDismissRequest = {
                showFrameNumberDialog = false
            },
            onNumberSelected = { number ->
                penObject?.let { onSuccess(it, number) }
            }
        )
    }
}

@Composable
private fun Canvas(
    size: Size,
    drawingInput: DrawingInput.Pen,
    onPenObjectDraw: (obj: PenObject) -> Unit,
    modifier: Modifier = Modifier,
) {
    val frame = remember { ActiveFrame(size = size) }

    Canvas(
        modifier = modifier
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .penDrawInteractor(
                frame = frame,
                drawingInput = drawingInput,
                onFinishAction = { action ->
                    val obj = (action as FrameAction.CreateAction).obj as PenObject
                    onPenObjectDraw(obj)
                },
            )
    ) {}
}

@Composable
private fun TopBar(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_arrow_back_24),
            onClick = onBackClick,
        )
    }
}

@Composable
private fun BottomBar() {
    Text(
        text = stringResource(R.string.add_frames_range_hint),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun FramesNumberDialog(
    onDismissRequest: () -> Unit,
    onNumberSelected: (Int) -> Unit,
) {
    AskNumberDialog(
        title = stringResource(R.string.add_frames_number_dialog_title),
        onDismissRequest = onDismissRequest,
        onNumberTrySelect = { number ->
            try {
                val numberInt = number.toInt()
                if (numberInt > 0) {
                    onNumberSelected(numberInt)
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        },
        dialogProperties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    )
}
