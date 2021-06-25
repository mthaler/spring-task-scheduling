package com.mthaler.springtaskscheduling.services

import com.mthaler.springtaskscheduling.entities.Car
import org.joda.time.DateTime
import org.joda.time.Years
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer

@Service("scheduledCarService")
@Repository
@Transactional
class ScheduledCarServiceImpl: CarServiceImpl() {

    @Scheduled(fixedDelay = 10000)
    override fun updateCarAgeJob() {
        val cars = findAll()
        val currentDate = DateTime.now()
        logger.info("Car age update job started")
        cars.forEach(Consumer { car: Car ->
            val age = Years.yearsBetween(car.manufactureDate, currentDate).years
            car.age = age
            save(car)
            logger.info("Car age update --> $car")
        })
        logger.info("Car age update job completed successfully")
    }
}