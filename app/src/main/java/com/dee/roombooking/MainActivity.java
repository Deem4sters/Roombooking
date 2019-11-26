package com.dee.roombooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView checkinDate, checkoutDate, txtSpinner;
    Spinner SpinnerRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkinDate=findViewById(R.id.checkinDate);
        checkoutDate=findViewById(R.id.checkoutDate);
        txtSpinner=findViewById(R.id.txtSpinner);
        SpinnerRoom=findViewById(R.id.SpinnerRoom);

        checkinDate=
    }
}
