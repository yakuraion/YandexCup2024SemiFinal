package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.topmenu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.screens.drawing.components.working.TopBottomMenu

@Composable
fun DeleteFramesTopMenu(
    onLastFrameClick: () -> Unit,
    onAllFramesClick: () -> Unit,
) {
    TopBottomMenu {
        TextButton(
            onClick = onLastFrameClick
        ) {
            Text(
                text = stringResource(R.string.working_delete_frames_last_option),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Spacer(Modifier.width(8.dp))

        VerticalDivider(
            modifier = Modifier.height(32.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.width(8.dp))

        TextButton(
            onClick = onAllFramesClick
        ) {
            Text(
                text = stringResource(R.string.working_delete_frames_all_option),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
