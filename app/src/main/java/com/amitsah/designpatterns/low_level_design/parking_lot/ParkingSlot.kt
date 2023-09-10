package com.amitsah.designpatterns.low_level_design.parking_lot

class ParkingSlot(var name: String, var parkingSlotType: ParkingSlotType) {
    var isAvailable = true
    var vehicle: Vehicle? = null
    fun addVehicle(vehicle: Vehicle?) {
        this.vehicle = vehicle
        isAvailable = false
    }

    fun removeVehicle(vehicle: Vehicle?) {
        this.vehicle = null
        isAvailable = true
    }
}