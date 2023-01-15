package com.example.repo.model

import android.os.Parcel
import android.os.Parcelable

data class News(
    val id: Int,
    val category: List<String>?,
    val title: String?,
    val organization: String?,
    val date: String?,
    val location: String?,
    val phoneNumbers: List<String>?,
    val titleDescription: String?,
    val description: String?,
    val subDescription: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeStringList(category)
        parcel.writeString(title)
        parcel.writeString(organization)
        parcel.writeString(date)
        parcel.writeString(location)
        parcel.writeStringList(phoneNumbers)
        parcel.writeString(titleDescription)
        parcel.writeString(description)
        parcel.writeString(subDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }

}
