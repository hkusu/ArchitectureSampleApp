package io.github.hkusu.architecturesampleapp

import android.content.Context
import androidx.startup.Initializer
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

@Suppress("unused")
class FlipperInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        SoLoader.init(context, false)
        if (FlipperUtils.shouldEnableFlipper(context)) {
            AndroidFlipperClient.getInstance(context).apply {
                addPlugin(SharedPreferencesFlipperPlugin(context))
                addPlugin(DatabasesFlipperPlugin(context))
            }.start()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
