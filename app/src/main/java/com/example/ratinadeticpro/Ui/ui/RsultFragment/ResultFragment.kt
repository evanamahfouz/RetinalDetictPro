package com.example.ratinadeticpro.Ui.ui.RsultFragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ratinadeticpro.R
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        link_details.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also {
                it.data =
                    Uri.parse("https://towardsdatascience.com/detecting-retina-damage-from-oct-retinal-images-315b4af62938")
                startActivity(it)
            }
        }
    }
}