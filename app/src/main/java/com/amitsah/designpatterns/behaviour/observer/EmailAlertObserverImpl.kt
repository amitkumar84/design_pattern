package com.amitsah.designpatterns.behaviour.observer

class EmailAlertObserverImpl(private val email:String, private val stockObservable:StockObservable):NotificationAlertObserver {
    override fun update() {
        println("Sent email update to $email")
    }
}