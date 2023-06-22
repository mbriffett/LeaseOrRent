package com.example.matthewbriffett_comp304lab2_exercise1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matthewbriffett_comp304lab2_exercise1.R
import com.example.matthewbriffett_comp304lab2_exercise1.data.Home

/**
 * Adapter for the [RecyclerView] in all home fragments (ApartmentFragment, CondoFragment, etc. Displays [Home] data object.
 */
class SelectedHomeAdapter(
    private val context: Context,
    private val checkoutViewModel: SharedCheckoutViewModel
) : ListAdapter<Home, SelectedHomeAdapter.ItemViewHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.selected_list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.imageView.setImageDrawable(context.getDrawable(item.imageResourceId))
        holder.textView.text = context.getString(item.stringResourceId) + "\n" + context.getString(item.addressResourceId) + "\n" + context.getString(item.priceResourceId)

        // Set listener to null
        holder.radioButton.setOnCheckedChangeListener(null)

        //Check if the item at the position currently being bound is in the checkoutViewModel Home data (single home item) and set the button checked if so
        holder.radioButton.isChecked = checkoutViewModel.homeData.value?.equals(item) == true

        // Set listener again
        holder.radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //Update the homeData property of the checkoutViewModel with the selected item
                checkoutViewModel.homeData.value = item

                //Notify the adapter that the data has changed so that it can update the checked state of all radio buttons
                notifyDataSetChanged()
            }
        }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.homeImageView)
        val textView: TextView = view.findViewById(R.id.homeTextView)
        val radioButton: RadioButton = view.findViewById(R.id.homeRadioButton)
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<Home>() {
    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }
}
