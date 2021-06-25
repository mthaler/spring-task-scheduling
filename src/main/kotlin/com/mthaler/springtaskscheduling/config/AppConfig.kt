package com.mthaler.springtaskscheduling.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
@Import({DataServiceConfig.class})
@EnableScheduling
class AppConfig {

// decomment this to use the TaskScheduler explicitly declared bean
/*
    @Bean
    fun carScheduler(): TaskScheduler? {
        val carScheduler = ThreadPoolTaskScheduler()
        carScheduler.poolSize = 10
        return carScheduler
    }
 */
}