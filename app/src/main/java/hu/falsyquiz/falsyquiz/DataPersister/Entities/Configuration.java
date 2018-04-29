package hu.falsyquiz.falsyquiz.DataPersister.Entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Peti on 2018. 03. 24..
 * This class manages the configurations, for example, whether the application was already
 * installed.
 */
@AllArgsConstructor
@Entity(generateGettersSetters = false, generateConstructors = false)
public class Configuration {

    public static final String INSTALLED_KEY = "INSTALLED";
    public static final String INSTALLED_VALUE = "INSTALLED";

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private String value;

    public Configuration() {
    }
}
