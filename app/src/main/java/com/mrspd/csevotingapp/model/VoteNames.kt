package com.mrspd.csevotingapp.model

import android.os.Parcel
import android.os.Parcelable

data class VoteNames(
    var name : String?,
    var description : String?,
    var option1 : String?,
    var option2 : String?,
    var option3 : String?,
    var option4 : String?,
    var option5 : String?,
    var option6 : String?,
    var option7 : String?,
    var option8 : String?,
    var option9 : String?,
    var option10 : String?
) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(option1)
        parcel.writeString(option2)
        parcel.writeString(option3)
        parcel.writeString(option4)
        parcel.writeString(option5)
        parcel.writeString(option6)
        parcel.writeString(option7)
        parcel.writeString(option8)
        parcel.writeString(option9)
        parcel.writeString(option10)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VoteNames> {
        override fun createFromParcel(parcel: Parcel): VoteNames {
            return VoteNames(parcel)
        }

        override fun newArray(size: Int): Array<VoteNames?> {
            return arrayOfNulls(size)
        }
    }
}
