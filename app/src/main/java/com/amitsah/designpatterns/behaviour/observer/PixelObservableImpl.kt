package com.amitsah.designpatterns.behaviour.observer

class PixelObservableImpl : StockObservable {

    private val observers = mutableListOf<NotificationAlertObserver>()
    private var stockCount = 0;
    override fun add(observer: NotificationAlertObserver) {
        observers.add(observer)
    }

    override fun remove(observer: NotificationAlertObserver) {
        observers.remove(observer)
    }

    override fun notifySubscribers() {
        for (observer in observers) {
            observer.update()
        }
    }

    override fun setStockCount(newStocks: Int) {
        if (stockCount == 0) {
            notifySubscribers()
        }
        stockCount += newStocks;
    }

    override fun getStockCount(): Int {
        return stockCount;
    }
}