package com.agilysys.qa.gridIron.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestCase {

	String id() default "";
	
	String[] ids() default "";
}
