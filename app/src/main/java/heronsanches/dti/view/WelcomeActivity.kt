package heronsanches.dti.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import heronsanches.dti.R
import heronsanches.dti.view.databinding.observable.UserObservable
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        intent.getParcelableExtra<UserObservable>(EXTRA_USER)?.let {
            tvWelcome.text = getString(R.string.msg_welcome, it.email, getString(R.string.app_name))
        }
    }

    companion object {
        private const val EXTRA_USER = "user"

        fun open(activity: AppCompatActivity, userObservable: UserObservable) {
            val intent = Intent(activity, WelcomeActivity::class.java).apply {
                putExtra(EXTRA_USER, userObservable)
            }
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle()
            ActivityCompat.startActivity(activity, intent, options)
        }
    }
}