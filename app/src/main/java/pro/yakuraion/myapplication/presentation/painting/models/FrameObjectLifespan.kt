package pro.yakuraion.myapplication.presentation.painting.models

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class FrameObjectLifespan(
    private val createAction: FrameAction.CreateAction,
    private val modifyActions: List<FrameAction.ModifyAction>,
) {

    val obj: FrameObject

    init {
        var obj = createAction.obj
        for (modifyAction in modifyActions) {
            obj += modifyAction.newAttrs
        }
        this.obj = obj
    }
}
