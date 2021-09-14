package com.jwhh.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initializeDisplayContent();


    }

    private void initializeDisplayContent() {
        //1. Get a reference to the listview
        ListView listNotes = findViewById(R.id.list_notes);

        //2. We need to get the content we need to put inside of the ListView. For that we will use a DataManager.
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        //3. Put the data into an adapter.
        ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, notes);

        //4. Now just call listNotes.setAdapter to associate the adapter with the ListView
        listNotes.setAdapter(adapterNotes);

        /* 5. The setOnItemClickListener has an interface and the code below starts a new instance of the interface
        called an anonymous interface accessible only to the listNotes method. */
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //6. Creating an Intent - In order to launch an activity you need an intent.
                /*
                Error Originally Shows: Cannot resolve constructor.
                Reason: the first argument type is 'anonymous' indicating that "this" reference refers to an anonymous class.
                We're implementing this interface on OnItemClickListener() using an anonymous class. What we want is a reference to the nested
                NoteListActivity so we need to quality the path reference in the first argument to: 'NoteListActivity.this'
                 */
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                //7. Now to actually start the activity, we need to call the method startActivity passing in the intent.
                startActivity(intent);
            }
        });

    }
}