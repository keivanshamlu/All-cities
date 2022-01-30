package com.shamlou.bases_android.viewModel

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.shamlou.bases_android.fragment.LiveEvent
import com.shamlou.navigation.command.ActionCommand
import com.shamlou.navigation.command.NavigationCommand
import com.shamlou.navigation.command.NavigationFlow

abstract class BaseViewModel : ViewModel() {

    private val _navigationCommand = LiveEvent<NavigationCommand>()
    val navigationCommand: LiveData<NavigationCommand> = _navigationCommand

    fun navigateTo(flow: NavigationFlow) =
        _navigationCommand.postValue(NavigationCommand.To(flow))

    fun navigateBack() =
        _navigationCommand.postValue(NavigationCommand.Back)

    fun navigateBackTo(destinationId: Int, inclusive: Boolean) =
        _navigationCommand.postValue(NavigationCommand.BackTo(destinationId, inclusive))


    private val _actionCommand = LiveEvent<ActionCommand>()
    val actionCommand: LiveData<ActionCommand> = _actionCommand

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        _actionCommand.postValue(ActionCommand.ShowToast(message, duration))


    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
        _actionCommand.postValue(ActionCommand.ShowToastRes(message, duration))


    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
        _actionCommand.postValue(ActionCommand.ShowSnackBar(message, duration))


    fun showSnackBar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_SHORT) =
        _actionCommand.postValue(ActionCommand.ShowSnackBarRes(message, duration))

}