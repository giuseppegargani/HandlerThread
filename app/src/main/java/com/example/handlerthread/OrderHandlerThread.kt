package com.example.handlerthread

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import androidx.annotation.VisibleForTesting
import java.math.BigDecimal
import java.util.*

/*TODO 1 - The class OrderHandlerThread extends the class HandlerThread ( you should write the proper constructor of HandlerThread)
      usually we write the name of the class inside HandlerThread for make the debug more easy
      Solution: class OrderHandlerThread : HandlerThread("OrderHandlerThread") {}
    */

/*TODO 2 - Now write the primary constructor of the class OrderHandlerThread
    The primary constructor refers to the class of UIHandler of the MainActivity that we have previously built
    solution: private var uiHandler: MainActivity.UiHandler
 */

class OrderHandlerThread(private var uiHandler: MainActivity.UiHandler): HandlerThread("giuseppe") {

    /* TODO 3 - Declare and initialize two variables handler (Handler) and random (Random imported from java)
    solution:
    private var handler: Handler? = null
    private val random = Random()
 */

    /* TODO 4 - Write a function to convert in Rupie (convertCurrency) and to add sideDish (using the variable random e "when" statement)
    Crea due metodi per la conversione in Rupie e per la scelta casuale del contorno (foodPriceInDollars 68,45f) e attachSideOrder (con random e when)
     to get price in Rupie you should multiply for 68.45f
     solution:
        /**
     * Converts the food price from USD to Indian Rupees (INR).
    * 1 USD has been considered as equal to 68.45 INR.
    * @foodPriceInDollars price of the food in USD.
    */
    private fun convertCurrency(foodPriceInDollars: Float): Float {
    return foodPriceInDollars * 68.45f
    }
    /**
    * Attaches random side order to the incoming food orders.
     */
    private fun attachSideOrder(): String {
    val randomOrder = random.nextInt(3)
    return when (randomOrder) {
    0 -> "Chips"
    1 -> "Salad"
    else -> "Nachos"
    }
    }
     */

    /* TODO 5 - Write a getHandler method that returns an object expression that inherits from Handler supertype (with looper as parameter)
        solution:
        private fun getHandler(looper: Looper): Handler {
        return object:Handler(looper) {    }}
     */

    /*TODO 6 - Do the override of HandleMessage (where you place the orders - messages) and assign them to a new variable foodOrder
        to override a method you can use CTRL+O
        you should assign them as msg?.obj
        solution:
        override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        val foodOrder = msg?.obj as FoodOrder
     */

    /* TODO 7 - Process the orders converting the currency and attaching a side dish
        solution:
        foodOrder.foodPrice = convertCurrency(foodOrder.foodPrice)
        foodOrder.sideOrder = attachSideOrder()
     */

    /* TODO 8 - Initialize a new variable (processedMessage) of the type Message and you pass the processed order, and send the message
        To send the message to the UI you should use the method sendMessage on the destination Thread (UiThread)
        solution:
        val processedMessage = Message()
        processedMessage.obj = foodOrder
        uiHandler.sendMessage(processedMessage)
     */

    /* TODO 9 - Write the method sendOrder (not private) to send the orders using the variable handler that you have created (sendMessage)
        solution:
        fun sendOrder(foodOrder: FoodOrder) {
        val message = Message()
        message.obj = foodOrder
        handler?.sendMessage(message)
        }
     */

    /* TODO 10 - Do the override of onLooperPrepared (that assigns the handler variable)
        solution:
        override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = getHandler(looper)
        }
     */

    @VisibleForTesting (otherwise = VisibleForTesting.PRIVATE)
    var handler: Handler? = null
    @VisibleForTesting (otherwise = VisibleForTesting.PRIVATE)
    var random = Random()

    @VisibleForTesting (otherwise = VisibleForTesting.PRIVATE)
    fun convertCurrency(foodPriceInDollars: Float): Float {
        return (foodPriceInDollars.toBigDecimal() * 68.45.toBigDecimal()).toFloat()
    }

    @VisibleForTesting (otherwise = VisibleForTesting.PRIVATE)
    fun attachSideOrder():String {
        val randomOrder = random.nextInt(3)
        return when(randomOrder){
            0->"Patatine"
            1->"Insalata"
            else->"Nachos"
        }
    }

    @VisibleForTesting (otherwise = VisibleForTesting.PRIVATE)
    fun getHandler(looper: Looper): Handler {
        return object:Handler(looper) {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val foodOrder = msg?.obj as FoodOrder
                foodOrder.foodPrice = convertCurrency(foodOrder.foodPrice)
                foodOrder.sideOrder = attachSideOrder()
                val processedMessage = Message()
                processedMessage.obj = foodOrder
                uiHandler.sendMessage(processedMessage)
            }
        }
    }

    fun sendOrder(foodOrder:FoodOrder){
        val message = Message()
        message.obj = foodOrder
        handler?.sendMessage(message)
    }

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = getHandler(looper)
    }
}