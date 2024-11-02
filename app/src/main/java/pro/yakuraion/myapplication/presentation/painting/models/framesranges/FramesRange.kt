package pro.yakuraion.myapplication.presentation.painting.models.framesranges

import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.frames.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.frames.StaticFrame

interface FramesRange {

    val frameCount: Long

    operator fun get(index: Long): StaticFrame

    operator fun plus(action: FrameAction): FramesRange

    fun getActiveFrame(): ActiveFrame

    fun deleteLastFrame(): FramesRange?

    fun goBack(): FramesRange

    fun goForward(): FramesRange

    fun fix(): FramesRange
}
