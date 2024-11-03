package pro.yakuraion.myapplication.presentation.screens.drawing.components.working

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.components.MyIconButton

@Composable
fun DrawingWorkingTopBar(
    canGoBack: Boolean,
    canGoForward: Boolean,
    canDeleteFrame: Boolean,
    onGoBackClick: () -> Unit,
    onGoForwardClick: () -> Unit,
    onDeleteFrameClick: () -> Unit,
    onAddNewFrameClick: () -> Unit,
    onShowFramesClick: () -> Unit,
    onStartPreviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HistoryRow(
            canGoBack = canGoBack,
            canGoForward = canGoForward,
            onGoBackClick = onGoBackClick,
            onGoForwardClick = onGoForwardClick,
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            FrameActionsRow(
                canDeleteFrame = canDeleteFrame,
                onDeleteFrameClick = onDeleteFrameClick,
                onAddNewFrameClick = onAddNewFrameClick,
                onShowFramesClick = onShowFramesClick,
            )
        }

        PreviewRow(
            onStartPreviewClick = onStartPreviewClick,
            modifier = modifier
        )

    }
}

@Composable
private fun HistoryRow(
    canGoBack: Boolean,
    canGoForward: Boolean,
    onGoBackClick: () -> Unit,
    onGoForwardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        MyIconButton(
            enabled = canGoBack,
            painter = painterResource(R.drawable.ic_go_back_24),
            onClick = onGoBackClick,
            modifier = Modifier.size(32.dp),
        )

        MyIconButton(
            enabled = canGoForward,
            painter = painterResource(R.drawable.ic_go_forward_24),
            onClick = onGoForwardClick,
            modifier = Modifier.size(32.dp),
        )
    }
}

@Composable
private fun FrameActionsRow(
    canDeleteFrame: Boolean,
    onDeleteFrameClick: () -> Unit,
    onAddNewFrameClick: () -> Unit,
    onShowFramesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        MyIconButton(
            enabled = canDeleteFrame,
            painter = painterResource(R.drawable.ic_bin_32),
            onClick = onDeleteFrameClick,
            modifier = Modifier.size(40.dp),
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_new_frame_32),
            onClick = onAddNewFrameClick,
            modifier = Modifier.size(40.dp),
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_layers_32),
            onClick = onShowFramesClick,
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun PreviewRow(
    onStartPreviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_play_32),
            onClick = onStartPreviewClick,
            modifier = Modifier.size(40.dp),
        )
    }
}
