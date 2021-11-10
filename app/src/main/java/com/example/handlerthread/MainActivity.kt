package com.example.handlerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var foodListAdapter: FoodListAdapter
    private lateinit var uiHandler: UiHandler
    private lateinit var orderRecyclerView: RecyclerView

    /*TODO 1 - Add two properties without initialize them (foodRunnable and orderHandlerThread)
   solution:
   private lateinit var foodRunnable: FoodRunnable
   private lateinit var orderHandlerThread: OrderHandlerThread
 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        orderRecyclerView = findViewById(R.id.orderRecyclerView)
        foodListAdapter = FoodListAdapter(mutableListOf(), applicationContext)
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
        orderRecyclerView.adapter = foodListAdapter
        uiHandler = UiHandler()
        uiHandler.setRecyclerView(orderRecyclerView)
        uiHandler.setAdapter(foodListAdapter)
    }

    /* TODO 2 - Do the override of onStart() and inside it instantiate orderHandlerThread passing the uiHandler
    solution:
    override fun onStart() {
    super.onStart()
    orderHandlerThread = OrderHandlerThread(uiHandler)
*/

    /* TODO 3 - Make orderHandlerThread start and instantiate foodRunnable passing orderHandlerThread
        solution:
        orderHandlerThread.start()
        foodRunnable = FoodRunnable(orderHandlerThread)
     */

    /* TODO 4 - Set the maximum numbers of order (with foodRunnable) and make it start()
        solution:
        foodRunnable.setMaxOrders(10)
        foodRunnable.start()
    */

    /* TODO 5 - Inside the class uiHandler write the method addAndNotifyForOrder (with parameters foodOrder and position)
         solution:
         private fun addAndNotifyForOrder(foodOrder: FoodOrder, position: Int) {
        }
    */

    /* TODO 6 - Add the foodOrder to the list of weakFoodListAdapter and NotifyItemInserted (to update RecyclerView)
        use weakReference to avoid memoryLeaks (and not let the app crash)
        solution:
        weakRefFoodListAdapter.get()?.getOrderList()?.add(foodOrder)
        weakRefOrderRecyclerView.get()?.adapter?.notifyItemInserted(position)
     */

    /* TODO 6 - Write the override of HandleMessage and initialize position with the size of weakRefFoodListAdapter
        solution: val position = weakRefFoodListAdapter.get()?.getOrderList()?.size
     */

    /* TODO 7 - Inside HandleMEssage invoke the method addAndNotifyForOrder sending msg?.obj and position
        solution: addAndNotifyForOrder(msg?.obj as FoodOrder, position!!)
     */

    /* TODO 8 - Call a method od OrderRecyclerView to make smoothScrollToPosition passing it the itemCount
        solution: weakRefOrderRecyclerView.get()?.smoothScrollToPosition(weakRefFoodListAdapter.get()?.itemCount!!)
     */

    /* TODO 9 - Write the override of onDestroy (in MainActivity) make foodRunnable and orderHandlerThread stop.
        We should use safelyQuit() instead of quit() that now is deprecated
        solution:
        override fun onDestroy() {
        super.onDestroy()
        foodRunnable.stop()
        orderHandlerThread.quit()
        }
    */

    class UiHandler : Handler() {

        private lateinit var weakRefFoodListAdapter: WeakReference<FoodListAdapter>
        private lateinit var weakRefOrderRecyclerView: WeakReference<RecyclerView>

        fun setAdapter(foodListAdapter: FoodListAdapter) {
            weakRefFoodListAdapter = WeakReference(foodListAdapter)
        }

        fun setRecyclerView(foodRecyclerView: RecyclerView) {
            weakRefOrderRecyclerView = WeakReference(foodRecyclerView)
        }
    }

}