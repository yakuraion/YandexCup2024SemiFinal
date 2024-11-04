package pro.yakuraion.myapplication.presentation.painting.models.framesranges

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame
import pro.yakuraion.myapplication.presentation.painting.models.objects.PenObject
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

data class PenDrawFramesRange(
    val size: Size,
    val penObject: PenObject,
    val numberOfFrames: Int,
    val lastFrameAsRange: SingleFramesRange = SingleFramesRange(
        size = size,
        activeFrame = (ActiveFrame.new(size) + FrameAction.CreateAction(penObject)).fix()
    ),
) : FramesRange {

    override val frameCount: Long = numberOfFrames.toLong()

    override fun get(index: Long): StaticFrame {
        return if (index == numberOfFrames.toLong() - 1) {
            lastFrameAsRange[0]
        } else {
            val cutPenObject = getCutPenObject(
                originalPenObject = penObject,
                frameIndex = index.toInt(),
                numberOfFrames = numberOfFrames,
            )
            (ActiveFrame.new(size) + FrameAction.CreateAction(cutPenObject)).toStaticFrame(true)
        }
    }

    override fun plus(action: FrameAction): FramesRange = copy(lastFrameAsRange = lastFrameAsRange + action)

    override fun getActiveFrame(): ActiveFrame = lastFrameAsRange.getActiveFrame()

    override fun deleteLastFrame(): FramesRange? {
        return if (numberOfFrames == 1) {
            null
        } else {
            PenDrawFramesRange(
                size = size,
                penObject = getCutPenObject(penObject, numberOfFrames - 2, numberOfFrames - 1),
                numberOfFrames = numberOfFrames - 1,
            )
        }
    }

    override fun goBack(): FramesRange = copy(lastFrameAsRange = lastFrameAsRange.goBack())

    override fun goForward(): FramesRange = copy(lastFrameAsRange = lastFrameAsRange.goForward())

    override fun fix(): FramesRange = copy(lastFrameAsRange = lastFrameAsRange.fix())

    private fun getCutPenObject(originalPenObject: PenObject, frameIndex: Int, numberOfFrames: Int): PenObject {
        val path = requireNotNull(originalPenObject.getAttributes().pathAttributes).path
        val pathLength = (0..<path.lastIndex).fold(0f) { sum, index ->
            sum + getLength(path[index], path[index + 1])
        }
        val desiredPathLength = ((frameIndex + 1).toFloat() / numberOfFrames) * pathLength

        val newPath = mutableListOf(path[0])
        var newPathLength = 0f
        var currentPathPointIndex = 0
        while (
            currentPathPointIndex < path.lastIndex &&
            newPathLength + getLength(path[currentPathPointIndex], path[currentPathPointIndex + 1]) < desiredPathLength
        ) {
            newPath.add(path[currentPathPointIndex + 1])
            newPathLength += getLength(path[currentPathPointIndex], path[currentPathPointIndex + 1])
            currentPathPointIndex++
        }

        if (currentPathPointIndex < path.lastIndex) {
            val desiredLastLineLength = desiredPathLength - newPathLength
            val lastLine =
                getCutLine(path[currentPathPointIndex], path[currentPathPointIndex + 1], desiredLastLineLength)
            newPath.add(lastLine.second)
        }

        return originalPenObject.copy(
            attributes = originalPenObject.getAttributes().copy(
                pathAttributes = originalPenObject.getAttributes().pathAttributes!!.copy(
                    path = newPath
                )
            )
        )
    }

    private fun getCutLine(start: Offset, end: Offset, desiredLength: Float): Pair<Offset, Offset> {
        val lineLength = getLength(start, end)
        val coef = min(1f, desiredLength / lineLength)
        return start to start + (end - start) * coef
    }

    private fun getLength(start: Offset, end: Offset): Float {
        val delta = start - end
        return sqrt(delta.x.pow(2) + delta.y.pow(2))
    }
}
