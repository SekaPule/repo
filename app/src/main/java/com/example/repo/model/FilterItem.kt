package com.example.repo.model

import android.os.Parcel
import android.os.Parcelable
import com.example.repo.data.db.entities.FilterItemEntity

data class FilterItem(
    val id: Int,
    val name: String?,
    var check: Boolean
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

    companion object CREATOR : Parcelable.Creator<FilterItem> {
        override fun createFromParcel(parcel: Parcel): FilterItem {
            return FilterItem(parcel)
        }

        override fun newArray(size: Int): Array<FilterItem?> {
            return arrayOfNulls(size)
        }
    }

}

fun FilterItem.toFilterItemEntity(): FilterItemEntity = FilterItemEntity(
    id = id,
    name = name,
    check = check
)