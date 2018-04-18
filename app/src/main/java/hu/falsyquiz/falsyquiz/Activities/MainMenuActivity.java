package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Configuration;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Utils.QuestionInitializer;
import hu.falsyquiz.falsyquiz.R;

public class MainMenuActivity extends AbstractActivity {

    @BindView(R.id.mainMenuActivity_startButton)
    Button startButton;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        initOnClickListeners();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        if (menuItem.getTitle().equals(getResources().getString(R.string.mainMenuActivity_startQuizButton)) ) {
                            startActivity(new Intent(MainMenuActivity.this, QuestionActivity.class));
                        }
                        return true;
                    }
                });
        //clearQuestions();

        if (dataManager.getAllQuestions().isEmpty() || dataManager.getConfigurationValue(Configuration.INSTALLED_KEY) == null) {
            initQuestions();
            dataManager.createConfiguration(new Configuration(null, Configuration.INSTALLED_KEY, Configuration.INSTALLED_VALUE));
        }

    }

    private void initOnClickListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, QuestionActivity.class));
            }
        });
    }

    private void initQuestions() {
        for (Question question : QuestionInitializer.getDefaultQuestions()) {
            dataManager.createQuestion(question);
        }
    }

    private void clearQuestions() {
        dataManager.deleteAllQuestions();
    }
}
