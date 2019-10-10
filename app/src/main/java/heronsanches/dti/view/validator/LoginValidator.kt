package heronsanches.dti.view.validator

import android.content.Context
import android.util.Patterns
import heronsanches.dti.R
import heronsanches.dti.databinding.ActivityLoginBinding
import heronsanches.dti.view.databinding.observable.UserObservable

class LoginValidator(private val loginBinding: ActivityLoginBinding) {
    private val context: Context = loginBinding.tietEmail.context
    private val user: UserObservable? = loginBinding.user

    fun validate(): Boolean {
        val validation = mutableListOf<Boolean>()
        validation.add(checkEmail())
        validation.add(checkPassword())
        return !validation.contains(false)
    }

    private fun checkEmail(): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(user?.email).matches()) {
            val errorMessage = context.getString(R.string.error_email_invalid)
            loginBinding.tilEmail.setError(errorMessage)
            false
        } else {
            loginBinding.tilEmail.setError(null)
            true
        }
    }

    private fun checkPassword(): Boolean {
        return if (user?.password?.length !in 6..28) {
            val errorMessage = context.getString(R.string.error_password_invalid)
            loginBinding.tilPassword.setError(errorMessage)
            false
        } else {
            loginBinding.tilPassword.setError(null)
            true
        }
    }
}