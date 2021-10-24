package ir.omidtaheri.foodtest.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ir.omidtaheri.foodtest.databinding.MultiStatePageBinding


class MultiStatePage(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val viewBinding: MultiStatePageBinding =
        MultiStatePageBinding.inflate(LayoutInflater.from(context), this)

    private fun recyclerViewVisibility(show: Boolean) {

        if (show) {
            viewBinding.recyclerView.visibility = View.VISIBLE
        } else {
            viewBinding.recyclerView.visibility = View.GONE
        }
    }

    private fun progressBarVisibility(show: Boolean) {

        if (show) {
            viewBinding.progressBar.visibility = View.VISIBLE
        } else {
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    private fun errorLayoutVisibility(show: Boolean) {
        if (show) {
            viewBinding.errorLayout.root.visibility = View.VISIBLE

        } else {
            viewBinding.errorLayout.root.visibility = View.GONE
        }
    }

    private fun setErrorText(text: String) {
        viewBinding.errorLayout.errorText.text = text
    }

    private fun setErrorButtonText(text: String) {
        viewBinding.errorLayout.errorBtnRetry.text = text
    }


    private fun errorButtonClickListener(listener: OnClickListener) {
        viewBinding.errorLayout.errorBtnRetry.setOnClickListener(listener)
    }


    fun toLoadingState() {
        recyclerViewVisibility(false)
        progressBarVisibility(true)
        errorLayoutVisibility(false)
    }

    fun toErrorState(listener: OnClickListener, errorText: String, errorButtonText: String) {
        recyclerViewVisibility(false)
        progressBarVisibility(false)
        errorLayoutVisibility(true)
        errorButtonClickListener(listener)
        setErrorText(errorText)
        setErrorButtonText(errorButtonText)
    }


    fun toDateState() {
        recyclerViewVisibility(true)
        progressBarVisibility(false)
        errorLayoutVisibility(false)
    }

    fun configRecyclerView(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        layoutManager: RecyclerView.LayoutManager
    ) {
        viewBinding.recyclerView.adapter = adapter
        viewBinding.recyclerView.layoutManager = layoutManager
    }

    fun getRecyclerView() = viewBinding.recyclerView

}
