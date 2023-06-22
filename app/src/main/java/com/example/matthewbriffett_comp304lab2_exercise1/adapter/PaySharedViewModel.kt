package com.example.matthewbriffett_comp304lab2_exercise1.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaySharedViewModel: ViewModel() {
    val paymentChoice = MutableLiveData<Boolean>()
}