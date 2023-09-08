package com.amitsah.designpatterns.behaviour.observer

class SmsAlertObserverImpl(private val phone: String, private val stockObservable: StockObservable) :
    NotificationAlertObserver {
    override fun update() {
        println("Sent sms update to $phone")
    }
}