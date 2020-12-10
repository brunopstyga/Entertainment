package com.bruno.juegos.entertainment.ui.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.bruno.juegos.entertainment.R
import com.bruno.juegos.entertainment.model.Results
import com.bruno.juegos.entertainment.repositories.ApiRepositories
import com.bruno.juegos.entertainment.util.Keyboard
import kotlinx.coroutines.Dispatchers


class MainViewModel @ViewModelInject constructor(
        val apiRepositories: ApiRepositories
) : ViewModel() {


    val amount: MutableLiveData<String> = MutableLiveData()



    fun showResultAddNumberUser(view: View) {
        Keyboard.hide(view)
        Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_gameFragment)
    }

    fun getData() = liveData(Dispatchers.IO) {
        emit(apiRepositories.getData(
                amount.value?.toString() ?: ""))
    }

}
