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

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
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

    private void initQuestions() {
        for (Question question : QuestionInitializer.getDefaultQuestions()) {
            dataManager.createQuestion(question);
        }
    }

    private void clearQuestions() {
        dataManager.deleteAllQuestions();
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
