package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainMenuActivity extends AbstractActivity{

    public static final boolean MENU_ITEM_CHECKED = true;

    private DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = findViewById(R.id.drawer_layout);

        initMainMenuEventListeners();

        if (dataManager.getAllQuestions().isEmpty() || dataManager.getConfigurationValue(Configuration.INSTALLED_KEY) == null) {
            initQuestions();
            dataManager.createConfiguration(new Configuration(null, Configuration.INSTALLED_KEY, Configuration.INSTALLED_VALUE));
        }

    }

    private void initQuestions() {
        for (Question question : QuestionInitializer.getDefaultQuestions()) {
            dataManager.createQuestion(question);
        }
    }

    private void initMainMenuEventListeners() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                item.setChecked(MENU_ITEM_CHECKED);
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.mainMenuActivity_start:
                        showQuestionActivity();
                        break;
                    }

                return true;
            }
        });
    }

    private void clearQuestions() {
        dataManager.deleteAllQuestions();
    }

    private void showQuestionActivity() {
        startActivity(new Intent(MainMenuActivity.this, QuestionActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
