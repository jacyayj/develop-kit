package com.jacy.kit.net

import java.lang.annotation.Inherited

@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
annotation class Params constructor(val url:Array<String>,val name: String)
