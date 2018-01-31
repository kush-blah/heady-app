package com.example.kushsingh.heady_app.data;

import com.example.kushsingh.heady_app.data.network.DataSource;

import javax.inject.Inject;

public class HeadyDataRepository implements DataSource {
    private DataSource localDataSource, networkDataSource;

    @Inject
    public HeadyDataRepository(@Local DataSource localDataSource, @Remote DataSource networkDataSource) {
        this.localDataSource = localDataSource;
        this.networkDataSource = networkDataSource;
    }

}
