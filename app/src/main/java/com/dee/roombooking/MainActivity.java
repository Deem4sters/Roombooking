package com.dee.roombooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView checkin, checkout, type, txtResult, txtFirst, txtSecond;
    Spinner SpinnerRoom;
    Button btnCalculate;
    EditText etAdult, etChildren, etRoom;
    Boolean a1, a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.checkout);
        type = findViewById(R.id.type);
        txtResult = findViewById(R.id.txtResult);
        txtFirst = findViewById(R.id.txtFirst);
        txtSecond = findViewById(R.id.txtSecond);
        SpinnerRoom = findViewById(R.id.SpinnerRoom);
        btnCalculate = findViewById(R.id.btnCalculate);
        etAdult = findViewById(R.id.etAdult);
        etChildren = findViewById(R.id.etChildren);
        etRoom = findViewById(R.id.etRoom);

      checkin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            loadDatePickercheckin();
            a1=true;
          }
      });

      checkout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              loadDatePickercheckout();
              a2=true;
          }
      });
        String room[] = {"Select room type", "Deluxe -Rs. 2000", "Presidential -Rs. 5000", "Premium- Rs. 4000"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, room);
        SpinnerRoom.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int adult, child, room;
               double total,price,vat,GrossTotal;

                adult = Integer.parseInt(etAdult.getText().toString());
                child = Integer.parseInt(etChildren.getText().toString());
                room = Integer.parseInt(etRoom.getText().toString());


                if(TextUtils.isEmpty(etAdult.getText()))
                {
                    etAdult.setError("Enter no of Adults");
                    return;
                }
                if(TextUtils.isEmpty(etChildren.getText()))
                {
                    etChildren.setError("Enter no of children");
                    return;
                }
                if(TextUtils.isEmpty(etRoom.getText()))
                {
                    etRoom.setError("Enter no of rooms");
                    return;
                }


                if (SpinnerRoom.getSelectedItem() == "Deluxe -Rs. 2000") {
                    txtSecond.setText("2000");


                }
                if (SpinnerRoom.getSelectedItem() == "Presidential -Rs. 5000") {
                    txtSecond.setText("5000");


                }
                if (SpinnerRoom.getSelectedItem() == "Premium- Rs. 4000") {
                    txtSecond.setText("4000");

                }
                price=Integer.parseInt(txtSecond.getText().toString());
                total=price*room;
                vat=0.13*total;
                GrossTotal = total +vat;

                //int diff=Integer.parseInt(tvSecond.getText().toString())-Integer.parseInt(tvFirst.getText().toString());


                txtFirst.setText("Total: Rs." + total+"\n"+"Vat Rs.:"+vat+"\n"+"Gross Total: Rs."+GrossTotal);

            }
        });

    }
    private void loadDatePickercheckin() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }





    private void loadDatePickercheckout() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);


        txtSecond.setText(c.getTime() + "");


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month + 1;

        if (a1 == true) {
            String date = "Checking Date: " + month + "/" + dayOfMonth + "/" + year;

            //SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
            // // try {
            //     Date dateCheckout=format.parse(date);
            //  }
            //  catch (ParseException e)
            //  {
            //      e.printStackTrace();
            //  }
            checkin.setText(date);

            a1 = false;
            return;

        }
        if (a2 = true) {
            String date = "Check out Date: " + month + "/" + dayOfMonth + "/" + year;
            checkout.setText(date);
            // SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
            // try {
            //     Date dateCheckout=format.parse(date);
            // }
            // catch (ParseException e)
            // {
            //     e.printStackTrace();
            // }

            a2 = false;
            return;
        }
    }
}
