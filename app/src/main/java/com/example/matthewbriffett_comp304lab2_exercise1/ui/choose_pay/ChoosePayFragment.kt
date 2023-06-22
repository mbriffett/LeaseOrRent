package com.example.matthewbriffett_comp304lab2_exercise1.ui.choose_pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.matthewbriffett_comp304lab2_exercise1.R
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.PaySharedViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.SharedCheckoutViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.databinding.FragmentChoosePayBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChoosePayFragment : Fragment() {

    //Obtain a reference to the SharedCheckoutViewModel
    private val viewModel: SharedCheckoutViewModel by activityViewModels()
    private val payViewModel: PaySharedViewModel by activityViewModels()
    //Use view binding to access views
    private var _binding: FragmentChoosePayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate the layout using view binding
        _binding = FragmentChoosePayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Obtain the home data from the SharedCheckoutViewModel
        val homeData = viewModel.homeData

        //Set the image resource of the chooseImageView to the house image
        binding.chooseImageView.apply {
            homeData.value?.let { setImageResource(it.imageResourceId) }
        }

        //Set the text of the chooseTextView to the description of the house
        binding.chooseTextView.apply {
            text = homeData.value?.let { getString(it.stringResourceId) + "\n" + getString(it.addressResourceId) + "\n" + getString(it.priceResourceId) }
        }

        //Create a list of payment method checkboxes
        val paymentMethodCheckBoxes = listOf(
            binding.creditCheckBox,
            binding.debitCheckBox,
            binding.cashCheckBox
        )

        //Uncheck and check one of the checkboxes right away for testing purposes
        //This solved issues and just makes a preselection while Allowing navigation back to work as well

        binding.creditCheckBox.isChecked = false
        binding.creditCheckBox.isChecked = true

        //Variable for storing the last selected checkbox if one exists
        var lastSelected: CheckBox? = null
        paymentMethodCheckBoxes.forEach { checkBox ->
            //Save the last selected checkbox if it is checked when the view is created
            if (checkBox.isChecked) {
                lastSelected = checkBox
            }
            //Set an onClickListener for each checkbox to handle mutual exclusion of checked state and update paySharedViewModel and FAB visibility
            checkBox.setOnClickListener { buttonView ->
                val isChecked = (buttonView as CheckBox).isChecked
                if (isChecked) {
                    lastSelected = buttonView
                    paymentMethodCheckBoxes.filter { it != buttonView }
                        .forEach { it.isChecked = false }
                } else if (buttonView == lastSelected) {
                    buttonView.isChecked = false
                    lastSelected = null
                }

                //Update the paymentChoice property of the paySharedViewModel
                payViewModel.paymentChoice.value = checkBox.isChecked && (checkBox == binding.creditCheckBox || checkBox == binding.debitCheckBox)

                //Update the visibility of the FAB based on whether any checkbox is checked
                val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
                fab.visibility = if (paymentMethodCheckBoxes.any { it.isChecked }) View.VISIBLE else View.GONE
            }
        }

        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { view ->
            val action = ChoosePayFragmentDirections.actionChoosePayFragmentToPayDetailsFragment()
            findNavController().navigate(action)
        }


        //Set the initial visibility of the FAB to View.GONE so that it is not visible when the fragment is first shown
        fab.visibility = if (paymentMethodCheckBoxes.any { it.isChecked }) View.VISIBLE else View.GONE

        //The visibility of the FAB will be updated when any of the payment method checkboxes are clicked.
        //If any checkbox is checked, the FAB will become visible. If none of these checkboxes are checked,
        //the FAB will become invisible again.
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}