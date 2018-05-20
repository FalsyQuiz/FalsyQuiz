package hu.falsyquiz.falsyquiz.Moduls;

import android.content.Context;
import org.greenrobot.greendao.database.Database;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.DaoMaster;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.DaoSession;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers.DataManager;
import hu.falsyquiz.falsyquiz.DataPersister.Entities.Managers.DatabaseManager;

/**
 * This module class is responsible for providing the classes used to manage the database.
 */

@Module(includes = ApplicationModule.class)
public class StorageModule {

    private static final String DB_NAME = "FalsyQuiz-db";

    /**
     * This function provides the DaoSession.
     */
    @Provides
    @Singleton
    public DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();

        /*DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);*/

        return new DaoMaster(db).newSession();
    }

    /**
     * This function provides the DatabaseManager.
     */
    @Provides
    @Singleton
    public DatabaseManager provideDatabaseManager(DaoSession daoSession) {
        return new DatabaseManager(daoSession);
    }

    /**
     * This function provides the DataManager.
     */
    @Provides
    @Singleton
    public DataManager provideDataManager(DatabaseManager databaseManager) {
        return new DataManager(databaseManager);
    }

}
