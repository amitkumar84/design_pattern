package com.amitsah.designpatterns.behaviour.observer

interface StockObservable {
    fun add(observer:NotificationAlertObserver)

    fun remove(observer:NotificationAlertObserver)

    fun notifySubscribers()

    fun setStockCount(newStocks:Int)

    fun getStockCount():Int

}