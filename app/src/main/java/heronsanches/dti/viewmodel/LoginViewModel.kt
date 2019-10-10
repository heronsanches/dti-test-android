package heronsanches.dti.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import heronsanches.dti.model.repository.DtiRepository
import heronsanches.dti.model.retrofit.gson.DefaultAnswerGson
import heronsanches.dti.view.databinding.observable.UserObservable
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel(private val dtiRepository: DtiRepository) : ViewModel(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun login(user: UserObservable): LiveData<DefaultAnswerGson> {
        var defaultAnswer = MutableLiveData<DefaultAnswerGson>()

        launch {
            defaultAnswer.value = withContext(Dispatchers.IO) {
                dtiRepository.auth(user)
            }
        }
        return defaultAnswer
    }
}