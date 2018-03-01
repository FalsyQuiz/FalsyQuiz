package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Base class for common methods of activities.
 */

public class AbstractActivity extends AppCompatActivity {

    /**
     * Starts new Activity with specified Intent, and finishes the actual activity.
     * @param intent
     */

    protected void clearAndStartActivity(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * This method makes the Activity to fit the screen.
     */

    protected void makeActivityFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}
