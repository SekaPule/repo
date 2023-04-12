package com.example.repo.presentation.base.model

import android.os.Parcel
import android.os.Parcelable

data class FilterView(
    val id: Int,
    val name: String?,
    var check: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeByte(if (check) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterView> {
        override fun createFromParcel(parcel: Parcel): FilterView {
            return FilterView(parcel)
        }

        override fun newArray(size: Int): Array<FilterView?> {
            return arrayOfNulls(size)
        }
    }

}