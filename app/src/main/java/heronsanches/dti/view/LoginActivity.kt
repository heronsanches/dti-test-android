package heronsanches.dti.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import heronsanches.dti.R
import heronsanches.dti.databinding.ActivityLoginBinding
import heronsanches.dti.view.databinding.observable.UserObservable
import heronsanches.dti.view.validator.LoginValidator
import heronsanches.dti.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }
    private val loginViewModel: LoginViewModel by viewModel()
    private val loginValidator: LoginValidator by lazy { LoginValidator(binding) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getParcelableExtra<UserObservable>("user")

        if (user != null) binding.user = user
        else binding.user = UserObservable()

        binding.tietPassword.setOnEditorActionListener { _, actionId, _ ->
            handleEditorAction(actionId)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.tvError.text = ""
    }

    fun loginClick(view: View?) {
        if (!loginValidator.validate()) return
        binding.tvError.text = ""
        binding.progressBar.visibility = View.VISIBLE

        loginViewModel.login(binding.user!!).observe(this, Observer { answer ->
            binding.progressBar.visibility = View.GONE

            if (answer.success) WelcomeActivity.open(this, binding.user!!)
            else binding.tvError.text = answer.errorMessage
        })
    }

    fun registerClick(view: View) {
        RegisterActivity.open(this)
    }

    private fun handleEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_GO){
            loginClick(null)
            return true
        }
        return false
    }
}
