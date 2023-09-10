package com.amitsah.designpatterns.low_level_design.parking_lot


class Ticket private constructor(
    var ticketNumber: String? = null,
    var startTime: Long = 0,
    var endTime: Long = 0,
    var vehicle: Vehicle? = null,
    var parkingSlot: ParkingSlot
) {

    companion object {
        fun createTicket(vehicle: Vehicle, parkingSlot: ParkingSlot): Ticket {
            return Ticket(
                ticketNumber = vehicle.vehicleNumber + System.currentTimeMillis(),
                parkingSlot = parkingSlot,
                vehicle = vehicle,
                startTime = System.currentTimeMillis()
            )
        }
    }
}