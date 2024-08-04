package com.example.givinglandv1.data.model.posts

import android.os.Parcel
import android.os.Parcelable

data class Post(
    val id: Int,
    val name: String,
    val purpose: String,
    val description: String,
    val location_id: Int,
    val images: List<Image>?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createTypedArrayList(Image.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(purpose)
        parcel.writeInt(location_id)
        parcel.writeTypedList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}
