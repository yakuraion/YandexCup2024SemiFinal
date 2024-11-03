package pro.yakuraion.myapplication.presentation.painting.models.framesranges

import pro.yakuraion.myapplication.presentation.painting.models.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.ActiveFrame
import pro.yakuraion.myapplication.presentation.painting.models.StaticFrame

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
