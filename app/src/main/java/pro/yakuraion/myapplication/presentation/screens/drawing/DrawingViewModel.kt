package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.presentation.painting.models.FrameStep
import pro.yakuraion.myapplication.presentation.painting.models.actions.CreateAction
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.RectObject
import pro.yakuraion.myapplication.presentation.screens.drawing.state.DrawingScreenState
import kotlin.random.Random

class DrawingViewModel : ViewModel() {

    val state: DrawingScreenState = DrawingScreenState()

    fun onAddRectClick() {
        val newObj = RectObject(id = Random.nextInt())
        val action = CreateAction(
            size = Size(200f, 200f),
            centerOffset = Offset(200f, 200f),
            color = Color.Red,
        )
        addNewStep(newObj, action, true)
    }

    fun onNewAction(obj: FrameObject, action: FrameAction) {
        addNewStep(obj, action)
    }

    private fun addNewStep(
        obj: FrameObject,
        action: FrameAction,
        isObjectNew: Boolean = false,
    ) {
        val newStep = FrameStep(obj = obj, action = action)
        state.frame.update { oldFrame ->
            val objects = if (isObjectNew) oldFrame.objects + obj else oldFrame.objects
            oldFrame.copy(
                objects = objects,
                steps = oldFrame.steps + newStep,
            )
        }
    }
}
