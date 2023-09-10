package com.amitsah.designpatterns.low_level_design.parking_lot

enum class ParkingSlotType {
    TwoWheeler {
        override fun getPriceForParking(duration: Long): Double {
            return duration * 0.05
        }
    },
    Compact {
        override fun getPriceForParking(duration: Long): Double {
            return duration * 0.075
        }
    },
    Medium {
        override fun getPriceForParking(duration: Long): Double {
            return duration * 0.09
        }
    },
    Large {
        override fun getPriceForParking(duration: Long): Double {
            return duration * 0.10
        }
    };

    abstract fun getPriceForParking(duration: Long): Double
}