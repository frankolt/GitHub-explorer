package io.github.frankolt.githubexplorer.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.frankolt.githubexplorer.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class GitHubExplorerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
