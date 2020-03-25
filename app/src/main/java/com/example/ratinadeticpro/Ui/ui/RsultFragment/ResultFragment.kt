package com.example.ratinadeticpro.Ui.ui.RsultFragment


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.LunchFragmentActivity
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.Ui.ui.signUp.SignUpViewModel
import com.example.ratinadeticpro.data.db.UserEntity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject
import javax.xml.transform.Result

@Suppress("DEPRECATION")
class ResultFragment : Fragment() {
    lateinit var viewModel: ResultViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, factory).get(ResultViewModel::class.java).also {

                it.getPost(
                    "123456789"
                )
            }
        viewModel.mutableList.observe(this, Observer {

            result_type.text = it.type + " " + it.probability

            result_description.text = it.descrip
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {

                result_type.text = errorLabel

                Toast.makeText(activity, errorLabel, Toast.LENGTH_LONG).show()

            }
        })

        link_details.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also {
                it.data =
                    Uri.parse("https://towardsdatascience.com/detecting-retina-damage-from-oct-retinal-images-315b4af62938")
                startActivity(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}