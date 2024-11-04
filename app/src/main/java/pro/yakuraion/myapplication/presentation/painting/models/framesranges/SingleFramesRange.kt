package pro.yakuraion.myapplication.presentation.painting.models.framesranges

import androidx.compose.ui.geometry.Size
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

data class SingleFramesRange(
    val size: Size,
    private val activeFrame: ActiveFrame = ActiveFrame(size),
) : FramesRange {

    override val frameCount: Long = 1

    override fun get(index: Long): StaticFrame = activeFrame.toStaticFrame(true)

    override fun plus(action: FrameAction): SingleFramesRange = copy(activeFrame = activeFrame + action)

    override fun getActiveFrame(): ActiveFrame = activeFrame

    override fun deleteLastFrame(): FramesRange? = null

    override fun goBack(): SingleFramesRange = copy(activeFrame = activeFrame.goBack())

    override fun goForward(): SingleFramesRange = copy(activeFrame = activeFrame.goForward())

    override fun fix(): SingleFramesRange = copy(activeFrame = activeFrame.fix())
}
