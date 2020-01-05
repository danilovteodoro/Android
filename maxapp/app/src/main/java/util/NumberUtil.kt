package util

import java.text.NumberFormat

class NumberUtil {
    companion object {
        fun formatNumber(number:Long):String{
            return NumberFormat.getInstance()
                .format(number)
        }
    }
}