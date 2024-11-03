package pro.yakuraion.myapplication.presentation.painting.models

import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import pro.yakuraion.myapplication.core.mapState
import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.frames.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.frames.StaticFrame
import pro.yakuraion.myapplication.presentation.painting.models.framesranges.FramesRange
import pro.yakuraion.myapplication.presentation.painting.models.framesranges.SingleFramesRange

class Document(size: Size) {

    private val framesRanges: MutableStateFlow<List<FramesRange>> = MutableStateFlow(
        listOf(SingleFramesRange(size))
    )

    val activeFrame: StateFlow<ActiveFrame> = framesRanges.mapState { it.last().getActiveFrame() }

    val previousFrame: StateFlow<StaticFrame?> = framesRanges.mapState { ranges ->
        val lastRange = ranges.last()
        if (lastRange.frameCount == 1L) {
            val previousRange = ranges.getOrNull(ranges.lastIndex - 1)
            previousRange?.get(previousRange.frameCount - 1)
        } else {
            lastRange[lastRange.frameCount - 2]
        }
    }

    fun getStaticFramesCount(): Long {
        return framesRanges.value.sumOf { it.frameCount }
    }

    fun getStaticFrame(index: Long): StaticFrame {
        var currentFirstIndex = 0L
        var currentFramesRangeIndex = 0
        while (currentFirstIndex + framesRanges.value[currentFramesRangeIndex].frameCount <= index) {
            currentFirstIndex += framesRanges.value[currentFramesRangeIndex].frameCount
            currentFramesRangeIndex++
        }
        return framesRanges.value[currentFramesRangeIndex][index - currentFirstIndex]
    }

    fun canGoBack(): StateFlow<Boolean> = activeFrame.mapState { it.canGoBack() }

    fun canGoForward(): StateFlow<Boolean> = activeFrame.mapState { it.canGoForward() }

    fun addNewAction(action: FrameAction) {
        updateLastFrameRange { it + action }
    }

    fun goBack() {
        if (!canGoBack().value) error("Can't go back")

        updateLastFrameRange { it.goBack() }
    }

    fun goForward() {
        if (!canGoForward().value) error("Can't go forward")

        updateLastFrameRange { it.goForward() }
    }

    fun addNewFramesRanges(framesRange: FramesRange) {
        framesRanges.update { it.dropLast(1) + it.last().fix() + framesRange }
    }

    fun deleteLastFrame() {
        val lastFramesRangeWithoutLastFrame = framesRanges.value.last().deleteLastFrame()

        if (lastFramesRangeWithoutLastFrame == null && framesRanges.value.size == 1) {
            error("Can't remove last frame")
        }

        framesRanges.update { framesRanges ->
            if (lastFramesRangeWithoutLastFrame != null) {
                framesRanges.dropLast(1) + lastFramesRangeWithoutLastFrame
            } else {
                framesRanges.dropLast(1)
            }
        }
    }

    private fun updateLastFrameRange(action: (FramesRange) -> FramesRange) {
        framesRanges.update { it.dropLast(1) + action(it.last()) }
    }
}
