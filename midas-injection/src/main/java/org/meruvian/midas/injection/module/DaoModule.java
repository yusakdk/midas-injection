package org.meruvian.midas.injection.module;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.meruvian.midas.injection.repository.db.DaoMaster;
import org.meruvian.midas.injection.repository.db.DaoSession;
import org.meruvian.midas.injection.repository.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by meruvian on 11/04/16.
 */
@Module
public class DaoModule {
    private Application application;

    public DaoModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DaoMaster daoMaster() {
        DaoMaster.OpenHelper helper = new DaoMaster.OpenHelper(application, "midas", null) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };

        return new DaoMaster(helper.getWritableDatabase());
    }

    @Provides
    @Singleton
    DaoSession daoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    NewsDao newsDao(DaoSession daoSession) {
        return daoSession.getNewsDao();
    }

}
