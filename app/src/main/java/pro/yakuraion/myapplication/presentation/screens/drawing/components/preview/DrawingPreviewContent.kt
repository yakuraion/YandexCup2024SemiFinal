package pro.yakuraion.myapplication.presentation.screens.drawing.components.preview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.components.MyIconButton
import pro.yakuraion.myapplication.presentation.screens.drawing.components.DrawingContentScaffold
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

@Composable
fun DrawingPreviewContent(
    state: DrawingScreenState.Preview,
    onNewFrameRequest: (oldIndex: Long) -> Unit,
    onStopClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DrawingContentScaffold(
        topBar = {
            TopBar(
                onStopClick = onStopClick,
            )
        },
        canvas = {
            Canvas(modifier = modifier) {
                drawImage(state.staticFrame.bitmap)
            }
        },
        bottomBar = {},
        bottomBarMenu = {},
        modifier = modifier,
    )

    LaunchedEffect(state.frameIndex) {
        delay(50L)
        onNewFrameRequest(state.frameIndex)
    }
}

@Composable
private fun TopBar(
    onStopClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_stop_32),
            onClick = onStopClick,
        )
    }
}
