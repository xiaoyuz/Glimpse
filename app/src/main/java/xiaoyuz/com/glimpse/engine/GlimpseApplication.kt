package xiaoyuz.com.glimpse.engine

import android.app.Application
import xiaoyuz.com.glimpse.utils.reducedUUID

class GlimpseApplication : Application() {

    companion object {
        val DEVICE_ID = reducedUUID()
    }

    override fun onCreate() {
        super.onCreate()
    }
}