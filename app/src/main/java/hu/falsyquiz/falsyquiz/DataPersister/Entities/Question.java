package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Entity for questions.
 */

@AllArgsConstructor
@Entity (generateGettersSetters = false, generateConstructors = false)
public class Question {

    public static final String OPTION_A = "A";
    public static final String OPTION_B = "B";
    public static final String OPTION_C = "C";
    public static final String OPTION_D = "D";

    @Id
    @NotNull
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String question;

    @NotNull
    @Getter
    @Setter
    private String optionA;

    @NotNull
    @Getter
    @Setter
    private String optionB;

    @NotNull
    @Getter
    @Setter
    private String optionC;

    @NotNull
    @Getter
    @Setter
    private String optionD;

    @NotNull
    @Getter
    @Setter
    private String answer;

    public Question() {
    }

}
