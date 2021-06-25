package com.mthaler.springtaskscheduling.services

import com.mthaler.springtaskscheduling.entities.Car

interface CarService {

    fun findAll(): List<Car>
    fun save(car: Car): Car
    fun updateCarAgeJob()
    fun isDone(): Boolean
}