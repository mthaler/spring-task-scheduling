package com.mthaler.springtaskscheduling.entities

import org.hibernate.annotations.Type
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
@Table(name="car")
class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "LICENSE_PLATE")
    var licensePlate: String? = null

    @Column(name = "MANUFACTURER")
    var manufacturer: String? = null

    @Column(name = "MANUFACTURE_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    var manufactureDate: DateTime? = null

    @Column(name = "AGE")
    var age = 0

    @Version
    var version = 0

    override fun toString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return String.format(
            "{License: %s, Manufacturer: %s, Manufacture Date: %s, Age: %d}",
            licensePlate, manufacturer, sdf.format(manufactureDate!!.toDate()), age
        )
    }
}