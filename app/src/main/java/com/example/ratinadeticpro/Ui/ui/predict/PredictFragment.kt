package com.example.ratinadeticpro.ui.ui.predict

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.core.content.edit
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.ratinadeticpro.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.predict_fragment.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class PredictFragment : Fragment() {
    private lateinit var uriImg: Uri

    private val chose = "Choose com.example.ratinadeticpro.data.model.Email Client..."
    private val email = "retinaldetection@gmail.com"
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    lateinit var idUser: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.predict_fragment, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idUser = sharedPreferences.getString(getString(R.string.id_user_key), "")!!

        //BUTTON CLICK

        img_pick_btn.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }
        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_predictFragment2_to_dashBoradFrag)
        }
        go.setOnClickListener {
            val id: Int = radio_group.checkedRadioButtonId
            if (id == R.id.radio_right) {
                sharedPreferences.edit {
                    this.putString(getString(R.string.eye_part_key), "right")
                    this.commit()
                }
                sendEmail(uriImg, "right")
            } else {
                sharedPreferences.edit {
                    this.putString(getString(R.string.eye_part_key), "lift")
                    this.commit()
                }
                sendEmail(uriImg, "lift")

            }
        }
    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000
        private const val SEND_EMAIL_CODE = 2000

        //Permission code
        private const val PERMISSION_CODE = 1001
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image_view.setImageURI(data?.data)
            uriImg = data!!.data!!
            go.isEnabled = true

        } else if (requestCode == SEND_EMAIL_CODE) {
            Log.v("MessageSent", "SentDon")
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
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_predictFragment2_to_resultFragment2)
                        // depending on success
                        // onSignFailed();
                        progressDialog.dismiss()
                    }
                }, 12000
            )


        }
    }

    private fun sendEmail(uri: Uri, eye_dir: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND).apply {
            /*To send an email you need to specify mailto: as URI using setData() method
            and com.example.ratinadeticpro.data.model.getData type will be to text/plain using setType() method*/
            data = Uri.parse("mailto:")
            type = "application/image"

            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            //put the Subject in the intent
            putExtra(Intent.EXTRA_SUBJECT, "$idUser $eye_dir")
            putExtra(Intent.EXTRA_STREAM, uri)

            //put the message in the intent
            putExtra(Intent.EXTRA_TEXT, "$idUser $eye_dir")
        }



        try {
            //start email intent
            startActivityForResult(Intent.createChooser(mIntent, chose), SEND_EMAIL_CODE)


        } catch (e: Exception) {
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Log.v("errror", e.message)
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}

