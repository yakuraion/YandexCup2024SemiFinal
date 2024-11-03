package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.components.MyIconButton

@Composable
fun InstrumentsBottomMenu(
    isSquareActive: Boolean,
    isTriangleActive: Boolean,
    isCircleActive: Boolean,
    onSquareClick: () -> Unit,
    onTriangleClick: () -> Unit,
    onCircleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomMenu(
        modifier = modifier
            .padding(horizontal = 12.dp),
    ) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_square_24),
            onClick = onSquareClick,
            modifier = Modifier.size(32.dp),
            active = isSquareActive,
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_triangle_24),
            onClick = onTriangleClick,
            modifier = Modifier.size(32.dp),
            active = isTriangleActive,
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_circle_24),
            onClick = onCircleClick,
            modifier = Modifier.size(32.dp),
            active = isCircleActive,
        )
    }
}
