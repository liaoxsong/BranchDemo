package co.songliao.branchdemo

import android.app.Application
import io.branch.referral.Branch

/**
 * created by Liao Song on 6/25/21
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // Branch logging for debugging
        // Branch logging for debugging
        Branch.enableLogging()

        // Branch object initialization

        // Branch object initialization
        Branch.getAutoInstance(this)
    }
}