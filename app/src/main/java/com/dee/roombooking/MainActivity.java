package com.dee.roombooking;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView checkin, checkout, txtResult, txtPrice;
    Spinner SpinnerRoom;
    EditText etAdult, etChild, etRoom;
    Button btnCalculate;
    int year1, year2;
    int month1, month2;
    int day1, day2;
    int adult, children, room;
    double total, roomPrice, vat, grossTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding
       checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.checkout);
        txtResult = findViewById(R.id.txtResult);
        txtPrice = findViewById(R.id.txtPrice);
        SpinnerRoom = findViewById(R.id.SpinnerRoom);
        etAdult = findViewById(R.id.etAdult);
        etChild = findViewById(R.id.etChildren);
        etRoom = findViewById(R.id.etRoom);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Data in array for spinner (Different Rooms)
        final String rooms[] = {"Delux - Rs. 2000", "Presidential - Rs. 5000", "Premium - Rs. 4000"};
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rooms
        );
        SpinnerRoom.setAdapter(adapter);

        // For Checkin Date
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckinDate();
            }
        });

        // For Checkout Date
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCheckoutDate();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validation
                if (TextUtils.isEmpty(etAdult.getText())) {
                    etAdult.setError("Please enter number of adult.");
                    return;
                }
                if (TextUtils.isEmpty(etChild.getText())) {
                    etChild.setError("Please enter number of children.");
                    return;
                }
                if (TextUtils.isEmpty(etRoom.getText())) {
                    etRoom.setError("Please enter number of rooms.");
                    return;
                }


                Calendar c = Calendar.getInstance();
                c.set(year1, month1, day1);
                Calendar c1 = Calendar.getInstance();
                c1.set(year2, month2, day2);

                //Calculating the difference in selected dates
                long diffMillis = c1.getTimeInMillis() - c.getTimeInMillis();
                long daysDiff = (diffMillis / (24 * 60 * 60 * 1000));


                adult = Integer.parseInt(etAdult.getText().toString());
                children = Integer.parseInt(etChild.getText().toString());
                room = Integer.parseInt(etRoom.getText().toString());


                // Defining the price of different rooms
                if (SpinnerRoom.getSelectedItem() == "Deluxe - Rs. 2000") {
                    txtPrice.setText("2000");
                }
                if (SpinnerRoom.getSelectedItem() == "Presidential - Rs. 5000") {
                    txtPrice.setText("5000");
                }
                if (SpinnerRoom.getSelectedItem() == "Premium - Rs. 4000") {
                    txtPrice.setText("4000");
                }

                roomPrice = Double.parseDouble(txtPrice.getText().toString());

                total = roomPrice * room * daysDiff;
                vat = total * 0.13;
                grossTotal = total + vat;

                // Displaying Result
                txtResult.setText("Total: Rs." + total + "\n" + "Vat Rs.:" + vat + "\n" + "Gross Total: Rs." + grossTotal);
            }
        });
    }


    // Show selected checkin dates to users
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "Check in Date: \n" + (month + 1) + "/" + dayOfMonth + "/" + year;
        day1 = dayOfMonth;
        month1 = month;
        year1 = year;
        checkin.setText(date);
    }

    private void loadCheckinDate() {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, year, month, day);
        datePickerDialog.show();
    }


    // Show selected checkout dated to users
    private void loadCheckoutDate() {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Check out Date: \n" + (month + 1) + "/" + dayOfMonth + "/" + year;
                day2 = dayOfMonth;
                month2 = month;
                year2 = year;
                checkout.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
