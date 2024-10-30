package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.core.combineStates
import pro.yakuraion.myapplication.presentation.painting.models.Document
import pro.yakuraion.myapplication.presentation.painting.models.actions.CreateAction
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject
import pro.yakuraion.myapplication.presentation.painting.models.objects.RectObject
import pro.yakuraion.myapplication.presentation.screens.drawing.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState
import kotlin.random.Random

class DrawingViewModel : ViewModel() {

    private var canvasSize: Size = Size(0f, 0f)

    private val document: MutableStateFlow<Document> = MutableStateFlow(Document())

    private val activeFrame: MutableStateFlow<ActiveFrame> = MutableStateFlow(ActiveFrame())

    private val inPreview: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val drawingState: StateFlow<DrawingScreenState.Drawing> = combineStates(
        document,
        activeFrame,
    ) { document, activeFrame ->
        DrawingScreenState.Drawing(
            previousFrame = document.frames.lastOrNull(),
            activeFrame = activeFrame,
        )
    }

    val state: StateFlow<DrawingScreenState> = combineStates(
        drawingState,
        inPreview,
    ) { drawingState, inPreview ->
        if (inPreview) {
            createPreviewState()
        } else {
            drawingState
        }
    }

    fun onCanvasSizeAvailable(size: Size) {
        canvasSize = size
    }

    fun onAddFrameClick() {
        fixAppliedActions()
        document.update { it.copy(frames = it.frames + activeFrame.value.frame) }
        activeFrame.update { ActiveFrame() }
    }

    fun onDeleteFrameClick() {
        val prevFrame = document.value.frames.last()
        activeFrame.update {
            ActiveFrame(
                frame = prevFrame,
                appliedActions = prevFrame.actions.count(),
            )
        }
        document.update { it.copy(frames = it.frames.dropLast(1)) }
    }

    fun onPreviousActionClick() {
        activeFrame.update { it.copy(appliedActions = it.appliedActions - 1) }
    }

    fun onNextActionClick() {
        activeFrame.update { it.copy(appliedActions = it.appliedActions + 1) }
    }

    fun onPreviewClick() {
        inPreview.update { true }
    }

    fun onCancelPreviewClick() {
        inPreview.update { false }
    }

    fun onAddRectClick() {
        val newObj = RectObject(id = Random.nextInt())
        addNewAction(getDefaultCreateAction(newObj))
    }

    fun onNewAction(action: FrameAction) {
        addNewAction(action)
    }

    private fun addNewAction(action: FrameAction) {
        fixAppliedActions()
        activeFrame.update { activeFrame ->
            ActiveFrame(
                frame = activeFrame.frame + action,
                appliedActions = activeFrame.appliedActions + 1,
            )
        }
    }

    private fun fixAppliedActions() {
        activeFrame.update { it.copy(frame = it.frame.goToAction(it.appliedActions)) }
    }

    private fun getDefaultCreateAction(obj: FrameObject): CreateAction {
        return CreateAction(
            obj = obj,
            size = Size(DEFAULT_CREATE_WIDTH, DEFAULT_CREATE_HEIGHT),
            centerOffset = Offset(canvasSize.width / 2, canvasSize.height / 2),
            color = Color.Red,
        )
    }

    private fun createPreviewState(): DrawingScreenState.Preview {
        val document = document.value
        val activeFrame = activeFrame.value
        val lastFrame = activeFrame.copy(frame = activeFrame.frame.goToAction(activeFrame.appliedActions))
        val documentWithLastFrame = document.copy(frames = document.frames + lastFrame.frame)
        return DrawingScreenState.Preview(
            document = documentWithLastFrame,
            size = canvasSize,
        )
    }

    companion object {

        private const val DEFAULT_CREATE_WIDTH = 300f
        private const val DEFAULT_CREATE_HEIGHT = 300f
    }
}
