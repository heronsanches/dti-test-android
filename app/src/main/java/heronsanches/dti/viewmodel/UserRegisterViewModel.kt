package heronsanches.dti.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import heronsanches.dti.model.repository.DtiRepository
import heronsanches.dti.model.retrofit.gson.DefaultAnswerGson
import heronsanches.dti.view.databinding.observable.UserObservable
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserRegisterViewModel(private val dtiRepository: DtiRepository) : ViewModel(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun userRegister(user: UserObservable): LiveData<DefaultAnswerGson> {
        val defaultAnswer= MutableLiveData<DefaultAnswerGson>()
        Log.d("hsgpf", "login before registered: " + defaultAnswer.value)

        launch {
            defaultAnswer.value = withContext(Dispatchers.IO) {
                dtiRepository.registerUser(user)
            }
        }
        Log.d("hsgpf", "login after registered: " + defaultAnswer.value)
        return defaultAnswer
    }
}