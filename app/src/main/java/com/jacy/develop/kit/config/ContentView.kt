package com.jwell56.usteel.annotation

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Inherited
annotation class ContentView constructor(val layoutId: Int)
