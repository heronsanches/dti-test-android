package heronsanches.dti.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import heronsanches.dti.R
import heronsanches.dti.databinding.ActivityRegisterBinding
import heronsanches.dti.view.databinding.observable.UserObservable
import heronsanches.dti.view.validator.RegisterValidator
import heronsanches.dti.viewmodel.UserRegisterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)
    }
    private val userRegisterViewModel: UserRegisterViewModel by viewModel()
    private val registerValidator: RegisterValidator by lazy { RegisterValidator(binding) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.user = UserObservable()
        initializeToolbar()

        binding.tietPassword.setOnEditorActionListener { _, actionId, _ ->
            handleEditorAction(actionId)
        }
    }

    fun registerClick(view: View?) {
        if (!registerValidator.validate()) return
        binding.tvError.text = ""
        binding.progressBar.visibility = View.VISIBLE

        userRegisterViewModel.userRegister(binding.user!!).observe(this, Observer { answer ->
            binding.progressBar.visibility = View.GONE

            if (answer.success) onBackPressed()
            else binding.tvError.text = answer.errorMessage
        })
    }

    private fun initializeToolbar() {
        binding.toolbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun handleEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            registerClick(null)
            return true
        }
        return false
    }

    companion object {
        fun open(activity: AppCompatActivity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle()
            ActivityCompat.startActivity(activity, intent, options)
        }
    }
}