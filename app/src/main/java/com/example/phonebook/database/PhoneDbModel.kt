package com.example.phonebook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhoneDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "color_id") val colorId: Long,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean
) {
    companion object {
        val DEFAULT_PHONES = listOf(
            PhoneDbModel(1, "Pitchaya", "0971111111", "Friend", 1, false),
            PhoneDbModel(2, "Brandon", "0922222222", "Family", 1, false),
            PhoneDbModel(3, "Aceu", "093456789", "Work", 1, false),
            PhoneDbModel(4, "Timmy", "0944444441", "Work", 1, false),
        )
    }
}
