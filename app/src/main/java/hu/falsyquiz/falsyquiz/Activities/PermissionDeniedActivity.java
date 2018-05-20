package hu.falsyquiz.falsyquiz.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hu.falsyquiz.falsyquiz.R;

public class PermissionDeniedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_denied);
    }
}
