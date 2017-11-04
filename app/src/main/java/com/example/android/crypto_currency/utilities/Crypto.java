package com.example.android.crypto_currency.utilities;

/**
 * Created by my computer on 27-Oct-17.
 */

public class Crypto {
    private String mCurrency;
    private float mBtc;
    private float mEth;

    Crypto(String currency, float btc, float eth){
        mCurrency = currency;
        mBtc = btc;
        mEth = eth;
    }

    public String getCurrency(){
        return mCurrency;
    }

    public float getBtc(){

        return mBtc;
    }

    public float getEth(){

        return mEth;
    }
}
