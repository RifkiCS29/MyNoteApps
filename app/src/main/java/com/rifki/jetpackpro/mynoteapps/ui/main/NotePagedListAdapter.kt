package com.rifki.jetpackpro.mynoteapps.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rifki.jetpackpro.mynoteapps.database.Note
import com.rifki.jetpackpro.mynoteapps.databinding.ItemNoteBinding
import com.rifki.jetpackpro.mynoteapps.ui.insert.NoteAddUpdateActivity

class NotePagedListAdapter(private val activity: Activity) : PagedListAdapter<Note, NotePagedListAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Note> = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldNote: Note, newNote: Note): Boolean {
                return oldNote.title == newNote.title && oldNote.description == newNote.description
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: Note, newNote: Note): Boolean {
               return oldNote == newNote
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val notes = getItem(position)
        if (notes != null) {
            holder.bind(notes)
        }
    }

    fun getSwipedData(swipedPosition: Int): Note? = getItem(swipedPosition)

    inner class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}