package com.chatlist.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.chatlist.R
import com.chatlist.data.PreferenceRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var userNameEt: TextInputEditText
    private lateinit var userNameTil: TextInputLayout
    private lateinit var btn: Button

    @Inject
    lateinit var spRepository: PreferenceRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn = requireView().findViewById(R.id.start_chat_btn)
        userNameEt = requireView().findViewById(R.id.username_et)
        userNameTil = requireView().findViewById(R.id.username_til)

        btn.setOnClickListener {
            if (isValid()) {
                spRepository.name = userNameEt.text.toString()
                findNavController().navigate(R.id.chatFragment)
            }
        }
    }

    private fun isValid(): Boolean {
        if (userNameEt.text.isNullOrEmpty()) {
            userNameTil.error = getString(R.string.username_validation)
            return false
        } else if (userNameEt.text!!.length < 3) {
            userNameTil.error = getString(R.string.min_validation)
            return false
        } else {
            return true
        }
    }

}