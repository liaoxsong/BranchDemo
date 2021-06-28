package co.songliao.branchdemo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.songliao.branchdemo.entity.DemoObject
import com.bumptech.glide.Glide
import com.google.gson.Gson

/**
 * created by Liao Song on 6/25/21
 */
class OtherActivity : AppCompatActivity() {

    companion object {
        val KEY_DIRECT = "other"

        val KEY_CONTENT = "content"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        val titleText = findViewById<TextView>(R.id.tv_title)
        val subtitleText = findViewById<TextView>(R.id.tv_subtitle)
        val imageView = findViewById<ImageView>(R.id.iv_other)

        intent?.getStringExtra(KEY_CONTENT)?.let {
            val demoObj = Gson().fromJson(it, DemoObject::class.java)
            titleText.text = demoObj.title
            subtitleText.text = demoObj.subtitle
            if (!demoObj.imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(demoObj.imageUrl).into(imageView)
            }
        }
    }


}