package pro.yakuraion.myapplication.presentation.screens.drawing.components.bottombar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pro.yakuraion.myapplication.presentation.ui.theme.MyApplicationTheme

@Composable
fun DrawingBottomBar(
    previousEnabled: Boolean,
    onAddFrameClick: () -> Unit,
    onAddRectClick: () -> Unit,
    onPreviousStepClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        IconButton(
            onClick = onAddFrameClick,
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onAddRectClick,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Black,
            )
        }

        IconButton(
            onClick = onPreviousStepClick,
            enabled = previousEnabled,
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
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
            previousEnabled = true,
            onAddFrameClick = {},
            onAddRectClick = {},
            onPreviousStepClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
