package co.songliao.branchdemo

import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.SharingHelper
import io.branch.referral.util.ContentMetadata
import io.branch.referral.util.LinkProperties
import io.branch.referral.util.ShareSheetStyle
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        val KEY_DEEP_LINK_PARAM = "deep_link_test"
        val BTAG = "Branch_SDK"
    }
    override fun onStart() {
        super.onStart()
        // Branch init
        Branch.sessionBuilder(this).withCallback(branchListener).withData(this.intent?.data).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_share).setOnClickListener {
            val buo = BranchUniversalObject()
                    .setCanonicalIdentifier("content/12345")
                    .setTitle("My Content Title")
                    .setContentDescription("My Content Description")
                    .setContentImageUrl("https://lorempixel.com/400/400")
                    .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                    .setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                    .setContentMetadata(ContentMetadata().addCustomMetadata("key1", "value1"))

            var lp = LinkProperties()
                    .setChannel("facebook")
                    .setFeature("sharing")
                    .setCampaign("content 123 launch")
                    .setStage("new user")
                    //.addControlParameter("$desktop_url", "http://example.com/home")
                    .addControlParameter(KEY_DEEP_LINK_PARAM, OtherActivity.KEY_DIRECT)
                    //.addControlParameter("custom_random", Long.toString(Calendar.getInstance().getTimeInMillis()))

            val ss = ShareSheetStyle(this@MainActivity, "Check this out!", "This stuff is awesome: ")
                    .setCopyUrlStyle(resources.getDrawable(android.R.drawable.ic_menu_send), "Copy", "Added to clipboard")
                    .setMoreOptionStyle(resources.getDrawable( android.R.drawable.ic_menu_search), "Show more")
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
                    .addPreferredSharingOption(SharingHelper.SHARE_WITH.HANGOUT)
                    .setAsFullWidthStyle(true)
                    .setSharingTitle("Share With")

            buo.showShareSheet(this, lp, ss, object : Branch.BranchLinkShareListener {
                override fun onShareLinkDialogLaunched() {}
                override fun onShareLinkDialogDismissed() {}
                override fun onLinkShareResponse(sharedLink: String?, sharedChannel: String?, error: BranchError?) {}
                override fun onChannelSelected(channelName: String) {}
            })
        }
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
        // Branch reinit (in case Activity is already in foreground when Branch link is clicked)
        Branch.sessionBuilder(this).withCallback(branchListener).reInit()
    }
    
    private fun startOther() {
        startActivity(Intent(this, OtherActivity::class.java))
    }

    private val branchListener = Branch.BranchReferralInitListener { referringParams, error ->
        if (error == null) {
            Log.i( BTAG, referringParams.toString())
            val key = referringParams?.optString(KEY_DEEP_LINK_PARAM) ?: ""
            if (key == OtherActivity.KEY_DIRECT) {
                startOther()
            }
        } else {
            Log.e(BTAG, error.message)
        }
    }

}