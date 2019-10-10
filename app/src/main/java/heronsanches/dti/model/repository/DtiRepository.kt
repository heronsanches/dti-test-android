package heronsanches.dti.model.repository

import heronsanches.dti.model.retrofit.gson.DefaultAnswerGson
import heronsanches.dti.view.databinding.observable.UserObservable

interface DtiRepository {
    fun auth(user: UserObservable): DefaultAnswerGson
    fun registerUser(user: UserObservable): DefaultAnswerGson
}