package pro.yakuraion.myapplication.core

inline fun <T> T.applyIf(condition: Boolean, statement: T.() -> T): T {
    if (condition) {
        return statement()
    }
    return this
}
