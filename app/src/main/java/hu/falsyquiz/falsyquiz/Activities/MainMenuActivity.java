package hu.falsyquiz.falsyquiz.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Configuration;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Question;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Utils.QuestionInitializer;
import hu.falsyquiz.falsyquiz.R;

public class MainMenuActivity extends AbstractActivity{

    public static final boolean MENU_ITEM_CHECKED = true;

    public static final int REQUEST_CODE = 250;

    private DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    /**
     * This method creates a MainMenuActivity, sets the toolbar, home button, initializes the
     * main menu's event listeners and initializes questions if you run the application for the
     * first time.
     * @param savedInstanceState
     */

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

    /**
     * This method initializes the game's questions.
     */
    private void initQuestions() {
        for (Question question : QuestionInitializer.getDefaultQuestions()) {
            dataManager.createQuestion(question);
        }
    }

    /**
     * This method initializes the NavigationItemSelectedListener.
     */
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
                    case R.id.mainMenuActivity_help:
                        showHelpActivity();
                        break;
                    case R.id.mainMenuActivity_results:
                        showResultsActivity();
                        break;
                    }

                return true;
            }
        });
    }

    /**
     * This method deletes all of the questions from the database.
     */
    private void clearQuestions() {
        dataManager.deleteAllQuestions();
    }

    /**
     * This method starts a QuestionActivity.
     */
    private void showQuestionActivity() {
        startActivity(new Intent(MainMenuActivity.this, QuestionActivity.class));
    }

    /**
     * This method starts a HelpActivity.
     */
    private void showHelpActivity() {
        startActivity(new Intent(MainMenuActivity.this, HelpActivity.class));
    }

    /**
     * This method starts a ResultActivity.
     */
    private void showResultsActivity() {
        startActivity(new Intent(MainMenuActivity.this, ResultsActivity.class));
    }

    /**
     * This function opens the NavigationDrawer when the user tap on the home button.
     * @param item The currently selected item.
     */
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
