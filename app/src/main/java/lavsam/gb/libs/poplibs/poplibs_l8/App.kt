package lavsam.gb.libs.poplibs.poplibs_l8

import android.app.Application
import com.facebook.stetho.Stetho
import lavsam.gb.libs.poplibs.poplibs_l8.di.AppComponent
import lavsam.gb.libs.poplibs.poplibs_l8.di.AppModule
import lavsam.gb.libs.poplibs.poplibs_l8.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        Stetho.initializeWithDefaults(this)
    }
}