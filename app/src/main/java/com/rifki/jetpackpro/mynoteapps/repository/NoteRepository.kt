package com.rifki.jetpackpro.mynoteapps.repository

import android.app.Application
import androidx.paging.DataSource
import com.rifki.jetpackpro.mynoteapps.database.Note
import com.rifki.jetpackpro.mynoteapps.database.NoteDao
import com.rifki.jetpackpro.mynoteapps.database.NoteRoomDatabase
import com.rifki.jetpackpro.mynoteapps.helper.SortUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//penghubung antara database atau resource data dengan ViewModel
class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes(sort: String): DataSource.Factory<Int, Note> {
        val query = SortUtils.getSortedQuery(sort)
        return mNoteDao.getAllNotes(query)
    }

    fun insert(note: Note) {
        executorService.execute { mNoteDao.insert(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNoteDao.update(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNoteDao.delete(note) }
    }
}