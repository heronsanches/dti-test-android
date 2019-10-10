package heronsanches.dti.view.validator

import android.content.Context
import android.util.Patterns
import heronsanches.dti.R
import heronsanches.dti.databinding.ActivityRegisterBinding
import heronsanches.dti.view.databinding.observable.UserObservable

class RegisterValidator(private val registerBinding: ActivityRegisterBinding) {
    private val context: Context = registerBinding.tietEmail.context
    private val user: UserObservable? = registerBinding.user

    fun validate(): Boolean {
        val validation = mutableListOf<Boolean>()
        validation.add(checkEmail())
        validation.add(checkPassword())
        return !validation.contains(false)
    }

    private fun checkEmail(): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(user?.email).matches()) {
            val errorMessage = context.getString(R.string.error_email_invalid)
            registerBinding.tilEmail.error = errorMessage
            false
        } else {
            registerBinding.tilEmail.error = null
            true
        }
    }

    private fun checkPassword(): Boolean {
        return if (user?.password?.length !in 6..28) {
            val errorMessage = context.getString(R.string.error_password_invalid)
            registerBinding.tilPassword.error = errorMessage
            false
        } else {
            registerBinding.tilPassword.error = null
            true
        }
    }
}