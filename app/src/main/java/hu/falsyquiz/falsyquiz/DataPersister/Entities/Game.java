package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Peti on 2018. 03. 24..
 */


@Entity (generateConstructors = false, generateGettersSetters = false)
public class Game {

    public static final int LIVES_LOWER_BOUND = 1;

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private int numOfQuestions;

    @Getter
    @Setter
    private int correctAnswers;

    @Getter
    @Setter
    private Boolean usedFifty;

    @Getter
    @Setter
    private Boolean usedPhone;

    @Getter
    @Setter
    private Boolean usedSurprise;

    @Getter
    @Setter
    private int lives;

    public Game() {
    }

    public boolean gameOver() {
        return lives < LIVES_LOWER_BOUND;
    }
}
