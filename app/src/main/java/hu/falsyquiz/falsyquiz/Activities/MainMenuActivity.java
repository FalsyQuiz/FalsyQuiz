package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.R;

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.mainMenuActivity_startButton)
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        initOnClickListeners();
    }

    private void initOnClickListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, QuestionActivity.class));
            }
        });
    }
}
