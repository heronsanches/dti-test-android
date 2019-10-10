package heronsanches.dti.di

import android.content.Context
import com.google.gson.GsonBuilder
import heronsanches.dti.R
import heronsanches.dti.model.repository.DtiRepository
import heronsanches.dti.model.repository.DtiRepositoryImpl
import heronsanches.dti.model.retrofit.DtiApi
import heronsanches.dti.viewmodel.LoginViewModel
import heronsanches.dti.viewmodel.UserRegisterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dtiModule = module {
    single { this }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder().setLenient().create()
        val context = get() as Context

        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.dti_api))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        retrofit.create(DtiApi::class.java) as DtiApi
    }
    single { DtiRepositoryImpl(dtiApi = get(), context = get()) as DtiRepository }
    viewModel { LoginViewModel(dtiRepository = get()) }
    viewModel { UserRegisterViewModel(dtiRepository = get()) }
}