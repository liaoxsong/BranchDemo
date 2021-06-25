package co.songliao.branchdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * created by Liao Song on 6/25/21
 */
class OtherActivity: AppCompatActivity() {

    companion object {
        val KEY_DIRECT = "other"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
    }


}