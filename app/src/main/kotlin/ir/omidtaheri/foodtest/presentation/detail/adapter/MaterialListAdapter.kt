package ir.omidtaheri.foodtest.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.base.BaseViewHolder
import ir.omidtaheri.foodtest.data.models.FoodMaterial
import ir.omidtaheri.foodtest.databinding.FoodMaterialItemBinding


class MaterialListAdapter(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var listItems: MutableList<FoodMaterial> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            FoodMaterialItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class ItemViewHolder(private val binding: FoodMaterialItemBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            val item = listItems[position]

            binding.apply {
                foodMaterialName.text = item.name
                foodMaterialAmount.text = item.amount
            }
        }
    }


    override fun getItemCount() = listItems.size

    fun addItem(item: FoodMaterial) {
        listItems.add(item)
        notifyItemInserted(listItems.size - 1)
    }

    fun addItems(list: List<FoodMaterial>) {
        listItems.clear()
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(item: FoodMaterial) {
        val index = listItems.indexOf(item)
        if (index >= 0) {
            listItems.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun clearList() {
        listItems.clear()
        notifyDataSetChanged()
    }

}
