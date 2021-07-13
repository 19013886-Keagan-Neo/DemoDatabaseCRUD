package sg.edu.rp.c346.id19013886.demodatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = findViewById(R.id.tvID);
        etContent = findViewById(R.id.etContent);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setNoteContent(etContent.getText().toString());
                //dbh.updateNote(data);
                dbh.close();

                long updatedID = dbh.updateNote(data);

                if (updatedID != -1) {
                    Toast.makeText(EditActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "ID not found! Update fail!", Toast.LENGTH_SHORT).show();
                }
                //finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                //dbh.deleteNote(data.getId());

                long deletedID = dbh.deleteNote(data.getId());

                if (deletedID != -1) {
                    Toast.makeText(EditActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "ID not found! Delete fail!", Toast.LENGTH_SHORT).show();
                }

                //finish();
            }
        });

    }
}