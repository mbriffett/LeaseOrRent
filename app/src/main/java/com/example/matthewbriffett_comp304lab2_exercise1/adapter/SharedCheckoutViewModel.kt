package com.example.matthewbriffett_comp304lab2_exercise1.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewbriffett_comp304lab2_exercise1.data.Home

//this will store the final selected home in a place where it can be shared across fragments
class SharedCheckoutViewModel : ViewModel() {
    //MutableLiveData of whatever actual type is required for observing and updating views in real time
    var homeData = MutableLiveData<Home>()
}