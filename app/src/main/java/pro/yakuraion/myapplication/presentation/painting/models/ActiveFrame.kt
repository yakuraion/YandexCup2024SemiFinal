package pro.yakuraion.myapplication.presentation.painting.models

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class ActiveFrame(
    val size: Size,
    val actions: List<FrameAction> = emptyList(),
    val appliedActions: Int = 0,
    val staticBitmap: ImageBitmap? = null,
) {

    private val objectsLifespans: List<FrameObjectLifespan> = calculateLifespans()

    private val activeObjectLifespan: FrameObjectLifespan? = objectsLifespans.lastOrNull()

    val activeObject: FrameObject? = activeObjectLifespan?.obj

    operator fun plus(action: FrameAction): ActiveFrame {
        return copy(
            actions = actions.take(appliedActions) + action,
            appliedActions = appliedActions + 1,
        )
    }

    fun canGoBack(): Boolean = appliedActions > 0

    fun canGoForward(): Boolean = appliedActions < actions.count()

    fun goBack(): ActiveFrame {
        if (!canGoBack()) error("Can't go back")

        return copy(appliedActions = appliedActions - 1)
    }

    fun goForward(): ActiveFrame {
        if (!canGoForward()) error("Can't go forward")

        return copy(appliedActions = appliedActions + 1)
    }

    fun toStaticFrame(withActiveObject: Boolean): StaticFrame {
        val bitmap = render(withActiveObject)
        return StaticFrame(bitmap)
    }

    fun fix(): ActiveFrame {
        return copy(
            actions = emptyList(),
            appliedActions = 0,
            staticBitmap = render(true)
        )
    }

    private fun calculateLifespans(): List<FrameObjectLifespan> {
        val objectsLifespans = mutableListOf<FrameObjectLifespan>()

        var currentActionIndex = 0
        while (currentActionIndex < appliedActions) {
            val createAction = actions[currentActionIndex] as FrameAction.CreateAction
            val modifyActions = mutableListOf<FrameAction.ModifyAction>()

            currentActionIndex++

            while (currentActionIndex < appliedActions && actions[currentActionIndex] is FrameAction.ModifyAction) {
                modifyActions += actions[currentActionIndex] as FrameAction.ModifyAction
                currentActionIndex++
            }

            objectsLifespans += FrameObjectLifespan(createAction, modifyActions)
        }

        return objectsLifespans
    }

    private fun render(withActiveObject: Boolean): ImageBitmap {
        val drawScope = CanvasDrawScope()
        val bitmap = ImageBitmap(size.width.toInt(), size.height.toInt())
        val canvas = Canvas(bitmap)

        drawScope.draw(
            density = Density(1f),
            layoutDirection = LayoutDirection.Ltr,
            canvas = canvas,
            size = size,
        ) {
            staticBitmap?.let { drawImage(it) }

            var lifespansToRender = objectsLifespans
            if (!withActiveObject) {
                lifespansToRender = lifespansToRender.dropLast(1)
            }

            for (lifespan in lifespansToRender) {
                with(lifespan.obj) {
                    draw()
                }
            }
        }
        return bitmap
    }

    companion object {

        fun new(size: Size): ActiveFrame {
            return ActiveFrame(
                size = size,
            )
        }

        fun fromStaticFrame(frame: StaticFrame): ActiveFrame {
            return ActiveFrame(
                size = Size(frame.bitmap.width.toFloat(), frame.bitmap.height.toFloat()),
                staticBitmap = frame.bitmap,
            )
        }
    }
}
