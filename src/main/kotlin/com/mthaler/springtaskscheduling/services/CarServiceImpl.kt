package com.mthaler.springtaskscheduling.services

import com.mthaler.springtaskscheduling.entities.Car
import com.mthaler.springtaskscheduling.repos.CarRepository
import org.joda.time.DateTime
import org.joda.time.Years
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer


@Service("carService")
@Repository
@Transactional
class CarServiceImpl: CarService {

    var done = false

    @Autowired
    lateinit var carRepository: CarRepository

    @Transactional(readOnly = true)
    override fun findAll(): List<Car> {
        return carRepository.findAll().toList()
    }

    override fun save(car: Car): Car {
        return carRepository.save(car)
    }

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
        done = true
    }

    override fun isDone(): Boolean {
        return done
    }

    companion object {

        val logger = LoggerFactory.getLogger(CarServiceImpl::class.java)

    }
}