package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mInputNote;
    public static final String APP_PREFERENCES = "MyNote";
    private SharedPreferences myNoteSharedPref;
    private static String NOTE_TEXT = "note_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        saveValues();
        getDateFromSharedPref();
    }

    public void getDateFromSharedPref() {
        String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
        mInputNote.setText(noteTxt);
        Toast.makeText(MainActivity.this,
                noteTxt,
                Toast.LENGTH_LONG).show();
    }

    public void saveValues() {
        Button mBtnSaveNote = findViewById(R.id.btnSaveNote);
        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
                String noteTxt = mInputNote.getText().toString();
                myEditor.putString(NOTE_TEXT, noteTxt);
                myEditor.apply();
                Toast.makeText(MainActivity.this,
                        R.string.valueSave,
                        Toast.LENGTH_LONG).show();
        }
        });
    }

    public void initView() {
        mInputNote = findViewById(R.id.inputNote);
        myNoteSharedPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
    }
}
