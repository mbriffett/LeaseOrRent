package com.example.matthewbriffett_comp304lab2_exercise1.ui.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.matthewbriffett_comp304lab2_exercise1.R
import com.example.matthewbriffett_comp304lab2_exercise1.adapter.PaySharedViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.databinding.FragmentPayDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PayDetailsFragment : Fragment() {
    //Obtain a reference to the paySharedViewModel
    private val paySharedViewModel: PaySharedViewModel by activityViewModels()

    //Use view binding to access views
    private var _binding: FragmentPayDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Add validation for fullNameEditText
        //Use a TextWatcher to validate the input as the user types
        binding.fullNameEditText.addTextChangedListener(object : TextWatcher {
            //Called after the text has changed
            override fun afterTextChanged(s: Editable?) {
                //Get the input as a string
                val input = s.toString()
                //Define a regular expression pattern that only allows letters and spaces
                val pattern = "^[a-zA-Z\\s]*$"
                //Check if the input matches the pattern using the matches method
                if (!input.matches(pattern.toRegex())) {
                    //If the input does not match the pattern, set an error message on the EditText
                    binding.fullNameEditText.error = "Invalid input. Only letters and spaces are allowed."
                } else {
                    //If the input matches the pattern, clear any error message on the EditText
                    binding.fullNameEditText.error = null
                }
            }

            //Called before the text is changed
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            //Called when the text is being changed
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        //Add validation for cardNumberEditText using the Luhn algorithm
        //Use a TextWatcher to validate the input as the user types
        binding.cardNumberEditText.addTextChangedListener(object : TextWatcher {
            //Called after the text has changed
            override fun afterTextChanged(s: Editable?) {
                //Get the input as a string
                val input = s.toString()
                //Check if the card number is valid using the luhnCheck function
                if (!luhnCheck(input)) {
                    //If the card number is not valid, set an error message on the EditText
                    binding.cardNumberEditText.error = "Invalid card number."
                } else {
                    //If the card number is valid, clear any error message on the EditText
                    binding.cardNumberEditText.error = null
                }
            }

            //Called before the text is changed
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            //Called when the text is being changed
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        //Add validation for favoriteSportEditText
        //Use a TextWatcher to validate the input as the user types
        binding.favoriteSportEditText.addTextChangedListener(object : TextWatcher {
            //Called after the text has changed
            override fun afterTextChanged(s: Editable?) {
                //Get the input as a string
                val input = s.toString()
                //Define a regular expression pattern that only allows letters and spaces
                val pattern = "^[a-zA-Z\\s]*$"
                //Check if the input matches the pattern using the matches method
                if (!input.matches(pattern.toRegex())) {
                    //If the input does not match the pattern, set an error message on the EditText
                    binding.favoriteSportEditText.error = "Invalid input. Only letters and spaces are allowed."
                } else {
                    //If the input matches the pattern, clear any error message on the EditText
                    binding.favoriteSportEditText.error = null
                }
            }

            //Called before the text is changed
            override fun beforeTextChanged(s: CharSequence?, start: Int, count:Int, after:Int){}

            //Called when the text is being changed
            override fun onTextChanged(s:CharSequence?, start:Int,before:Int,count:Int){}
        })

        //Add validation for favoriteTeamEditText
        //Use a TextWatcher to validate the input as the user types
        binding.favoriteTeamEditText.addTextChangedListener(object : TextWatcher {
            //Called after the text has changed
            override fun afterTextChanged(s: Editable?) {
                //Get the input as a string
                val input = s.toString()
                //Define a regular expression pattern that only allows letters and spaces
                val pattern = "^[a-zA-Z\\s]*$"
                //Check if the input matches the pattern using the matches method
                if (!input.matches(pattern.toRegex())) {
                    //If the input does not match the pattern, set an error message on the EditText
                    binding.favoriteTeamEditText.error = "Invalid input. Only letters and spaces are allowed."
                } else {
                    //If the input matches the pattern, clear any error message on the EditText
                    binding.favoriteTeamEditText.error = null
                }
            }

            //Called before the text is changed
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            //Called when the text is being changed
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        //Add validation for favoriteFoodEditText
        //Use a TextWatcher to validate the input as the user types
        binding.favoriteFoodEditText.addTextChangedListener(object : TextWatcher {
            //Called after the text has changed
            override fun afterTextChanged(s: Editable?) {
                //Get the input as a string
                val input = s.toString()
                //Define a regular expression pattern that only allows letters and spaces
                val pattern = "^[a-zA-Z\\s]*$"
                //Check if the input matches the pattern using the matches method
                if (!input.matches(pattern.toRegex())) {
                    //If the input does not match the pattern, set an error message on the EditText
                    binding.favoriteFoodEditText.error = "Invalid input. Only letters and spaces are allowed."
                } else {
                    //If the input matches the pattern, clear any error message on the EditText
                    binding.favoriteFoodEditText.error = null
                }
            }

            //Called before the text is changed
            override fun beforeTextChanged(s: CharSequence?, start: Int, count:Int, after:Int) {}

            //Called when the text is being changed
            override fun onTextChanged(s:CharSequence?, start:Int,before:Int,count:Int){}
        })

        return root
    }

    //Function to check if a card number is valid using the Luhn algorithm
    private fun luhnCheck(cardNumber:String):Boolean{
        var sum=0
        var alternate=false
        for(i in cardNumber.length-1 downTo 0){
            var n=cardNumber[i]-'0'
            if(alternate){
                n*=2
                if(n>9){
                    n=(n%10)+1
                }
            }
            sum+=n
            alternate=!alternate
        }
        return (sum%10==0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get and hide fab
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.visibility = View.GONE
        //set up observer to watch for changes in the paySharedViewModel (payment choice is a bool, true for debit/credit, false for cash)
        paySharedViewModel.paymentChoice.observe(viewLifecycleOwner) { value ->
            binding.cardNumberEditText.visibility = if (value == true) View.VISIBLE else View.GONE
        }
    }
}
