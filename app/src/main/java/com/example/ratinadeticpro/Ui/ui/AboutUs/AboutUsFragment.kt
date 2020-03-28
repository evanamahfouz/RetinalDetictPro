package com.example.ratinadeticpro.Ui.ui.AboutUs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.fragment_about_us.*

/**
 * A simple [Fragment] subclass.
 */
class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_goDash.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_aboutUsFragment_to_dashBoradFrag)
        }
    }

}
