package com.example.notesapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NotesDatabaseHelper dbHelper;
    private ArrayList<String> notesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dbHelper = new NotesDatabaseHelper(getActivity());

        // Fetch notes from SQLite
        notesList = dbHelper.getAllNotes();
        notesAdapter = new NotesAdapter(notesList, note -> {
            // Handle note click (edit or delete)
            Bundle bundle = new Bundle();
            bundle.putString("note", note);
            AddEditNoteFragment editNoteFragment = new AddEditNoteFragment();
            editNoteFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, editNoteFragment)
                    .commit();
        });

        recyclerView.setAdapter(notesAdapter);

        FloatingActionButton fab = view.findViewById(R.id.fabAddNote);
        fab.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new AddEditNoteFragment())
                    .commit();
        });

        return view;
    }
}
