package org.meruvian.midas.injection.module;

import org.meruvian.midas.injection.activity.NewsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by meruvian on 11/04/16.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface DaoComponent {
    void inject(NewsActivity activity);

}
