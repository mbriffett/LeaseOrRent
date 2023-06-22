package com.example.matthewbriffett_comp304lab2_exercise1.ui.town_house

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.matthewbriffett_comp304lab2_exercise1.R
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.HomeAdapter
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.SharedViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.data.DataSource
import com.example.matthewbriffett_comp304lab2_exercise1.databinding.FragmentTownHouseBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TownHouseFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentTownHouseBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myDataset = DataSource().loadTownHouses()
        _binding = FragmentTownHouseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //get RecyclerView and set data binding to appropriate recycler
        val recyclerView: RecyclerView = binding.townHouseRecycler
        //create HomeAdapter with passed DataSet to set up an adapter to be used with recyclerview
        val homeAdapter = context?.let { HomeAdapter(it, viewModel, viewLifecycleOwner) }
        //set recyclerView adapter to created homeAdapter
        recyclerView.adapter = homeAdapter
        homeAdapter?.submitList(myDataset)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { view ->
            //Handle FAB click
            //action defined in navigation view for use with navController and navDirections
            //utlises androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3 dependency/plugin
            val action = TownHouseFragmentDirections.actionTownHouseFragmentToSelectedHomeFragment()
            findNavController().navigate(action)
        }
        //observe contents of viewmodel (LiveMutableData<List<Home>>) and update fab visibility accordingly
        //makes fab visible when at least one button is selected since selections will be in the selectedHomeDataList
        viewModel.selectedHomeData.observe(viewLifecycleOwner, Observer {
            fab.visibility = if (it.isNullOrEmpty()) View.GONE else View.VISIBLE
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
