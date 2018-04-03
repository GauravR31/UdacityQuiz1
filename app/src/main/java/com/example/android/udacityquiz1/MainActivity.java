package com.example.android.udacityquiz1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button nextButton;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        usernameEditText = (EditText) findViewById(R.id.edit_text_username);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        descriptionEditText = (EditText) findViewById(R.id.edit_text_description);

        usernameEditText.requestFocus();

        if (savedInstanceState != null) {
            String username = savedInstanceState.getString("username");
            String email = savedInstanceState.getString("email");
            String description = savedInstanceState.getString("description");

            if (username != null) {
                usernameEditText.setText(username, TextView.BufferType.EDITABLE);
            }

            if (email != null) {
                emailEditText.setText(email, TextView.BufferType.EDITABLE);
            }

            if (description != null) {
                descriptionEditText.setText(description, TextView.BufferType.EDITABLE);
            }
        }

        nextButton = (Button) findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

                sharedPrefEditor.putString(getString(R.string.sharedpref_username), usernameEditText.getText().toString());
                sharedPrefEditor.putString(getString(R.string.sharedpref_email), emailEditText.getText().toString());
                sharedPrefEditor.putString(getString(R.string.sharedpref_description), descriptionEditText.getText().toString());

                sharedPrefEditor.apply();

                usernameEditText.clearComposingText();
                emailEditText.clearComposingText();
                descriptionEditText.clearComposingText();

                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_button) {
            startActivity(new Intent(MainActivity.this, DetailsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        usernameEditText = (EditText) findViewById(R.id.edit_text_username);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        descriptionEditText = (EditText) findViewById(R.id.edit_text_description);

        String usernameString = usernameEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String descriptionString = descriptionEditText.getText().toString();

        outState.putString("username", usernameString);
        outState.putString("email", emailString);
        outState.putString("description", descriptionString);
        super.onSaveInstanceState(outState);
    }
}
