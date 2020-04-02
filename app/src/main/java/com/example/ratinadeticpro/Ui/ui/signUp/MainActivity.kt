@file:Suppress("DEPRECATION")

package com.example.ratinadeticpro.Ui.ui.signUp

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ratinadeticpro.data.Firebase.User
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.Login.LoginActivity
import com.example.ratinadeticpro.Ui.ui.LunchFragmentActivity
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var id: String
    private lateinit var pass: String
    private var gender: String = ""
    private lateinit var age: String
    private lateinit var email: String
    private lateinit var viewModel: SignUpViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_signup.setOnClickListener {
            signup()

        }
        hidePassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                input_password.transformationMethod = HideReturnsTransformationMethod.getInstance()


            } else {
                input_password.transformationMethod = PasswordTransformationMethod.getInstance()


            }
        }

        link_login.setOnClickListener {

            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
        gender_radio_group.setOnCheckedChangeListener { _, checkedId ->
            gender = if (checkedId == male_radio_btn.id)
                "male"
            else "female"


        }
    }

    private fun signup() {
        Log.d("SignUp", "signupNow")

        if (!validate()) {
            onSignFailed()
            return
        }

        btn_signup.isEnabled = false

        val progressDialog = ProgressDialog(
            this,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        if (gender != "female") {
            gender = "male"
        }
        viewModel =
            ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java).also {

                it.getPost(
                    User(
                        id,
                        pass,
                        email,
                        age,
                        gender
                    )
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
        btn_signup.isEnabled = true
        viewModel.mutableList.observe(this, Observer {
            sharedPreferences.edit {
                this.putString("id_user", id)
                this.commit()
            }
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

        btn_signup.isEnabled = true
    }

    private fun validate(): Boolean {
        var valid = true

        id = input_Id.text.toString()
        email = input_email.text.toString()
        pass = input_password.text.toString()
        age = input_age.text.toString()

        if (id.isEmpty() || id.length != 9) {
            input_Id.error = "ID must be 9 characters"
            valid = false
        } else {
            input_Id.error = null
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(
                input_email.text
            ).matches()
        ) {
            input_email.error = "enter a valid email address"
            valid = false
        } else {
            input_email.error = null
        }

        if (pass.isEmpty() || pass.length < 5 || pass.length > 10) {
            input_password.error = "between 5 and 10 alphanumeric characters"
            valid = false
        } else {
            input_password.error = null
        }
        if (age.isEmpty() || age.length < 2) {
            input_age.error = "between 1 and 2 alphanumeric characters"
            valid = false
        } else {
            input_age.error = null
        }

        return valid
    }

}
