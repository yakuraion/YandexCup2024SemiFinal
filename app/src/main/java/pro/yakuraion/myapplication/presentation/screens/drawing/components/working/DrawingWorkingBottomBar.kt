package pro.yakuraion.myapplication.presentation.screens.drawing.components.working

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.R
import pro.yakuraion.myapplication.presentation.components.ColorButton
import pro.yakuraion.myapplication.presentation.components.MyIconButton

@Composable
fun DrawingWorkingBottomBar(
    isPenActive: Boolean,
    isEraserActive: Boolean,
    isInstrumentsActive: Boolean,
    currentColor: Color,
    onPenClick: () -> Unit,
    onEraserClick: () -> Unit,
    onInstrumentsClick: () -> Unit,
    onColorPickerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        MyIconButton(
            painter = painterResource(R.drawable.ic_pencil_32),
            onClick = onPenClick,
            active = isPenActive,
            modifier = Modifier.size(40.dp),
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_erase_32),
            onClick = onEraserClick,
            active = isEraserActive,
            modifier = Modifier.size(40.dp),
        )

        Spacer(Modifier.width(8.dp))

        MyIconButton(
            painter = painterResource(R.drawable.ic_instruments_32),
            onClick = onInstrumentsClick,
            active = isInstrumentsActive,
            modifier = Modifier.size(40.dp),
        )

        Spacer(Modifier.width(8.dp))

        ColorButton(
            color = currentColor,
            onClick = onColorPickerClick,
            withBorder = true,
        )
    }
}
