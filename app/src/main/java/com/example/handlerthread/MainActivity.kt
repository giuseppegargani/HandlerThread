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