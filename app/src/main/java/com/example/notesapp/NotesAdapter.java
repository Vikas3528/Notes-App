package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private ArrayList<String> notesList;
    private OnNoteClickListener onNoteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(String note);
    }

    public NotesAdapter(ArrayList<String> notesList, OnNoteClickListener onNoteClickListener) {
        this.notesList = notesList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view, onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteTextView.setText(notesList.get(position));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteTextView;

        public NoteViewHolder(@NonNull View itemView, OnNoteClickListener onNoteClickListener) {
            super(itemView);
            noteTextView = itemView.findViewById(R.id.textViewNote);

            itemView.setOnClickListener(v -> onNoteClickListener.onNoteClick(noteTextView.getText().toString()));
        }
    }
}
