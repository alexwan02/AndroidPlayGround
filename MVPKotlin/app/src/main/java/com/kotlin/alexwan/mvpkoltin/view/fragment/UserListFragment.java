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

package com.kotlin.alexwan.mvpkoltin.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.kotlin.alexwan.mvpkoltin.internal.di.component.UserComponent;
import com.kotlin.alexwan.mvpkoltin.model.UserModel;
import com.kotlin.alexwan.mvpkoltin.presenter.UserDetailPresenter;
import com.kotlin.alexwan.mvpkoltin.presenter.UserListPresenter;
import com.kotlin.alexwan.mvpkoltin.view.adapter.UserAdapter;
import com.kotlin.alexwan.mvpkoltin.view.interaction.UserListView;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by alexwan on 31/07/2017.
 */

public class UserListFragment extends BaseFragment implements UserListView {

    @Inject
    UserListPresenter mUserListPresenter;

    @Inject
    UserAdapter mUserAdapter;

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void renderUserList(Collection<UserModel> userModels) {

    }

    @Override
    public void viewUser(UserModel userModel) {

    }
}
