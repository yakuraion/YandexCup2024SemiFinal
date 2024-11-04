package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 32.dp),
    ) {
        val value = (lineWeight - MIN_LINE_WEIGHT) / (MAX_LINE_WEIGHT - MIN_LINE_WEIGHT)

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            )

            Spacer(Modifier.width(16.dp))

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
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(16.dp))

            Spacer(
                modifier = Modifier
                    .size(25.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            )
        }
    }
}
