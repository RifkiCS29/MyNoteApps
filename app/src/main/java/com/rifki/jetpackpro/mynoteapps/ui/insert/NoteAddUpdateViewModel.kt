package com.rifki.jetpackpro.mynoteapps.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mynoteapps.database.Note
import com.rifki.jetpackpro.mynoteapps.repository.NoteRepository

//penghubung antara Repository dengan Activity .
class NoteAddUpdateViewModel(application: Application): ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}