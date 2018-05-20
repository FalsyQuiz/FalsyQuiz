package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Peti on 2018. 03. 24..
 * This class is responsible for representing the games.
 */
@Entity (generateConstructors = false, generateGettersSetters = false)
public class Game implements Parcelable {

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

    /**
     * This function checks whether the game is over.
     */
    public boolean gameOver() {
        return lives < LIVES_LOWER_BOUND;
    }

    /// PARCELABLE INTERFACE

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeInt(numOfQuestions);
        out.writeInt(correctAnswers);
        out.writeInt(usedFifty ? 1 : 0);
        out.writeInt(usedPhone ? 1 : 0);
        out.writeInt(usedSurprise ? 1 : 0);
        out.writeInt(lives);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public Game(Parcel in) {
        id = in.readLong();
        numOfQuestions = in.readInt();
        correctAnswers = in.readInt();
        usedFifty = in.readInt() == 1;
        usedPhone = in.readInt() == 1;
        usedSurprise = in.readInt() == 1;
        lives = in.readInt();
    }
}
