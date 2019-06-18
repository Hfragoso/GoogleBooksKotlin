package com.example.googlebooks_kotlin.entities.typeconverter

import androidx.room.TypeConverter
import com.example.googlebooks_kotlin.entities.VolumeInfo
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


class VolumeInfoTypeConverter {

    var gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create()

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