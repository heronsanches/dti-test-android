package heronsanches.dti.model.retrofit

import heronsanches.dti.model.retrofit.gson.DefaultAnswerGson
import heronsanches.dti.view.databinding.observable.UserObservable
import retrofit2.Call
import retrofit2.http.*

interface DtiApi {
    @POST("auth")
    fun auth(@Body user: UserObservable): Call<DefaultAnswerGson>

    @POST("user")
    fun registerUser(@Body user: UserObservable): Call<DefaultAnswerGson>
}