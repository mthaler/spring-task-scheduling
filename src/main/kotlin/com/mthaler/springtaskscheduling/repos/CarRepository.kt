package com.mthaler.springtaskscheduling.repos

import com.mthaler.springtaskscheduling.entities.Car
import org.springframework.data.repository.CrudRepository

interface CarRepository: CrudRepository<Car, Long>