package com.example.kushsingh.heady_app.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ambab on 31/7/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
