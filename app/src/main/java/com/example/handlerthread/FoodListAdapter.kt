package com.example.handlerthread

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handlerthread.databinding.FoodListItemBinding

class FoodListAdapter(
    private val foodOrderList: MutableList<FoodOrder>,
    private val context: Context
) : RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return foodOrderList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindItems(foodOrderList[position])
    }

    fun getOrderList(): MutableList<FoodOrder> {
        return foodOrderList
    }

    /*non si può usare findViewById!!!!
    in culo a tutti!!! si può fare il binding!!!!!!
    Si mette TIPO BINDING DENTRO COSTRUTTORE PRIMARIO!!!!
    E RESTITUISCE LA BINDING.ROOT!!!!!

    */

    class FoodViewHolder(val binding: FoodListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(food: FoodOrder) {

            binding.foodNameTextView.text = itemView.context.getString(R.string.food_text, food.foodName)
            binding.foodPriceTextView.text = itemView.context.getString(R.string.price_text, food.foodPrice.toString())
            binding.sideOrderTextView.text = itemView.context.getString(R.string.side_order_text, food.sideOrder)
        }
        companion object {
            fun from(parent: ViewGroup): FoodViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodListItemBinding.inflate(layoutInflater, parent, false)

                return FoodViewHolder(binding)
            }
        }
    }
}