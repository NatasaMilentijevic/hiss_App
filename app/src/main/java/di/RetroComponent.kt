package di

import dagger.Component
import presentation.login.LoginActivity
import presentation.login.LoginActivityViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
class RetroComponent {

    fun inject(loginViewModel: LoginActivityViewModel) {}
}