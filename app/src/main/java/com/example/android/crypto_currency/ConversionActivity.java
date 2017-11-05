package com.example.android.crypto_currency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.crypto_currency.utilities.Crypto;

public class ConversionActivity extends AppCompatActivity {

    int quantity = 1;

    Crypto crypto_currency = MainActivity.current_currency;

    EditText quantity_txt, quantity_value_txt;
    TextView type_txt, type_txtvw;
    TextView type_value, currency_view;

    int type = 1;//btc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        quantity_txt = (EditText) findViewById(R.id.quantity_txt);
        type_txt = (TextView) findViewById(R.id.type_txt);
        type_value = (TextView) findViewById(R.id.type_value);
        type_txtvw = (TextView) findViewById(R.id.type_txtvw);
        currency_view = (TextView) findViewById(R.id.currency_view);
        quantity_value_txt = (EditText) findViewById(R.id.quantity_value_txt);

        //set deafult, bitcoin
        quantity_value_txt.setText((crypto_currency.getBtc() * quantity) + "");
        type_txtvw.setText("Bitcoin");
        type_value.setText((crypto_currency.getBtc() * quantity) + " ");
        currency_view.setText(crypto_currency.getCurrency() + "");



        quantity_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(type == 1){
                    quantity_value_txt.setText((crypto_currency.getBtc() * quantity) + "");
                }
                else{
                    quantity_value_txt.setText((crypto_currency.getEth() * quantity) + "");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(type == 1){
                    quantity_value_txt.setText((crypto_currency.getBtc() * quantity) + "");
                }
                else{
                    quantity_value_txt.setText((crypto_currency.getEth() * quantity) + "");

                }
            }
        });

    }


    public void increment(View view) {
        quantity++;
        Button decrease_btn = (Button) findViewById(R.id.decrease_btn);
        decrease_btn.setEnabled(true);

        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
        if(quantity < 2) {
            Button decrease_btn = (Button) findViewById(R.id.decrease_btn);
            decrease_btn.setEnabled(false);
        }
    }

    private void display(int quantity) {
        quantity_txt.setText(""+quantity);
    }

    public void change(View view){
        if (type == 1) {
            type = 2;
            type_txt.setText("ETH");
            type_txtvw.setText("ETH");
            currency_view.setText(crypto_currency.getCurrency() + "");
            type_value.setText(crypto_currency.getEth() + " ");
            quantity_value_txt.setText((crypto_currency.getEth() * quantity) + "");


        }
        else {
            type = 1;
            type_txt.setText("BTC");
            type_txtvw.setText("Bitcoin");
            currency_view.setText(crypto_currency.getCurrency() + "");
            type_value.setText(crypto_currency.getBtc() + " ");
            quantity_value_txt.setText((crypto_currency.getBtc() * quantity) + "");



        }
    }

    public void getDefault(){}
}
