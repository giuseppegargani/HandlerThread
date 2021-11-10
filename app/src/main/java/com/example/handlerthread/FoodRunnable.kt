package com.example.handlerthread

import java.util.*

class FoodRunnable(private var orderHandlerThread: OrderHandlerThread) : Runnable {

    private var thread: Thread = Thread(this)
    private var alive: Boolean = false
    private var count: Int = 0
    private var size: Int = 0

    override fun run() {
        alive = true
        while (alive && count < size) {
            count++
            val foodName = getRandomOrderName()
            val foodPrice = getRandomOrderPrice()
            orderHandlerThread.sendOrders(FoodOrder(foodName, foodPrice))
            try {
                Thread.sleep(1000)
            } catch (exception: InterruptedException) {
                exception.printStackTrace()
            }
        }
    }

    fun start() {
        if (!thread.isAlive)
            thread.start()
    }

    fun stop() {
        alive = false
    }

    /**
     * @return Random Order Name for the restaurant.
     */
    private fun getRandomOrderName(): String {
        val random = Random()
        val randomOrder = random.nextInt(10)
        return when (randomOrder) {
            0 -> "McBurger"
            1 -> "McCola"
            2 -> "McPizza"
            3 -> "McIceCream"
            4 -> "McNoodles"
            5 -> "McBeer"
            6 -> "McLime"
            7 -> "McCoffee"
            8 -> "McCake"
            else -> "McOrange"
        }
    }

    /**
     * @return get the random price for orders in restaurant.
     */
    private fun getRandomOrderPrice(): Float {
        val random = Random()
        val randomOrder = random.nextInt(10)
        return when (randomOrder) {
            0 -> 5f
            1 -> 10f
            2 -> 15f
            3 -> 20f
            4 -> 25f
            5 -> 30f
            6 -> 35f
            7 -> 40f
            8 -> 45f
            else -> 50f
        }
    }

    fun setMaxOrders(size: Int){
        this.size = size
    }
}
