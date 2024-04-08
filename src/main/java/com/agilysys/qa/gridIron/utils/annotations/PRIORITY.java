package com.agilysys.qa.gridIron.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PRIORITY {

	String priority() default "TBD";
}
