package com.example.android.udacityquiz1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView usernameTextView;
    TextView emailTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        usernameTextView = (TextView) findViewById(R.id.text_view_username);
        emailTextView = (TextView) findViewById(R.id.text_view_email);
        descriptionTextView = (TextView) findViewById(R.id.text_view_description);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String usernameString = sharedPreferences.getString(getString(R.string.sharedpref_username), null);
        String emailString = sharedPreferences.getString(getString(R.string.sharedpref_email), null);
        String descriptionString = sharedPreferences.getString(getString(R.string.sharedpref_description), null);

        if (usernameString != null)
            usernameTextView.setText(usernameString);

        if (emailString != null)
            emailTextView.setText(emailString);

        if (descriptionString != null)
            descriptionTextView.setText(descriptionString);
    }
}
