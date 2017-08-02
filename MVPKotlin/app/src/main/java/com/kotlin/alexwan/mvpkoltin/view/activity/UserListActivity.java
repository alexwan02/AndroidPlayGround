/*
 * Copyright (C) 2015 Alex Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.kotlin.alexwan.mvpkoltin.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kotlin.alexwan.mvpkoltin.R;
import com.kotlin.alexwan.mvpkoltin.internal.di.HasComponent;
import com.kotlin.alexwan.mvpkoltin.internal.di.component.DaggerUserComponent;
import com.kotlin.alexwan.mvpkoltin.internal.di.component.UserComponent;
import com.kotlin.alexwan.mvpkoltin.model.UserModel;
import com.kotlin.alexwan.mvpkoltin.view.fragment.UserListFragment;

/**
 * Created by alexwan on 31/07/2017.
 */

public class UserListActivity extends BaseActivity implements HasComponent<UserComponent>, UserListFragment.UserListListener {

    private UserComponent mUserComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initializeInjector();
        addFragment(R.id.container, new UserListFragment());
    }

    private void initializeInjector() {
        this.mUserComponent = DaggerUserComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public UserComponent getComponent() {
        return mUserComponent;
    }

    @Override
    public void onUserClicked(final UserModel userModel) {
        mNavigator.navigateToUserDetail(this, userModel.getUserId());
    }
}
