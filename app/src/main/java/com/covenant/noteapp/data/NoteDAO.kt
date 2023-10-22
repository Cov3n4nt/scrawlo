package com.covenant.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: NoteTable)

//Implement is_delete update to 1 here:
    @Update
    suspend fun archiveNote(note: NoteTable)

    @Update
    suspend fun updateNote(note: NoteTable)

    @Delete
    suspend fun deleteNote(note: NoteTable)

    @Query("SELECT * FROM note_table WHERE is_deleted = 1 ORDER BY id ASC")
    fun getArchivedNotes(): Flow<List<NoteTable>>

    @Query("SELECT * FROM note_table WHERE is_deleted = 0 ORDER BY id ASC")
    fun getNotes(): Flow<List<NoteTable>>
}