package com.example.anonifydemo.ui.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
//import androidx.credentials.exceptions.GetCredentialCancellationException
//import androidx.credentials.exceptions.GetCredentialProviderConfigurationException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

interface Utils {

    companion object {
        const val TAG = "Anonify: Utils"
    }

    fun toast(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun handleFailure(context: Context? = null, e: Exception){
        when(e){
            is FirebaseAuthUserCollisionException -> {
                toast(context!!, "Email address is already registered with another user")
            }
//            is GetCredentialProviderConfigurationException -> {
//                toast(context, "No Google Account Found, Please Add a Gmail Account")
//            }
//            is GetCredentialCancellationException -> {
//
//            }
            is IllegalStateException -> {
                toast(context!!, "Invalid email or password, Try again!!")
            }

            is FirebaseAuthInvalidCredentialsException -> {
                toast(context!!, "Invalid email or password, Try again!!")
            }
            else -> {

                Log.e(TAG, "Exception $e")
            }
        }
    }

    fun log(message: String){
        Log.d(TAG, message)
    }



}