package com.epitech.cashmanagerinterface

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.epitech.cashmanagerinterface.common.MainScreenView
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * @ExperimentalPermissionsApi: is used to indicate that the code uses experimental api, used to access and manage runtime permissions.
 * @onCreate: is called when the activity is created. It sets up the app's UI and handles the camera permission request
 * CashManagerInterfaceTheme: defines the overall look of the app (colors, fonts and other visual elements)
 */
@ExperimentalPermissionsApi
class MainActivity() : ComponentActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView()
//            CashManagerInterfaceTheme {
//                androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Bottom
//                    ) {
//                        CameraPermission()
//                    }
//                }
//            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
