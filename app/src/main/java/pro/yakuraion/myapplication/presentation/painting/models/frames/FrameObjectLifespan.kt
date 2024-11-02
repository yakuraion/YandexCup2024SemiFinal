package pro.yakuraion.myapplication.presentation.painting.models.frames

import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class FrameObjectLifespan(
    private val createAction: FrameAction.CreateAction,
    private val modifyActions: List<FrameAction.ModifyAction>,
) {

    val obj: FrameObject

    init {
        val shape = createAction.shape
        var attrs = createAction.attrs
        for (modifyAction in modifyActions) {
            attrs = modifyAction.modify(attrs)
        }
        obj = FrameObject(shape, attrs)
    }
}
