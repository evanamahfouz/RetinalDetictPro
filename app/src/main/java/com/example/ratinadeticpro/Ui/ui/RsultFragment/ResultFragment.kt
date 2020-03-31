package com.example.ratinadeticpro.Ui.ui.RsultFragment


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory

import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class ResultFragment : Fragment() {
    private lateinit var viewModel: ResultViewModel
    private lateinit var idUser: String
    private lateinit var eyePart: String

    @Inject
    lateinit var sharedPreferences: SharedPreferences
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
        idUser = sharedPreferences.getString(getString(R.string.id_user_key), "")!!
        eyePart = sharedPreferences.getString(getString(R.string.eye_part_key), "")!!

        Toast.makeText(activity, idUser + " " + eyePart, Toast.LENGTH_LONG).show()

        val progressDialog = ProgressDialog(
            context,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Waiting For The Result...")
        progressDialog.show()

        android.os.Handler().postDelayed(
            {
                run {
                    // On complete call either onSignSuccess or onSignFailed
                    // depending on success
                    onSignSuccess()
                    // onSignFailed();
                    progressDialog.dismiss()
                }
            }, 9000
        )


        link_details.setOnClickListener {
            Intent(Intent.ACTION_VIEW).also {
                it.data =
                    Uri.parse("https://towardsdatascience.com/detecting-retina-damage-from-oct-retinal-images-315b4af62938")
                startActivity(it)
            }
        }
    }

    private fun onSignSuccess() {
        viewModel =
            ViewModelProviders.of(this, factory).get(ResultViewModel::class.java).also {

                it.getPost(
                    idUser, eyePart
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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}