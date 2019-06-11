package com.example.googlebooks_kotlin.entities.typeconverter

import androidx.room.TypeConverter
import com.example.googlebooks_kotlin.entities.VolumeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


class VolumeInfoTypeConverter {

    @Inject
    lateinit var gson: Gson

    @TypeConverter
    fun stringToVolumeInfo(data: String?): VolumeInfo? {
        if (data == null) {
            return null
        }

        val listType = object : TypeToken<VolumeInfo>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun volumeInfoToString(volumeInfo: VolumeInfo): String {
        return gson.toJson(volumeInfo)
    }
}