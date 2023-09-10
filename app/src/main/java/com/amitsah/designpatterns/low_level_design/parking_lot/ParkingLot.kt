package com.amitsah.designpatterns.low_level_design.parking_lot

class ParkingLot private constructor(
    private val nameOfParkingLot: String,
    private val address: Address,
    private val parkingFloors: MutableList<ParkingFloor>
) {
    fun addFloors(name: String, parkSlots: Map<ParkingSlotType, Map<String, ParkingSlot>>) {
        val parkingFloor = ParkingFloor(name, parkSlots)
        parkingFloors.add(parkingFloor)
    }

    fun removeFloors(parkingFloor: ParkingFloor) {
        parkingFloors.remove(parkingFloor)
    }

    fun assignTicket(vehicle: Vehicle): Ticket? {
        val parkingSlot: ParkingSlot = getParkingSlotForVehicleAndPark(vehicle) ?: return null
        return createTicketForSlot(parkingSlot, vehicle)
    }

    fun scanAndPay(ticket: Ticket): Double {
        val endTime = System.currentTimeMillis()
        ticket.parkingSlot.removeVehicle(ticket.vehicle)
        val duration = (endTime - ticket.startTime) / 1000
        //persist record to database
        return ticket.parkingSlot.parkingSlotType.getPriceForParking(duration)
    }

    private fun createTicketForSlot(parkingSlot: ParkingSlot, vehicle: Vehicle): Ticket {
        return Ticket.createTicket(vehicle, parkingSlot)
    }

    private fun getParkingSlotForVehicleAndPark(vehicle: Vehicle): ParkingSlot? {
        var parkingSlot: ParkingSlot? = null
        for (floor in parkingFloors) {
            parkingSlot = floor.getRelevantSlotForVehicleAndPark(vehicle)
            if (parkingSlot != null) break
        }
        return parkingSlot
    }

    companion object {
        fun getInstance(
            nameOfParkingLot: String,
            address: Address,
            parkingFloors: MutableList<ParkingFloor>
        ): ParkingLot {
            return ParkingLot(nameOfParkingLot, address, parkingFloors)
        }
    }
}