package hu.falsyquiz.falsyquiz.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Game;
import hu.falsyquiz.falsyquiz.R;

/**
 * Created by Peti on 2018. 04. 29..
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    public interface GameAdapterListener {
        Context getContext();
    }

    private List<Game> games;

    private GameAdapterListener gameAdapterListener;

    public GameAdapter(GameAdapterListener gameAdapterListener, List<Game> games) {
        this.gameAdapterListener = gameAdapterListener;
        this.games = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.resultText.setText(games.get(position).gameOver() ?
                gameAdapterListener.getContext().getString(R.string.gameCard_looseText) :
                gameAdapterListener.getContext().getString(R.string.gameCard_winText));

        holder.numOfQuestionsText.setText(games.get(position).getCorrectAnswers() + "/" + games.get(position).getNumOfQuestions());

        if (games.get(position).getUsedPhone()) {
            holder.phoneIcon.setVisibility(View.VISIBLE);
        } else {
            holder.phoneIcon.setVisibility(View.INVISIBLE);
        }

        if (games.get(position).getUsedFifty()) {
            holder.fiftyIcon.setVisibility(View.VISIBLE);
        } else {
            holder.fiftyIcon.setVisibility(View.INVISIBLE);
        }

        if (games.get(position).getUsedSurprise()) {
            holder.surpriseIcon.setVisibility(View.VISIBLE);
        } else {
            holder.surpriseIcon.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView card;

        @BindView(R.id.gameCard_fiftyIcon)
        ImageView fiftyIcon;

        @BindView(R.id.gameCard_phoneIcon)
        ImageView phoneIcon;

        @BindView(R.id.gameCard_surpriseIcon)
        ImageView surpriseIcon;

        @BindView(R.id.gameCard_result_text)
        TextView resultText;

        @BindView(R.id.gameCard_num_of_questions_text)
        TextView numOfQuestionsText;
        
        public ViewHolder(CardView cardView) {
            super(cardView);
            ButterKnife.bind(this, cardView);
            card = cardView;
        }
    }

}
