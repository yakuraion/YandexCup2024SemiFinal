package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private const val MIN_LINE_WEIGHT = 10f
private const val MAX_LINE_WEIGHT = 100f

@Composable
fun LineWeightBottomMenu(
    lineWeight: Float,
    onLineWeightChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomMenu(
        modifier = modifier.padding(horizontal = 40.dp),
        withIntrinsicWidth = false,
    ) {
        val value = (lineWeight - MIN_LINE_WEIGHT) / (MAX_LINE_WEIGHT - MIN_LINE_WEIGHT)

        Slider(
            value = value,
            onValueChange = { newValue ->
                val weight = newValue * (MAX_LINE_WEIGHT - MIN_LINE_WEIGHT) + MIN_LINE_WEIGHT
                onLineWeightChanged(weight)
            },
            colors = SliderColors(
                thumbColor = MaterialTheme.colorScheme.tertiary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                activeTickColor = Color.Transparent,
                inactiveTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTickColor = Color.Transparent,
                disabledThumbColor = MaterialTheme.colorScheme.tertiary,
                disabledActiveTrackColor = MaterialTheme.colorScheme.primary,
                disabledActiveTickColor = Color.Transparent,
                disabledInactiveTrackColor = MaterialTheme.colorScheme.primary,
                disabledInactiveTickColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
