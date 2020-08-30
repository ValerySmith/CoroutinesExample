package valerysmith.com.coroutinesexample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import valerysmith.com.coroutinesexample.components.screenModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initKoin()
    }

    private fun initLogger() {
        Timber.plant(
            //     if (BuildConfig.DEBUG) Timber.DebugTree() else CrashlyticsCrashReportingTree()
        )
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            fragmentFactory()
            modules(
                listOf(
                    appModule,
                    screenModule
                    /*  repositoryModule,
                      databaseModule,
                      cryptoSharedPreferencesModule,
                      prayerModule,
                      passcodeModule*/
                )
            )
        }
    }
}