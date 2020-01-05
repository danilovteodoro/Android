package br.com.maximatech.maxapp.extensions

import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.progress.*


fun Fragment.showProgress(mainLayout:View){
    if(progress == null) return
    mainLayout.visibility = View.GONE
    progress.visibility = View.VISIBLE
}
fun Fragment.hideProgress(mainLayout:View){
    if(progress == null) return
    mainLayout.visibility = View.VISIBLE
    progress.visibility = View.GONE
}

