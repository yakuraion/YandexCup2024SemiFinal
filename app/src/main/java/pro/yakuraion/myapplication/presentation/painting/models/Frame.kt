package pro.yakuraion.myapplication.presentation.painting.models

import pro.yakuraion.myapplication.presentation.painting.models.objects.FrameObject

data class Frame(
    val objects: List<FrameObject> = emptyList(),
    val steps: List<FrameStep> = emptyList(),
) {

    val snapshot: FrameSnapshot = calculateSnapshot()

    private fun calculateSnapshot(): FrameSnapshot {
        val map = objects.associateWith { obj ->
            val objSteps = steps.filter { it.obj == obj }
            var attrs = FrameObjectAttrs.notExist()
            for (step in objSteps) {
                attrs = step.action.applyTo(attrs)
            }
            attrs
        }
        return FrameSnapshot(map)
    }
}
