package pro.yakuraion.myapplication.presentation.screens.drawing

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.core.combineStates
import pro.yakuraion.myapplication.core.mapState
import pro.yakuraion.myapplication.presentation.painting.models.Document
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.framesranges.PenDrawFramesRange
import pro.yakuraion.myapplication.presentation.painting.models.framesranges.SingleFramesRange
import pro.yakuraion.myapplication.presentation.painting.models.objects.PenObject
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingInput
import pro.yakuraion.myapplication.presentation.screens.drawing.models.DrawingScreenState

class DrawingViewModel : ViewModel() {

    // todo
    private val canvasSize: Size = Size(1080.0f, 2274.0f)

    private val document: Document = Document(canvasSize)

    private val drawingInputType: MutableStateFlow<DrawingInputType> = MutableStateFlow(DrawingInputType.PEN)

    private val strokeWidth: MutableStateFlow<Float> = MutableStateFlow(30f)

    private val color: MutableStateFlow<Color> = MutableStateFlow(Color.Red)

    private val drawingInput: StateFlow<DrawingInput> = combineStates(
        drawingInputType,
        strokeWidth,
        color,
    ) { inputType, strokeWidth, color ->
        when (inputType) {
            DrawingInputType.PEN -> DrawingInput.Pen(strokeWidth, color)
            DrawingInputType.ERASER -> DrawingInput.Eraser(strokeWidth)
            DrawingInputType.SHAPE_SQUARE -> DrawingInput.Shape(strokeWidth, color, DrawingInput.Shape.Type.SQUARE)
            DrawingInputType.SHAPE_TRIANGLE -> DrawingInput.Shape(strokeWidth, color, DrawingInput.Shape.Type.TRIANGLE)
            DrawingInputType.SHAPE_CIRCLE -> DrawingInput.Shape(strokeWidth, color, DrawingInput.Shape.Type.CIRCLE)
        }
    }

    private val workingState: DrawingScreenState.Working = DrawingScreenState.Working(
        activeFrame = document.activeFrame,
        previousFrame = document.previousFrame,
        canGoBack = document.canGoBack(),
        canGoForward = document.canGoForward(),
        canDeleteFrame = document.previousFrame.mapState { it != null },
        drawingInput = drawingInput,
        lastStrokeWidth = strokeWidth,
        lastColor = color,
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

    fun onWorkingAddNewFramesRangeClick() {
        _state.update {
            DrawingScreenState.AddFramesRange(
                size = canvasSize,
                penDrawingInput = DrawingInput.Pen(strokeWidth.value, color.value)
            )
        }
    }

    fun onWorkingShowFramesClick() {
        _state.update {
            DrawingScreenState.FramesOverview(
                originalFrameSize = canvasSize,
                selectedFrameIndex = document.getFramesCount() - 1,
                lastFrameIndex = document.getFramesCount() - 1,
                getFramePreviewAction = { index -> document.getStaticFrame(index).bitmap },
            )
        }
    }

    fun onWorkingStartPreviewClick() {
        setPreviewFrame(0)
    }

    fun onWorkingNewAction(action: FrameAction) {
        document.addNewAction(action)
    }

    fun onWorkingPenClick() {
        drawingInputType.update { DrawingInputType.PEN }
    }

    fun onWorkingEraserClick() {
        drawingInputType.update { DrawingInputType.ERASER }
    }

    fun onWorkingInstrumentsMenuSquareClick() {
        drawingInputType.update { DrawingInputType.SHAPE_SQUARE }
    }

    fun onWorkingInstrumentsMenuTriangleClick() {
        drawingInputType.update { DrawingInputType.SHAPE_TRIANGLE }
    }

    fun onWorkingInstrumentsMenuCircleClick() {
        drawingInputType.update { DrawingInputType.SHAPE_CIRCLE }
    }

    fun onWorkingLineWeightMenuStrokeWidthChanged(strokeWidth: Float) {
        this.strokeWidth.update { strokeWidth }
    }

    fun onWorkingColorsMenuColorClick(color: Color) {
        this.color.update { color }
    }

    fun onAddFramesRangeBackClick() {
        _state.update { workingState }
    }

    fun onAddFramesRangeSuccess(obj: PenObject, framesNumber: Int) {
        val newFramesRange = PenDrawFramesRange(
            size = canvasSize,
            penObject = obj,
            numberOfFrames = framesNumber,
        )
        document.addNewFramesRanges(newFramesRange)
        _state.update { workingState }
    }

    fun onFramesOverviewBackClick() {
        _state.update { workingState }
    }

    fun onFramesOverviewGoToFrameClick(frameIndex: Long) {
        _state.update {
            DrawingScreenState.FramesOverview(
                originalFrameSize = canvasSize,
                selectedFrameIndex = frameIndex,
                lastFrameIndex = document.getFramesCount() - 1,
                getFramePreviewAction = { index -> document.getStaticFrame(index).bitmap },
            )
        }
    }

    fun onPreviewNewFrameRequest(oldIndex: Long) {
        if (oldIndex < document.getFramesCount() - 1) {
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

    enum class DrawingInputType {
        PEN, ERASER, SHAPE_SQUARE, SHAPE_TRIANGLE, SHAPE_CIRCLE
    }
}
