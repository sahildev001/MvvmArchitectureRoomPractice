package com.example.mvvmarchitectureroompractice.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mvvmarchitectureroompractice.R;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String  EXTRA_TITLE = "com.example.mvvmarchitectureroompractice.view.EXTRA_TITLE";
    public static final String  EXTRA_DESCRIPTION = "com.example.mvvmarchitectureroompractice.view.EXTRA_DESCREPTION";
    public static final String  EXTRA_PRIORITY = "com.example.mvvmarchitectureroompractice.view.EXTRA_PRIORITY";
    public static final String  EXTRA_ID = "com.example.mvvmarchitectureroompractice.view.EXTRA_ID";

        private EditText ettitle,etdescripton;
        private NumberPicker numberPickerPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ettitle = findViewById(R.id.etTittle);
        etdescripton = findViewById(R.id.etDescription);
        numberPickerPriority = findViewById(R.id.NumberPickerPriority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("EditNote");
            ettitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etdescripton.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));

        }else {
        setTitle("Add Note");}
    }

    private void saveNote(){
        String title = ettitle.getText().toString();
        String description = etdescripton.getText().toString();
        int priority = numberPickerPriority.getValue();
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}