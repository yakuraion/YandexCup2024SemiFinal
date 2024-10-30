package pro.yakuraion.myapplication.presentation.painting.models

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction

data class Frame(val actions: List<FrameAction> = emptyList()) {

    val snapshot: FrameSnapshot

    init {
        var snapshot = FrameSnapshot()
        for (action in actions) {
            snapshot = action.applyTo(snapshot)
        }
        this.snapshot = snapshot
    }

    operator fun plus(action: FrameAction): Frame {
        return Frame(actions = actions + action)
    }

    fun goToAction(number: Int): Frame {
        return Frame(actions = actions.take(number))
    }

    fun render(size: Size): ImageBitmap {
        val drawScope = CanvasDrawScope()
        val bitmap = ImageBitmap(size.width.toInt(), size.height.toInt())
        val canvas = Canvas(bitmap)

        drawScope.draw(
            density = Density(1f),
            layoutDirection = LayoutDirection.Ltr,
            canvas = canvas,
            size = size,
        ) {
            for ((obj, attrs) in snapshot.map) {
                with(obj) {
                    draw(attrs)
                }
            }
        }
        return bitmap
    }
}
