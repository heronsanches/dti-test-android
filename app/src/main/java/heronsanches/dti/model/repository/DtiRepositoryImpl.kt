package heronsanches.dti.model.repository

import android.content.Context
import heronsanches.dti.R
import heronsanches.dti.model.retrofit.DtiApi
import heronsanches.dti.model.retrofit.gson.DefaultAnswerGson
import heronsanches.dti.view.databinding.observable.UserObservable
import java.io.IOException

class DtiRepositoryImpl(private val dtiApi: DtiApi, private val context: Context) : DtiRepository {
    // TODO: code replication here
    override fun auth(user: UserObservable): DefaultAnswerGson {
        return try {
            val answer = dtiApi.auth(user).execute()

            if (answer.isSuccessful) {
                answer.body() as DefaultAnswerGson
            } else {
                DefaultAnswerGson(false, context.getString(R.string.error_login))
            }
        } catch (e: IOException) {
            DefaultAnswerGson(false, context.getString(R.string.error_server_connection))
        } catch (e: RuntimeException) {
            DefaultAnswerGson(false, context.getString(R.string.error_server_runtime))
        }
    }

    override fun registerUser(user: UserObservable): DefaultAnswerGson {
        return try {
            val answer = dtiApi.registerUser(user).execute()

            if (answer.isSuccessful) {
                answer.body() as DefaultAnswerGson
            } else {
                DefaultAnswerGson(false, context.getString(R.string.error_register))
            }
        } catch (e: IOException) {
            DefaultAnswerGson(false, context.getString(R.string.error_server_connection))
        } catch (e: RuntimeException) {
            DefaultAnswerGson(false, context.getString(R.string.error_server_runtime))
        }
    }
}