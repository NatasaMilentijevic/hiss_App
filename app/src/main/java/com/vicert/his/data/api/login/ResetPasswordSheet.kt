package com.vicert.his.data.api.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vicert.his.databinding.FragmentResetPasswordSheetBinding
import com.vicert.his.presentation.login.LoginActivityViewModel

class ResetPasswordSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentResetPasswordSheetBinding
    private lateinit var forgotViewModel : LoginActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        forgotViewModel = ViewModelProvider(activity).get(LoginActivityViewModel::class.java)

        binding.btnResetSave.setOnClickListener{
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction(){
        forgotViewModel.email.value = binding.tvResetEmail.text.toString()
        forgotViewModel.oldPassword.value = binding.tvResetOldPassword.text.toString()
        forgotViewModel.newPassword.value = binding.tvResetNewPassword.text.toString()
        forgotViewModel.confirmNewPassword.value = binding.tvResetConfirmNewPassword.text.toString()

        binding.tiResetEmail.setText("")
        binding.tiResetOldPassword.setText("")
        binding.tiResetNewPassword.setText("")
        binding.tiConfirmPassword.setText("")
        dismiss()
    }

}