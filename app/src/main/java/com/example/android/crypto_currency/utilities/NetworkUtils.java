package com.example.android.crypto_currency.utilities;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by my computer on 28-Oct-17.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    static String crypto_compare_url_string = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH%2CBTC&tsyms=USD%2CEUR%2CNGN%2CRUB%2CCAD%2CJPY%2CGBP%2CAUD%2CINR%2CHKD%2CIDR%2CSGD%2CCHF%2CCNY%2CZAR%2CTHB%2CSAR%2CKRW%2CGHS%2CBRL";


    private static URL CRYPTO_COMPARE_REQUEST_URL;



    /**
     * Builds the URL used to talk to the cryptocompare
     *
     * @return The URL to use to query the cryptocompare server.
     */
    public static URL buildUrl(String queryString) {
        Log.v(TAG, "Built Query String " + queryString);
        String crypto_compare_url_string_with_query = crypto_compare_url_string + queryString;

        Uri builtUri = Uri.parse(crypto_compare_url_string_with_query).buildUpon()
//                .appendQueryParameter(QUERY_PARAM, queryString)

                .build();

        CRYPTO_COMPARE_REQUEST_URL = null;
        try {
            CRYPTO_COMPARE_REQUEST_URL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + CRYPTO_COMPARE_REQUEST_URL);
        return CRYPTO_COMPARE_REQUEST_URL;
    }



    /**
     * This method returns the entire result from the HTTP response.
     *
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    @Nullable
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
