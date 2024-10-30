package pro.yakuraion.myapplication.presentation.painting.models

import pro.yakuraion.myapplication.presentation.painting.models.actions.FrameAction
import timber.log.Timber

data class Frame(val actions: List<FrameAction> = emptyList()) {

    val snapshot: FrameSnapshot

    init {
        var snapshot = FrameSnapshot()
        for (action in actions) {
            snapshot = action.applyTo(snapshot)
        }
        this.snapshot = snapshot
        Timber.d("new snapshot = ${this.snapshot}")
    }

    operator fun plus(action: FrameAction): Frame {
        return Frame(actions = actions + action)
    }

    fun goToAction(number: Int): Frame {
        return Frame(actions = actions.take(number))
    }
}
