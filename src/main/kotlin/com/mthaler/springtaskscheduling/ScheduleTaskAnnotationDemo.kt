package com.mthaler.springtaskscheduling

import com.mthaler.springtaskscheduling.config.AppConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericApplicationContext

fun main(args: Array<String>) {
    val ctx: GenericApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    System.`in`.read()
    ctx.close()
}