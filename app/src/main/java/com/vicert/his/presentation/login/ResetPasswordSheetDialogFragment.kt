package com.vicert.his.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vicert.his.HisApplication
import com.vicert.his.databinding.FragmentResetPasswordSheetBinding
import com.vicert.his.presentation.base.ViewModelFactory
import com.vicert.his.utils.toast
import javax.inject.Inject

class ResetPasswordSheetDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentResetPasswordSheetBinding

    @Inject
    lateinit var resetPasswordFactory : ViewModelFactory<LoginActivityViewModel>
    private val viewModel: LoginActivityViewModel by viewModels { resetPasswordFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as HisApplication).getMainComponent().inject(this)


        viewModel.resetResult.observe(this){
            when (it) {
                is ResetState.LoadingState -> {
                    showLoading()
                }
                is ResetState.SuccessState -> {
                    stopLoading()
                    dismiss()
                }
                is ResetState.FailState -> {
                    requireContext().toast(it.msg)
                    stopLoading()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResetPassword.setOnClickListener{
            val email = binding.etEmailAddress.text.toString().trim()
            val oldPass = binding.etOldPassword.text.toString().trim()
            val newPass =  binding.etNewPassword.text.toString().trim()
            val confPass =  binding.etConfirmNewPassword.text.toString().trim()
            viewModel.resetPassword(email = email, oldPassword = oldPass, newPassword = newPass, confirmNewPassword = confPass )
        }
    }
    private fun showLoading() {
        binding.pgReset.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.pgReset.visibility = View.GONE
    }


}