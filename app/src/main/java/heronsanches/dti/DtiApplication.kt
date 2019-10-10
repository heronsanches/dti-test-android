package heronsanches.dti

import android.app.Application
import heronsanches.dti.di.dtiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class DtiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DtiApplication)
            modules(dtiModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}