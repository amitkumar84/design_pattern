package com.amitsah.designpatterns.behaviour.observer

fun main(){
    val stockObservable = PixelObservableImpl()
    val observer1 = EmailAlertObserverImpl("a@gmail.com", stockObservable)
    val observer2 = SmsAlertObserverImpl("34496342", stockObservable)
    stockObservable.add(observer1)
    stockObservable.add(observer2)
    stockObservable.setStockCount(10)
}