package ir.omidtaheri.foodtest.presentation.categorylist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.base.BaseViewHolder
import ir.omidtaheri.foodtest.base.glideutils.loadFoodCategoryImage
import ir.omidtaheri.foodtest.data.models.FoodCategoryModel
import ir.omidtaheri.foodtest.databinding.CategoryListEmptyStateBinding
import ir.omidtaheri.foodtest.databinding.CategoryListItemBinding


class CategoryListAdapter(private val context: Context, private val clickListener: Callback) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var listItems: MutableList<FoodCategoryModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            ViewTypes.VIEW_TYPE_ITEM -> {
                return ItemViewHolder(
                    CategoryListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else ->
                return EmptyViewHolder(
                    CategoryListEmptyStateBinding.inflate(
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


    inner class ItemViewHolder(private val binding: CategoryListItemBinding) :
        BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            val item = listItems[position]

            binding.apply {
                item.imageUrl?.let { categoryImageview.loadFoodCategoryImage(it, context) }
                categoryTextview.text = item.name
                root.setOnClickListener {
                    clickListener.onItemClick(item.id, item.name)
                }
            }
        }
    }

    inner class EmptyViewHolder(private val binding: CategoryListEmptyStateBinding) :
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

    fun addItem(item: FoodCategoryModel) {
        listItems.add(item)
        notifyItemInserted(listItems.size - 1)
    }

    fun addItems(list: List<FoodCategoryModel>) {
        listItems.clear()
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    fun remove(item: FoodCategoryModel) {
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
        fun onItemClick(categoryId: Long, categoryName: String)
    }
}
