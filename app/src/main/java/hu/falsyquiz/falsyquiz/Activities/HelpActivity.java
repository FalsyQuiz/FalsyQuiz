package hu.falsyquiz.falsyquiz.Activities;

import android.os.Bundle;

import hu.falsyquiz.falsyquiz.R;

/**
 * This activity is responsible for displaying help.
 */
public class HelpActivity extends AbstractActivity {

    /**
     * This method creates a HelpActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}
