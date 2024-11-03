package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.core.applyIf

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    withIntrinsicWidth: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .applyIf(withIntrinsicWidth) {
                width(IntrinsicSize.Min)
            }
            .height(IntrinsicSize.Min)
            .clickable {}
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xA0000000))
        )

        Row(
            modifier = modifier.height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
