package presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import repository.LoginRepository

class LoginViewModelProviderFactory(
    val loginRepository: LoginRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(loginRepository) as T
    }
}