package util

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.ImageSpan
import br.com.maximatech.maxapp.R

class TextUtil {
    companion object {
        fun spanDrawable(d:Drawable):SpannableString{
            val ss = SpannableString("  ")
            d.setBounds(0,0,22,22)
            val imageSpan = ImageSpan(d, ImageSpan.ALIGN_BOTTOM)
            ss.setSpan(imageSpan,0,2,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
            return ss
        }
    }
}