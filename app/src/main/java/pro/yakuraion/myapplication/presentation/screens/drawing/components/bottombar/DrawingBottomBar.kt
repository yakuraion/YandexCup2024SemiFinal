package pro.yakuraion.myapplication.presentation.screens.drawing.components.bottombar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pro.yakuraion.myapplication.presentation.ui.theme.MyApplicationTheme

@Composable
fun DrawingBottomBar(
    deleteFrameEnabled: Boolean,
    previousEnabled: Boolean,
    nextEnabled: Boolean,
    onAddFrameClick: () -> Unit,
    onDeleteFrameClick: () -> Unit,
    onAddPenMove: () -> Unit,
    onAddRectClick: () -> Unit,
    onPreviousActionClick: () -> Unit,
    onNextActionClick: () -> Unit,
    onPreviewClick: () -> Unit,
    onCancelPreviewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        IconButton(
            onClick = onAddFrameClick,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onDeleteFrameClick,
            enabled = deleteFrameEnabled,
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onAddPenMove,
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onAddRectClick,
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onPreviousActionClick,
            enabled = previousEnabled,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onNextActionClick,
            enabled = nextEnabled,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.rotate(180f)
            )
        }

        IconButton(onClick = onPreviewClick) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(onClick = onCancelPreviewClick) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                tint = Color.Black,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        DrawingBottomBar(
            deleteFrameEnabled = true,
            previousEnabled = true,
            nextEnabled = true,
            onAddFrameClick = {},
            onDeleteFrameClick = {},
            onAddPenMove = {},
            onAddRectClick = {},
            onPreviousActionClick = {},
            onNextActionClick = {},
            onPreviewClick = {},
            onCancelPreviewClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
