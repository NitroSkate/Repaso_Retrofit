package com.example.moviedex.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movie_aux")
data class MovieAux(
    var Title : String = "N/A",
    var Year : String = "N/A",
    var Released : String = "N/A",
    var Runtime : String = "N/A",
    var Genre : String = "N/A",
    @field:Json(name = "Director")
    var Director : String = "N/A",
    @field:Json(name = "Actors")
    var Actors : String = "N/A",
    var Plot : String = "N/A",
   // @field:Json(name = "imdbID")
   // var imdbID : String = "N/A",
    var Language : String = "N/A",
    var imdbRating : String = "N/A",
    var Poster : String = "N/A"
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

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
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Year)
        parcel.writeString(Released)
        parcel.writeString(Runtime)
        parcel.writeString(Genre)
        parcel.writeString(Director)
        parcel.writeString(Actors)
        parcel.writeString(Plot)
        parcel.writeString(Language)
        parcel.writeString(imdbRating)
        parcel.writeString(Poster)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieAux> {
        override fun createFromParcel(parcel: Parcel): MovieAux {
            return MovieAux(parcel)
        }

        override fun newArray(size: Int): Array<MovieAux?> {
            return arrayOfNulls(size)
        }
    }
}