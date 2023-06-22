package com.example.matthewbriffett_comp304lab2_exercise1.ui.selected_homes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.SelectedHomeAdapter
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.SharedCheckoutViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.SharedViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.data.Home
import com.example.matthewbriffett_comp304lab2_exercise1.databinding.FragmentSelectedHomesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SelectedHomeFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private val sharedCheckoutViewModel: SharedCheckoutViewModel by activityViewModels()
    private var _binding: FragmentSelectedHomesBinding? = null
    private var homeList: List<Home> = emptyList()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectedHomesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //get RecyclerView and set data binding to appropriate recycler
        val recyclerView: RecyclerView = binding.selectedHomesRecycler
        //observe list from sharedViewModel to get selected homes to show
        //create the home adapter within the observer block so to ensure the adapter is created and set after the data has been obeserved and the homeList property updated
        viewModel.selectedHomeData.observe(viewLifecycleOwner, Observer { newList ->
            homeList = newList.toList()

            //note the below is within the observer block
            //create HomeAdapter with passed DataSet to set up a shared view to store the chosen house (to move to checkout screen with correct data)
            //cannot use submit here as that is for submitting a list of items to show in a recycler view to its adapter
            //The other sharedViewModel functions to hold the list that gets submitted to the select home screen before checkout
            //and is used to hold the values so on return the items can be checked again
            val selectedHomeAdapter = context?.let { SelectedHomeAdapter(it, sharedCheckoutViewModel) }
            // Submit the new list of homes to the adapter using the submitList method
            selectedHomeAdapter?.submitList(homeList)
            //set recyclerView adapter to created selectedHomeAdapter
            recyclerView.adapter = selectedHomeAdapter
        })

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = requireActivity().findViewById<FloatingActionButton>(com.example.matthewbriffett_comp304lab2_exercise1.R.id.fab)

        fab.setOnClickListener { view ->
            //Handle FAB click
            //action defined in navigation view for use with navController and navDirections
            //utlises androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3 dependency/plugin
            val action = SelectedHomeFragmentDirections.actionSelectedHomeFragmentToChoosePayFragment()
            findNavController().navigate(action)
        }
        //observe contents of sharedCheckoutModel and ensure they are contained in viewModel (selected Home data) (LiveMutableData<List<Home>>) and update fab visibility accordingly
        //if there is an entry from a previous navigation it will not be visible in the selected homes if other items are selected
        //but still remains in the sharedCheckout list, making the nav button visible with no selections and allowing you to navigate
        //to its checkout page despite not being selected or even in the view of houses.  Removing the checkout data would prevent nav back from the pay details page
        //while retaining choose payment page item data.
        //This way, the nav button isn't visible while that old entry is in the checkoutModel and you have to select a
        //radio button for a house to make it visible
        //which replaces the old checkoutModel (a single home) value with the new value anyway
        sharedCheckoutViewModel.homeData.observe(viewLifecycleOwner, Observer {

            if (viewModel.selectedHomeData.value?.contains(it) == false) {
                fab.visibility = View.GONE
            }
            else fab.visibility = View.VISIBLE
            //fab.visibility = if (it == null) View.GONE else View.VISIBLE

        })

        //Set the initial visibility of the FAB to View.GONE so that it is not visible when the fragment is first shown
        fab.visibility = View.GONE

        //The visibility of the FAB will be updated when any home is selected or deselected.
        //If any home is selected, the FAB will become visible. If no homes are selected,
        //the FAB will become invisible again.
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}