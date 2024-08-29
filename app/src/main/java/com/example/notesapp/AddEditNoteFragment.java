package com.example.notesapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNoteFragment extends Fragment {

    private EditText editTextNote;
    private NotesDatabaseHelper dbHelper;
    private String currentNote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);

        editTextNote = view.findViewById(R.id.editTextNote);
        Button saveButton = view.findViewById(R.id.saveButton);
        dbHelper = new NotesDatabaseHelper(getActivity());

        if (getArguments() != null) {
            currentNote = getArguments().getString("note");
            editTextNote.setText(currentNote);
        }

        saveButton.setOnClickListener(v -> {
            String noteText = editTextNote.getText().toString();
            if (TextUtils.isEmpty(noteText)) {
                Toast.makeText(getActivity(), "Please enter a note", Toast.LENGTH_SHORT).show();
            } else {
                if (currentNote == null) {
                    dbHelper.addNote(noteText);
                } else {
                    dbHelper.updateNote(currentNote, noteText);
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new NotesListFragment())
                        .commit();
            }
        });

        return view;
    }
}
