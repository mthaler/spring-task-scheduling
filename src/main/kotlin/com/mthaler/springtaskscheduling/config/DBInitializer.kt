package com.mthaler.springtaskscheduling.config

import com.mthaler.springtaskscheduling.entities.Car
import com.mthaler.springtaskscheduling.repos.CarRepository
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct

@Service
class DBInitializer {

    private val logger: Logger = LoggerFactory.getLogger(DBInitializer::class.java)

    @Autowired
    lateinit var carRepository: CarRepository

    @PostConstruct
    fun initDB() {
        logger.info("Starting database initialization...")
        val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        var car = Car()
        car.licensePlate = "GRAVITY-0405"
        car.manufacturer = "Ford"
        car.manufactureDate = DateTime.parse("2006-09-12", formatter)
        carRepository.save(car)
        car = Car()
        car.licensePlate = "CLARITY-0432"
        car.manufacturer = "Toyota"
        car.manufactureDate = DateTime.parse("2003-09-09", formatter)
        carRepository.save(car)
        car = Car()
        car.licensePlate = "ROSIE-0402"
        car.manufacturer = "Toyota"
        car.manufactureDate = DateTime.parse("2017-04-16", formatter)
        carRepository.save(car)
        logger.info("Database initialization finished.")
    }
}