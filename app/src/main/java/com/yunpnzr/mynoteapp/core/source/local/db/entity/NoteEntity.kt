package com.yunpnzr.mynoteapp.core.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class NoteEntity {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
    @ColumnInfo("title")
    var title: String? = null
    @ColumnInfo("content")
    var content: String? = null
    @ColumnInfo("date")
    var date: String? = null
}