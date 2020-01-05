package br.com.maximatech.maxapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.maximatech.maxapp.R

class AlvarasFragment : Fragment() {

    companion object {
        fun getInstance():AlvarasFragment{
            return AlvarasFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alvaras,container,false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.setTitle(R.string.str_alvaras)
    }
}