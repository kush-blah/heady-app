package com.example.kushsingh.heady_app.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ambab on 26/7/17.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {
}
