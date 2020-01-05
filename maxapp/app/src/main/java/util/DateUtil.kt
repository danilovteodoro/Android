package util

import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class DateUtil {
    companion object {
        fun format(date:Date,pattern:String):String{
            val dateFormat = SimpleDateFormat(pattern)
            return dateFormat.format(date)
        }

        fun parse(date:String,pattern: String):Date?{
            return SimpleDateFormat(pattern,Locale("pt","BR")).parse(date)
        }

        fun isToday(date: Date):Boolean{
            val dateNow = Calendar.getInstance()
            val compareDate = Calendar.getInstance()
            compareDate.time = date

            return dateNow.get(Calendar.YEAR) == compareDate.get(Calendar.YEAR)
                    && dateNow.get(Calendar.MONTH) == compareDate.get(Calendar.MONTH)
                    && dateNow.get(Calendar.DAY_OF_MONTH) == compareDate.get(Calendar.DAY_OF_MONTH)
        }
        fun isSameYear(date: Date):Boolean{
            val dateNow = Calendar.getInstance()
            val compareDate = Calendar.getInstance()
            compareDate.time = date

            return dateNow.get(Calendar.YEAR) == compareDate.get(Calendar.YEAR)

        }
    }
}