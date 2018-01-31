package com.example.kushsingh.heady_app.data;

import com.example.kushsingh.heady_app.injection.ApplicationComponent;
import com.example.kushsingh.heady_app.injection.ApplicationScoped;

import dagger.Component;

/**
 * Created by Kush on 27/7/17.
 */
@ApplicationScoped
@Component(dependencies = ApplicationComponent.class, modules = DataRespositoryModule.class)
public interface DataRepositoryComponent {

}
