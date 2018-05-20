package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import hu.falsyquiz.falsyquiz.R;

/**
 * This activity is responsible for the loading animation that appears at the beginning of the app.
 */
public class SplashActivity extends AbstractActivity {

    private static final int SPLASH_SCREEN_DELAY = 3000;

    /**
     * This method creates a SplashActivity and makes the activity full screen.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeActivityFullScreen();
        setContentView(R.layout.activity_splash);
    }

    /**
     * This method calls the method, which starts the timer for the loading screen.
     */
    @Override
    public void onStart() {
        super.onStart();
        waitForSplashAnimation(SPLASH_SCREEN_DELAY);
    }

    /**
     * This method starts the timer for splash screen.
     * @param duration The length of the animation in millisec.
     */

    private void waitForSplashAnimation(int duration) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                clearAndStartActivity(new Intent(SplashActivity.this, MainMenuActivity.class));
            }

        }, duration);
    }
}
