package com.example.phonebook.database

import com.example.phonebook.domain.model.ColorModel
import com.example.phonebook.domain.model.NEW_PHONE_ID
import com.example.phonebook.domain.model.PhoneModel

class DbMapper {
    // Create list of NoteModels by pairing each note with a color
    fun mapPhones(
        phoneDbModels: List<PhoneDbModel>,
        colorDbModels: Map<Long, ColorDbModel>
    ): List<PhoneModel> = phoneDbModels.map {
        val colorDbModel = colorDbModels[it.colorId]
            ?: throw RuntimeException("Color for colorId: ${it.colorId} was not found. Make sure that all colors are passed to this method")

        mapPhone(it, colorDbModel)
    }

    // convert NoteDbModel to NoteModel
    fun mapPhone(noteDbModel: PhoneDbModel, colorDbModel: ColorDbModel): PhoneModel {
        val color = mapColor(colorDbModel)
//        val isCheckedOff = with(noteDbModel) { if (canBeCheckedOff) isCheckedOff else null }
        return with(noteDbModel) { PhoneModel(id, name, phone, tag, color) }
    }

    // convert list of ColorDdModels to list of ColorModels
    fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel> =
        colorDbModels.map { mapColor(it) }

    // convert ColorDbModel to ColorModel
    fun mapColor(colorDbModel: ColorDbModel): ColorModel =
        with(colorDbModel) { ColorModel(id, name, hex) }

    // convert NoteModel back to NoteDbModel
    fun mapDbPhone(phones: PhoneModel): PhoneDbModel =
        with(phones) {
//            val canBeCheckedOff = isCheckedOff != null
//            val isCheckedOff = isCheckedOff ?: false
            if (id == NEW_PHONE_ID)
                PhoneDbModel(
                    name = name,
                    phone = phone,
                    tag = tag,
//                    isCheckedOff = isCheckedOff,
                    colorId = color.id,
                    isInTrash = false
                )
            else
                PhoneDbModel(id, name, phone, tag, color.id, false)
        }
}