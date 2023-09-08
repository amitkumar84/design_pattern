package com.amitsah.designpatterns.behaviour.strategy

interface DriveStrategy {
    fun drive()
}

class BasicDriveStrategy: DriveStrategy {
    override fun drive() {
        println("Basic Driving Capabilities")
    }
}

class SportsDriveStrategy: DriveStrategy{
    override fun drive() {
        println("Sports Driving Capabilities")
    }
}

abstract class Vehicle(private val driveStrategy: DriveStrategy){
    fun drive(){
        driveStrategy.drive()
    }
}

class GoodSVehicle(driveStrategy: DriveStrategy): Vehicle(driveStrategy){
}

class OffRoadVehicle(driveStrategy: DriveStrategy):Vehicle(driveStrategy = driveStrategy){

}

class SportsVehicle(driveStrategy: DriveStrategy):Vehicle(driveStrategy = driveStrategy){

}

fun main(){
    var vehicle:Vehicle = GoodSVehicle(BasicDriveStrategy())
    vehicle.drive()

    vehicle = OffRoadVehicle(SportsDriveStrategy())
    vehicle.drive()

    vehicle = SportsVehicle(SportsDriveStrategy())
    vehicle.drive()
}