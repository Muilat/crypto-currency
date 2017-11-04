package com.example.android.crypto_currency.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by my computer on 28-Oct-17.
 */

public class CryptoLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Crypto>> {

    String currencies[] = {"NGN","USD","EUR","BRL","RUB","GHS","CAD","KRW","JPY","SAR","GBP","THB","AUD","ZAR","INR","CNY","HKD","CHF","IDR","SGD" };


    //default constructor for the loader
    public CryptoLoader(Context context) {
        super(context);

    }


    /**
     * This is the method of the AsyncTaskLoader that will load and parse the JSON data
     * from github in the background.
     *
     * @return  crypto data from cryptocompare Api as an array of Cryptos.
     *         null if an error occurs
     */
    @Override
    public ArrayList<Crypto> loadInBackground() {

        String requestString = "";


        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            URL cryptoRequestUrl = NetworkUtils.buildUrl(requestString);
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(cryptoRequestUrl);
        } catch (IOException e) {
            // TODO Handle the IOException
            Log.v("loadInBackground", "Unable to load in background");
        }


        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<Crypto> cryptos = extractCryptoCurrency(jsonResponse);


        return cryptos;
//
    }

    /* this is requured to tell the loader to start doing the background load

     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }


    /*
    //return a list of{@link Developer} object that has been built up from
    * @param jsonResponse The json string parse.

    */
    public  ArrayList<Crypto> extractCryptoCurrency(String jsonResponse){

        //create an empty arraylist that we can start adding cryto currency to
        ArrayList<Crypto> cryptos = new ArrayList<>();

        /* try to parse the sample response, if there is an erroir
           //in the way it is array exception should be trhrown
         */
        try{
            //build up array of cryptos with the corresponding data

            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONObject ethObject = baseJsonResponse.getJSONObject("ETH");
            JSONObject btcObject = baseJsonResponse.getJSONObject("BTC");

            Log.v(baseJsonResponse.toString(),"Lets c");



            for(int i = 0; i < currencies.length; i++) {

                String currency = currencies[i];
                float eth = ethObject.getLong(currencies[i]);
                float btc = btcObject.getLong(currencies[i]);

                Crypto crypto = new Crypto(currency,eth,btc);

                cryptos.add(crypto);

            }



        } catch (JSONException e){
            /* if any error is thrown when executing the try;
            //catch the excdeption so the app doesnt crash
             */
            Log.v("cryptoLoader","problem parsing the crypto JSON results", e);

        }


        return cryptos;
    }


}



