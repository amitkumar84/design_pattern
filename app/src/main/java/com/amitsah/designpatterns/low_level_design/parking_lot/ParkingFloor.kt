package com.amitsah.designpatterns.low_level_design.parking_lot

class ParkingFloor(
    var name: String,
    private var parkingSlots: Map<ParkingSlotType, Map<String, ParkingSlot>>
) {

    fun getRelevantSlotForVehicleAndPark(vehicle: Vehicle): ParkingSlot? {
        val vehicleCategory = vehicle.vehicleCategory
        val parkingSlotType = pickCorrectSlot(vehicleCategory)
        val relevantParkingSlot: Map<String, ParkingSlot>? = parkingSlots[parkingSlotType]
        var slot: ParkingSlot? = null
        if (relevantParkingSlot != null) {
            for (value in relevantParkingSlot.values) {
                if (value.isAvailable) {
                    slot = value
                    slot.addVehicle(vehicle)
                    break
                }
            }
        }
        return slot
    }

    private fun pickCorrectSlot(vehicleCategory: VehicleCategory?): ParkingSlotType? {
        return when (vehicleCategory) {
            VehicleCategory.TwoWheeler -> ParkingSlotType.TwoWheeler
            VehicleCategory.Hatchback, VehicleCategory.Sedan -> ParkingSlotType.Compact
            VehicleCategory.SUV -> ParkingSlotType.Medium
            VehicleCategory.Bus -> ParkingSlotType.Large
            else -> null
        }
    }
}