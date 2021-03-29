package io.github.hkusu.architecturesampleapp

import android.content.Context
import androidx.startup.Initializer
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
class FlipperInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(context, HiltEntryPoint::class.java)
        SoLoader.init(context, false)
        if (FlipperUtils.shouldEnableFlipper(context)) {
            AndroidFlipperClient.getInstance(context).apply {
                // addPlugin(SharedPreferencesFlipperPlugin(context)) 現状ではDataStoreの保存ファイルのパスに対応していない
                addPlugin(DatabasesFlipperPlugin(context))
                addPlugin(hiltEntryPoint.networkFlipperPlugin())
            }.start()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltEntryPoint {
        fun networkFlipperPlugin(): NetworkFlipperPlugin
    }
}
