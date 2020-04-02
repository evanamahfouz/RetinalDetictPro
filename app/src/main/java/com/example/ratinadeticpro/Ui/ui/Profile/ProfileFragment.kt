package com.example.ratinadeticpro.Ui.ui.Profile


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.Ui.ui.ViewModelFactory.ViewModelFactory
import com.example.ratinadeticpro.databinding.FragmentProfileBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {
    private lateinit var id: String
    private lateinit var viewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_profile,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = sharedPreferences.getString(getString(R.string.id_user_key), "")!!
        btn_goDash.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_profileFragment_to_dashBoradFrag)
        }
        viewModel =
            ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java).also {

                it.getPost(
                    id
                )
            }
        viewModel.mutableList.observe(viewLifecycleOwner, Observer {

            binding.userProfile = it
        })

        viewModel.mutableError.observe(viewLifecycleOwner, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                Toast.makeText(activity, errorLabel, Toast.LENGTH_LONG).show()

            }
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}
