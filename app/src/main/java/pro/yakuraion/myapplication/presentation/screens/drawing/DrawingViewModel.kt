package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.presentation.painting.models.Document
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.framesranges.SingleFramesRange
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObjectAttributes
import pro.yakuraion.myapplication.presentation.painting.models.objects.RectObject
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

class DrawingViewModel : ViewModel() {

    // todo
    private val canvasSize: Size = Size(1080.0f, 2274.0f)

    private val document: Document = Document(canvasSize)

    private val isPenEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val drawingState: DrawingScreenState.Drawing = DrawingScreenState.Drawing(
        activeFrame = document.activeFrame,
        previousFrame = document.previousFrame,
        canGoBack = document.canGoBack(),
        canGoForward = document.canGoForward(),
        isPenEnabled = isPenEnabled,
    )

    private val _state: MutableStateFlow<DrawingScreenState> = MutableStateFlow(drawingState)
    val state: StateFlow<DrawingScreenState> = _state

    fun onAddFrameClick() {
        val framesRange = SingleFramesRange(canvasSize)
        document.addNewFramesRanges(framesRange)
    }

    fun onDeleteFrameClick() {
        document.deleteLastFrame()
    }

    fun onPreviousActionClick() {
        document.goBack()
    }

    fun onNextActionClick() {
        document.goForward()
    }

    fun onAddPenMove() {
        isPenEnabled.update { !it }
    }

    fun onAddRectClick() {
        val obj = RectObject(getDefaultObjectAttrs())
        val action = FrameAction.CreateAction(obj)
        document.addNewAction(action)
    }

    fun onPreviewClick() {
        setPreviewFrame(0)

    }

    fun onCancelPreviewClick() {
        _state.update { drawingState }
    }

    fun onNewAction(action: FrameAction) {
        document.addNewAction(action)
    }

    fun onNewPreviewFrameRequest(lastIndex: Long) {
        if (lastIndex < document.getStaticFramesCount() - 1) {
            setPreviewFrame(lastIndex + 1)
        } else {
            setPreviewFrame(0)
        }
    }

    private fun setPreviewFrame(index: Long) {
        _state.update { DrawingScreenState.Preview(index, document.getStaticFrame(index)) }
    }

    private fun getDefaultObjectAttrs(): FrameObjectAttributes {
        return FrameObjectAttributes(
            positionAttributes = FrameObjectAttributes.PositionAttributes(
                centerOffset = Offset(canvasSize.width / 2, canvasSize.height / 2),
                size = Size(DEFAULT_CREATE_WIDTH, DEFAULT_CREATE_HEIGHT),
            ),
            colorAttributes = FrameObjectAttributes.ColorAttributes(
                color = Color.Red,
            ),
        )
    }

    companion object {

        private const val DEFAULT_CREATE_WIDTH = 300f
        private const val DEFAULT_CREATE_HEIGHT = 300f
    }
}
