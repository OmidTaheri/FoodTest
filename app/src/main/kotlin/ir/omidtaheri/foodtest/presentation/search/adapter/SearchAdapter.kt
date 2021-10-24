package ir.omidtaheri.foodtest.presentation.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.base.BaseViewHolder
import ir.omidtaheri.foodtest.base.glideutils.loadFoodImage
import ir.omidtaheri.foodtest.data.models.FoodModel
import ir.omidtaheri.foodtest.databinding.SearchListEmptyStateBinding
import ir.omidtaheri.foodtest.databinding.SearchListItemBinding


class SearchAdapter(private val context: Context, private val clickListener: Callback) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var listItems: MutableList<FoodModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            ViewTypes.VIEW_TYPE_ITEM -> {
                return ItemViewHolder(
                    SearchListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else ->
                return EmptyViewHolder(
                    SearchListEmptyStateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class ItemViewHolder(private val binding: SearchListItemBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            val item = listItems[position]

            binding.apply {
                item.imageUrl?.let { foodImageView.loadFoodImage(it, context) }
                foodTitle.text = item.title
                foodDesc.text = item.categoryName
                root.setOnClickListener {
                    clickListener.onItemClick(item.id)
                }
            }
        }
    }

    inner class EmptyViewHolder(private val binding: SearchListEmptyStateBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            binding.apply {
            }
        }
    }


    override fun getItemCount(): Int {
        return if (listItems.size != 0) {
            listItems.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listItems.size > 0) {
            ViewTypes.VIEW_TYPE_ITEM
        } else {
            ViewTypes.VIEW_TYPE_EMPTY
        }
    }

    fun addItem(item: FoodModel) {
        listItems.add(item)
        notifyItemInserted(listItems.size - 1)
    }

    fun addItems(list: List<FoodModel>) {
        listItems.clear()
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(item: FoodModel) {
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


    companion object ViewTypes {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    interface Callback {
        fun onItemClick(foodId: Long)
    }
}
