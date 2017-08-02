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

package com.kotlin.alexwan.mvpkoltin.presenter;

import com.kotlin.alexwan.mvpkoltin.internal.di.PerActivity;
import com.kotlin.alexwan.mvpkoltin.mapper.UserModelDataMapper;
import com.kotlin.alexwan.mvpkoltin.view.interaction.UserListView;

import javax.inject.Inject;

/**
 * Created by alexwan on 31/07/2017.
 */

@PerActivity
public class UserListPresenter implements Presenter {

    private UserListView mUserListView;

    private final UserModelDataMapper mModelMapper;

    @Inject
    UserListPresenter(UserModelDataMapper mModelMapper) {
        this.mModelMapper = mModelMapper;
    }

    public void setView(UserListView listView){
        this.mUserListView = listView;
    }

    public void initialize(){

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
