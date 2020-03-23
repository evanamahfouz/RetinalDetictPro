package com.example.ratinadeticpro.Ui.ui.Login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.LunchFragmentActivity
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.Ui.ui.predict.PredictFragment
import com.example.ratinadeticpro.Ui.ui.signUp.MainActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {


    private lateinit var id: String
    private lateinit var pass: String
    private lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            login()

        }
        link_signup.setOnClickListener {

            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }


        }


    }

    private fun login() {
        Log.d("SignUp", "signupNow")

        if (!validate()) {
            onSignFailed()
            return
        }

        btn_login.isEnabled = false

        val progressDialog = ProgressDialog(
            this,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        Toast.makeText(this, "SignIn Successfully", Toast.LENGTH_LONG).show()
        viewModel =
            ViewModelProviders.of(this, factory).get(LoginViewModel::class.java).also {

                it.getPost(
                    id, pass
                )
            }


        android.os.Handler().postDelayed(
            {
                run {
                    // On complete call either onSignSuccess or onSignFailed
                    // depending on success
                    onSignSuccess()
                    // onSignFailed();
                    progressDialog.dismiss()
                }
            }, 3000
        )
    }


    private fun onSignSuccess() {
        btn_login.isEnabled = true
        viewModel.mutableList.observe(this, Observer {
            setResult(RESULT_OK, null)

            Intent(this, LunchFragmentActivity::class.java).also {
                startActivity(it)
            }
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                Toast.makeText(this, errorLabel, Toast.LENGTH_LONG).show()

            }
        })

    }

    private fun onSignFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()

        btn_login.isEnabled = true
    }

    private fun validate(): Boolean {
        var valid = true

        id = input_id_login.text.toString()
        pass = input_password_login.text.toString()

        if (id.isEmpty() || id.length != 9) {
            input_id_login.error = "ID must be 9 characters"
            valid = false
        } else {
            input_id_login.error = null
        }


        if (pass.isEmpty() || pass.length < 5 || pass.length > 10) {
            input_password_login.error = "between 5 and 10 alphanumeric characters"
            valid = false
        } else {
            input_password_login.error = null
        }

        return valid
    }

}
