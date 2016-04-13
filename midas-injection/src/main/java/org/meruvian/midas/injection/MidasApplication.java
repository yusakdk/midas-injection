package org.meruvian.midas.injection;

import android.app.Application;

import org.meruvian.midas.injection.module.DaggerDaoComponent;
import org.meruvian.midas.injection.module.DaoComponent;
import org.meruvian.midas.injection.module.DaoModule;

/**
 * Created by meruvian on 11/04/16.
 */
public class MidasApplication extends Application {
    private DaoComponent daoComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        daoComponent = DaggerDaoComponent.builder().daoModule(new DaoModule(this)).build();

    }

    public DaoComponent getDaoComponent() {
        return daoComponent;
    }
}
