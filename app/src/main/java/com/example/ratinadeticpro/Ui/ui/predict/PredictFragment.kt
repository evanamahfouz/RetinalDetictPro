package com.example.ratinadeticpro.Ui.ui.predict

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
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
import androidx.navigation.fragment.NavHostFragment
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import kotlinx.android.synthetic.main.predict_fragment.*
import javax.inject.Inject


class PredictFragment : Fragment() {
    private lateinit var uriImg: Uri

    private val chose = "Choose com.example.ratinadeticpro.data.model.Email Client..."
    private val email = "retinaldetection@gmail.com"

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

        go.setOnClickListener {
            val id: Int = radio_group.checkedRadioButtonId
            if (id == R.id.radio_right)
                sendEmail(uriImg, "right")
            else
                sendEmail(uriImg, "lift")

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
            putExtra(Intent.EXTRA_SUBJECT, "predict retina disease")
            putExtra(Intent.EXTRA_STREAM, uri)

            //put the message in the intent
            putExtra(Intent.EXTRA_TEXT, "123 $eye_dir")
        }



        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, chose))

            Log.v("MessageSent", "SentDon")
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_predictFragment_to_resultFragment)

        } catch (e: Exception) {
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Log.v("errror", e.message)
            Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
        }

    }

}

