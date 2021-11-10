package com.example.handlerthread

import java.util.*

/* TODO - 1 Declare a private instance of OrderHandlerThread inside the primary constructor
    solution: class FoodRunnable(private var orderHandlerThread: OrderHandlerThread)
 */

/* TODO - 2 Add a method setMaxOrders (not private) that returns nothins but just assigns the numbers of orders to the private variable size
    solution:
    fun setMaxOrders(size: Int) {
    this.size = size
    }
 */

/* TODO - 3 Inside run() write a while loop (that check alive condition and count variable less than size)
    solution :  while (alive && count < size) {  }
*/

/* TODO - 4 Inside while, increment count by one, initialize a variable foodName with a random name (using method of this class)
    solution:
    count++
    val foodName = getRandomOrderName()
 */

/* TODO - 5 Initialize a new variable foodPrice with a random value
    val foodPrice = getRandomOrderPrice()
 */

/* TODO - 6 Pass the order to the OrderHandlerThread
    solution: orderHandlerThread.sendOrder(FoodOrder(foodName,foodPrice))
 */

/* TODO - 7 Set a delay of one second with a try-catch block
    The thread'll continue until the maximum number of orders
    solution:
    try {
        Thread.sleep(1000)
      } catch (exception: InterruptedException) {
        exception.printStackTrace()
      }
 */

class FoodRunnable() : Runnable {

    private var thread: Thread = Thread(this)
    private var alive: Boolean = false
    private var count: Int = 0
    private var size: Int = 0

    override fun run() {
        alive = true
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
}
