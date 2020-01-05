package br.com.maximatech.maxapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import br.com.maximatech.maxapp.R
import kotlinx.android.synthetic.main.legendas_dialog_fragment.view.*

class LegendasDialogFragment : DialogFragment() {

    companion object{
        fun show(fragmentManager: FragmentManager){
            LegendasDialogFragment().show(fragmentManager,"")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.legendas_dialog_fragment,container,false)
        view.btnFechar.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}