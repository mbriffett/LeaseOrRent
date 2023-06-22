package com.example.matthewbriffett_comp304lab2_exercise1.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.data.Home

//this will store the selected homes in a shared view model where all the fragments can see the data
class SharedViewModel : ViewModel() {
    //MutableLiveData of whatever actual type is required for observing and updating views in real time
    var selectedHomeData = MutableLiveData<List<Home>>()

}