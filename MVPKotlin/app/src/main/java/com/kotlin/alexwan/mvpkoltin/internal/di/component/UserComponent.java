package com.kotlin.alexwan.mvpkoltin.internal.di.component;

import com.kotlin.alexwan.mvpkoltin.internal.di.PerActivity;
import com.kotlin.alexwan.mvpkoltin.internal.di.modules.ActivityModule;
import com.kotlin.alexwan.mvpkoltin.internal.di.modules.UserModule;
import com.kotlin.alexwan.mvpkoltin.view.fragment.BaseFragment;
import com.kotlin.alexwan.mvpkoltin.view.fragment.UserDetailFragment;
import com.kotlin.alexwan.mvpkoltin.view.fragment.UserListFragment;

import dagger.Component;

/**
 * Created by alexwan on 31/07/2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {

    void inject(UserListFragment userListFragment);

    void inject(UserDetailFragment userDetailsFragment);

}
