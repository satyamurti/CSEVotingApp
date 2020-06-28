package com.mrspd.csevotingapp.model

import android.os.Parcel
import android.os.Parcelable

data class VoteStats(
    var name: String?,
    var count: Int

) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(count)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VoteStats> {
        override fun createFromParcel(parcel: Parcel): VoteStats {
            return VoteStats(parcel)
        }

        override fun newArray(size: Int): Array<VoteStats?> {
            return arrayOfNulls(size)
        }
    }
}
