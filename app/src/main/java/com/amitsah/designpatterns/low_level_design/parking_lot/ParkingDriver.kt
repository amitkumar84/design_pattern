package com.amitsah.designpatterns.low_level_design.parking_lot


fun main() {
    val nameOfParkingLot = "Amit Parking Lot"
    val address = Address(
        city = "Bangalore",
        country = "India",
        state = "KA"
    )
    val allSlots: MutableMap<ParkingSlotType, Map<String, ParkingSlot>> =
        HashMap<ParkingSlotType, Map<String, ParkingSlot>>()

    val compactSlot = HashMap<String, ParkingSlot>()
    compactSlot["C1"] = ParkingSlot("C1", ParkingSlotType.Compact)
    compactSlot["C2"] = ParkingSlot("C2", ParkingSlotType.Compact)
    compactSlot["C3"] = ParkingSlot("C3", ParkingSlotType.Compact)
    allSlots[ParkingSlotType.Compact] = compactSlot

    val largeSlot: MutableMap<String, ParkingSlot> = HashMap<String, ParkingSlot>()
    largeSlot["L1"] = ParkingSlot("L1", ParkingSlotType.Large)
    largeSlot["L2"] = ParkingSlot("L2", ParkingSlotType.Large)
    largeSlot["L3"] = ParkingSlot("L3", ParkingSlotType.Large)
    allSlots[ParkingSlotType.Large] = largeSlot

    val parkingFloor = ParkingFloor("1", allSlots)
    val parkingFloors: MutableList<ParkingFloor> = ArrayList<ParkingFloor>()
    parkingFloors.add(parkingFloor)
    val parkingLot: ParkingLot = ParkingLot.getInstance(nameOfParkingLot, address, parkingFloors)

    val vehicle = Vehicle()
    vehicle.vehicleCategory = VehicleCategory.Hatchback
    vehicle.vehicleNumber = "KA-01-MA-9999"

    val ticket: Ticket? = parkingLot.assignTicket(vehicle)
    System.out.println(" ticket number >> " + ticket?.ticketNumber)

    Thread.sleep(10000)
    ticket?.let {
        val price: Double = parkingLot.scanAndPay(it)
        println("price is >>$price")
    }

}