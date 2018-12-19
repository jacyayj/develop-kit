package com.jwell56.usteel.annotation

import java.lang.annotation.Inherited

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
annotation class ParamsName constructor(val name: String)
