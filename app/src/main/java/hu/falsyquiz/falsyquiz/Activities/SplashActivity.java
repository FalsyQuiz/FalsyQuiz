package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import hu.falsyquiz.falsyquiz.R;

public class SplashActivity extends AbstractActivity {

    private static final int SPLASH_SCREEN_DELAY = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeActivityFullScreen();
        setContentView(R.layout.activity_splash);
    }

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
