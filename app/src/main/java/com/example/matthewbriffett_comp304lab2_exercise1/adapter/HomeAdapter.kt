package com.example.matthewbriffett_comp304lab2_exercise1.adapter


    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.CheckBox
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.lifecycle.LifecycleOwner
    import androidx.lifecycle.Observer
    import androidx.recyclerview.widget.ListAdapter
    import androidx.recyclerview.widget.RecyclerView
    import com.example.matthewbriffett_comp304lab2_exercise1.R
    import com.example.matthewbriffett_comp304lab2_exercise1.data.Home


/**
     * Adapter for the [RecyclerView] in all home fragments (ApartmentFragment, CondoFragment, etc. Displays [Home] data object.
     */
class HomeAdapter(
    private val context: Context,
    private val viewModel: SharedViewModel,
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<Home, HomeAdapter.ItemViewHolder>(HomeDiffCallback()) {

    // List for holding selected homes to pass to selected homes fragment via shareviewmodel
    private var selectedDataSet: MutableList<Home> = mutableListOf()

    init {
        // Set stable IDs to improve performance
        setHasStableIds(true)

        // Observe the selectedHomeData in the SharedViewModel and update the selectedDataSet accordingly
        viewModel.selectedHomeData.observe(viewLifecycleOwner, Observer { newList ->
            selectedDataSet = newList.toMutableList()
            notifyDataSetChanged()
        })
    }

    // Return a stable ID for each item in the list
    override fun getItemId(position: Int): Long {
        return getItem(position).itemId
    }

    // ViewHolder class to hold references to the views for each data item
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.homeImageView)
        val textView: TextView = view.findViewById(R.id.homeTextView)
        val checkBox: CheckBox = view.findViewById(R.id.homeCheckBox)

    }

    // Create a new ViewHolder when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //list_item is the layout which will be used for each item in each view holder for any instance this adapter (HomeAdapter)
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // Bind data to the ViewHolder when it's being displayed
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.textView.text = context.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)


        // Set listener to null to prevent triggering it when changing checked state
        holder.checkBox.setOnCheckedChangeListener(null)

        // Change checked state based on whether the item is in the selectedDataSet
        holder.checkBox.isChecked = selectedDataSet.contains(item)

        // Set listener again to update the selectedDataSet and SharedViewModel when checked state changes
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedDataSet.add(item)
            } else {
                selectedDataSet.remove(item)
            }
            viewModel.selectedHomeData.value = selectedDataSet
        }
    }
}

