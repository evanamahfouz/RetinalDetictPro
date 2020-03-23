package com.example.ratinadeticpro.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity
data class User(
    val ID_User: String,
    val email: String,
    val pass: String,
    val gender: String,
    val age: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ID_User)
        parcel.writeString(email)
        parcel.writeString(pass)
        parcel.writeString(gender)
        parcel.writeString(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}