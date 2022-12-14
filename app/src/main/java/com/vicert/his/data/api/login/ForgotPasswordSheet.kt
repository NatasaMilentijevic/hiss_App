package com.vicert.his.data.api.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vicert.his.databinding.FragmentForgotPasswordSheetBinding
import com.vicert.his.presentation.login.LoginActivityViewModel

class ForgotPasswordSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentForgotPasswordSheetBinding
    private lateinit var forgotViewModel : LoginActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        forgotViewModel = ViewModelProvider(activity).get(LoginActivityViewModel::class.java)

        binding.buttonSave.setOnClickListener{
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction(){
        forgotViewModel.oldPassword.value = binding.tvOldPassword.text.toString()
        forgotViewModel.newPassword.value = binding.tvNewPassword.text.toString()
        binding.tvOldPassword.setText("")
        binding.tvNewPassword.setText("")
        dismiss()
    }

}