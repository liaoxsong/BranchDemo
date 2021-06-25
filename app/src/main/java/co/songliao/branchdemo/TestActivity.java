package co.songliao.branchdemo;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

/**
 * created by Liao Song on 6/25/21
 */
public class TestActivity extends AppCompatActivity {

    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener() {
        @Override
        public void onInitFinished(JSONObject linkProperties, BranchError error) {
            // do stuff with deep link data (nav to page, display content, etc)
            startActivity(new Intent(TestActivity.this, OtherActivity.class));
        }
    };
}
