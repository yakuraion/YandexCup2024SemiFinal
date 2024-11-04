package pro.yakuraion.myapplication.presentation.screens.drawing.components.framesoverview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.core.applyIf
import pro.yakuraion.myapplication.presentation.components.AskNumberDialog
import pro.yakuraion.myapplication.presentation.components.MyIconButton
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu.BottomMenu
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState
import kotlin.math.max
import kotlin.math.min

@Composable
fun DrawingFramesOverviewContent(
    state: DrawingScreenState.FramesOverview,
    onBackClick: () -> Unit,
    onGoToFrameClick: (index: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isBottomMenuVisible by remember { mutableStateOf(true) }
    var activeFrameIndex by remember(state.selectedFrameIndex) { mutableStateOf(state.selectedFrameIndex) }

    var isAskFrameIndexDialogVisible by remember { mutableStateOf(false) }

    DrawingContentScaffold(
        topBar = {
            TopBar(
                frameIndex = activeFrameIndex,
                lastFrameIndex = state.lastFrameIndex,
                onBackClick = onBackClick,
                onGoToFrameClick = { isAskFrameIndexDialogVisible = true }
            )
        },
        canvas = {
            Canvas(modifier = modifier) {
                drawImage(state.getFramePreviewAction.invoke(activeFrameIndex))
            }
        },
        bottomBar = {
            BottomBar(
                isBottomMenuVisible = isBottomMenuVisible,
                onShowHideClick = { isBottomMenuVisible = !isBottomMenuVisible },
            )
        },
        bottomBarMenu = {
            if (isBottomMenuVisible) {
                FramesBottomMenu(
                    state = state,
                    onFrameClick = { activeFrameIndex = it },
                )
            }
        },
        modifier = modifier,
    )

    if (isAskFrameIndexDialogVisible) {
        AskFrameIndexDialog(
            maxFrameIndex = state.lastFrameIndex,
            onDismissRequest = { isAskFrameIndexDialogVisible = false },
            onIndexSelected = onGoToFrameClick,
        )
    }
}

@Composable
private fun TopBar(
    frameIndex: Long,
    lastFrameIndex: Long,
    onBackClick: () -> Unit,
    onGoToFrameClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_arrow_back_24),
            onClick = onBackClick,
            modifier = Modifier.size(32.dp),
        )

        Text(
            text = "${frameIndex + 1} / ${lastFrameIndex + 1}",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        TextButton(
            onClick = onGoToFrameClick,
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            Text(
                text = stringResource(R.string.frames_overview_go_to),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun BottomBar(
    isBottomMenuVisible: Boolean,
    onShowHideClick: () -> Unit,
) {
    MyIconButton(
        painter = painterResource(R.drawable.ic_two_arrows_top),
        onClick = onShowHideClick,
        modifier = Modifier
            .size(40.dp)
            .applyIf(isBottomMenuVisible) {
                rotate(180f)
            },
    )
}

@Composable
private fun FramesBottomMenu(
    state: DrawingScreenState.FramesOverview,
    onFrameClick: (frameIndex: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomMenu(
        modifier = modifier
            .height(120.dp)
            .padding(horizontal = 12.dp),
    ) {
        PreviewLazyList(
            originalSize = state.originalFrameSize,
            initIndex = state.selectedFrameIndex,
            lastIndex = state.lastFrameIndex,
            getFramePreviewAction = state.getFramePreviewAction,
            onFrameClick = onFrameClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun PreviewLazyList(
    originalSize: Size,
    initIndex: Long,
    lastIndex: Long,
    getFramePreviewAction: (index: Long) -> ImageBitmap,
    onFrameClick: (frameIndex: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val aspectRatio = originalSize.width / originalSize.height

    val maxListWindow = Int.MAX_VALUE / 2

    val leftBorder = max(0, initIndex - (maxListWindow / 2))
    val rightBorder = min(lastIndex, initIndex + (maxListWindow / 2))

    val frameIndexToListIndex = { frameIndex: Long -> (frameIndex - leftBorder).toInt() }
    val listIndexToFrameIndex = { listIndex: Int -> listIndex.toLong() + leftBorder }

    val state = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        state = state,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = (rightBorder - leftBorder + 1).toInt(),
            key = { listIndex -> listIndexToFrameIndex(listIndex) }
        ) { index ->
            MiniCanvasBox(
                canvasAspectRatio = aspectRatio,
                imageBitmap = getFramePreviewAction(listIndexToFrameIndex(index)),
                onClick = { onFrameClick(listIndexToFrameIndex(index)) }
            )
        }
    }

    LaunchedEffect(initIndex, lastIndex) {
        state.scrollToItem(frameIndexToListIndex(initIndex))
    }
}

@Composable
private fun MiniCanvasBox(
    canvasAspectRatio: Float,
    imageBitmap: ImageBitmap,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .width(100.dp * canvasAspectRatio)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.White)
            .clickable { onClick() },
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawImage(
                image = imageBitmap,
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
        }
    }
}

@Composable
private fun AskFrameIndexDialog(
    maxFrameIndex: Long,
    onDismissRequest: () -> Unit,
    onIndexSelected: (Long) -> Unit,
) {
    AskNumberDialog(
        title = stringResource(R.string.frames_overview_frame_index_dialog_title),
        onDismissRequest = onDismissRequest,
        onNumberTrySelect = { number ->
            try {
                val numberLong = number.toLong()
                if (numberLong in 1..(maxFrameIndex + 1)) {
                    onIndexSelected(numberLong - 1)
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    )
}
