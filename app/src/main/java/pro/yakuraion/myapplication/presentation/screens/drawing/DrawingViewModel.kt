package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.presentation.painting.models.actions.CreateAction
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.RectObject
import pro.yakuraion.myapplication.presentation.screens.drawing.state.DrawingScreenState
import kotlin.random.Random

class DrawingViewModel : ViewModel() {

    private var canvasSize: Size = Size(0f, 0f)

    val state: DrawingScreenState = DrawingScreenState()

    fun onCanvasSizeAvailable(size: Size) {
        canvasSize = size
    }

    fun onPreviousStepClick() {
        state.frame.update { it.stepBack() }
    }

    fun onAddRectClick() {
        val newObj = RectObject(id = Random.nextInt())
        addNewAction(getDefaultCreateAction(newObj))
    }

    fun onNewAction(action: FrameAction) {
        addNewAction(action)
    }

    private fun addNewAction(action: FrameAction) {
        state.frame.update { it + action }
    }

    private fun getDefaultCreateAction(obj: FrameObject): CreateAction {
        return CreateAction(
            obj = obj,
            size = Size(DEFAULT_CREATE_WIDTH, DEFAULT_CREATE_HEIGHT),
            centerOffset = Offset(canvasSize.width / 2, canvasSize.height / 2),
            color = Color.Red,
        )
    }

    companion object {

        private const val DEFAULT_CREATE_WIDTH = 300f
        private const val DEFAULT_CREATE_HEIGHT = 300f
    }
}
