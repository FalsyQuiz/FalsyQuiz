package hu.falsyquiz.falsyquiz.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.Adapters.GameAdapter;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.R;

/**
 * This activity is responsible for displaying the results.
 */
public class ResultsActivity extends AbstractActivity implements GameAdapter.GameAdapterListener {

    @BindView(R.id.resultsActivity_games_recyclerView)
    RecyclerView gamesRecyclerView;

    private GameAdapter gameAdapter;

    /**
     * This method creates a ResultActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameAdapter = new GameAdapter(this, dataManager.getAllGames());
        gamesRecyclerView.setAdapter(gameAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
