package mf.irregex.keyboard

import android.content.Context
import kotlin.math.max

internal class IrregularKeyboard(
    context: Context?,
    xmlLayoutResId: Int,
    newKeyHeight: Int
) :
    Keyboard(context, xmlLayoutResId) {

    private var height: Int

    public override fun getKeyHeight(): Int {
        return super.getKeyHeight()
    }

    public override fun getVerticalGap(): Int {
        return super.getVerticalGap()
    }

    private fun setHeight(newHeight: Int) {
        height = newHeight
    }

    override fun getHeight(): Int {
        return height
    }

    init {
        height = super.getHeight()
        adjustKeyHeight(newKeyHeight)
    }

    private fun adjustKeyHeight(newKeyHeight: Int) {
        val oldKeyHeight = keyHeight
        val verticalGap = verticalGap
        var rows = 0
        for (key in keys) {
            key.height = newKeyHeight
            val row = (key.y + verticalGap) / (oldKeyHeight + verticalGap)
            key.y = row * newKeyHeight + (row - 1) * verticalGap
            rows = max(rows, row + 1)
        }
        val newH = rows * newKeyHeight + (rows - 1) * verticalGap
        setHeight(newH)
    }
}