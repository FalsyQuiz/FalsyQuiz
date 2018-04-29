package hu.falsyquiz.falsyquiz.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import hu.falsyquiz.falsyquiz.R;

/**
 * This activity is responsible for displaying the results.
 */
public class ResultsActivity extends AppCompatActivity {

    /**
     * This method creates a ResultActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}
