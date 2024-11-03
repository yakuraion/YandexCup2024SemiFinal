package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.core.mapState
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

    private val workingState: DrawingScreenState.Working = DrawingScreenState.Working(
        activeFrame = document.activeFrame,
        previousFrame = document.previousFrame,
        canGoBack = document.canGoBack(),
        canGoForward = document.canGoForward(),
        canDeleteFrame = document.previousFrame.mapState { it != null },
        isPenActive = MutableStateFlow(true),
        isEraserActive = MutableStateFlow(false),
        isInstrumentsActive = MutableStateFlow(false),
    )

    private val _state: MutableStateFlow<DrawingScreenState> = MutableStateFlow(workingState)
    val state: StateFlow<DrawingScreenState> = _state

    fun onWorkingGoBackClick() {
        document.goBack()
    }

    fun onWorkingGoForwardClick() {
        document.goForward()
    }

    fun onWorkingDeleteFrameClick() {
        document.deleteLastFrame()
    }

    fun onWorkingAddNewFrameClick() {
        val framesRange = SingleFramesRange(canvasSize)
        document.addNewFramesRanges(framesRange)
    }

    fun onWorkingShowFramesClick() {

    }

    fun onWorkingStartPreviewClick() {
        setPreviewFrame(0)
    }

    fun onWorkingNewAction(action: FrameAction) {
        document.addNewAction(action)
    }

    fun onWorkingPenClick() {

    }

    fun onWorkingEraserClick() {

    }

    fun onWorkingInstrumentsClick() {
        val obj = RectObject(getDefaultObjectAttrs())
        val action = FrameAction.CreateAction(obj)
        document.addNewAction(action)
    }

    fun onPreviewNewFrameRequest(oldIndex: Long) {
        if (oldIndex < document.getStaticFramesCount() - 1) {
            setPreviewFrame(oldIndex + 1)
        } else {
            setPreviewFrame(0)
        }
    }

    fun onPreviewStopClick() {
        _state.update { workingState }
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
