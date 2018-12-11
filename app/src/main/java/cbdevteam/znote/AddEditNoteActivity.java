package cbdevteam.znote;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "cbdevteam.znote.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "cbdevteam.znote.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "cbdevteam.znote.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "cbdevteam.znote.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;

    Button saveButton;

    @Override
    public void onBackPressed() {

            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            if (title.trim().isEmpty() && description.trim().isEmpty()) {


            }

            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESCRIPTION, description);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1){
                data.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, data);
            finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);

        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }else{
            setTitle("");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();

                if (title.trim().isEmpty() && description.trim().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddEditNoteActivity.this)
                            .setTitle("Empty Fields!")
                            .setIcon(R.drawable.znoteiconsmall)
                            .setMessage("Please enter a subject or note description.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //code if they select "yes"
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra(EXTRA_TITLE, title);
                data.putExtra(EXTRA_DESCRIPTION, description);

                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                if (id != -1){
                    data.putExtra(EXTRA_ID, id);
                }
                setResult(RESULT_OK, data);
                finish();
            }
        });


    }
}