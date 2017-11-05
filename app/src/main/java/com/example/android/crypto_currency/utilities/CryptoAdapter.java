package com.example.android.crypto_currency.utilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.crypto_currency.R;

import java.util.List;

/**
 * Created by my computer on 27-Oct-17.
 */

public class CryptoAdapter extends ArrayAdapter<Crypto> {

    /**
     * @param context The current context. Used to inflate the layout file.
     * @param cryptos A List of Word objects to display in a list
     */
    public CryptoAdapter(Activity context, List<Crypto> cryptos){
        // Here, I initialize the ArrayAdapter's internal storage for the context and the list.

        super(context, 0, cryptos);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Gets the Word object from the ArrayAdapter at the appropriate position
        Crypto crypto = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.crypto_list, parent, false);
        }
//
//        if(position % 2 == 0){
//            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.crypto_list);
//
//            layout.setBackgroundColor(convertView.getContext().getColor(R.color.colorPrimary)
//                    .getColour(R.color.colorPrimary));
//        }

        TextView currency = (TextView) convertView.findViewById(R.id.currency);
        TextView btc = (TextView) convertView.findViewById(R.id.btc);
        TextView eth = (TextView) convertView.findViewById(R.id.eth);

        currency.setText(crypto.getCurrency());
        btc.setText(crypto.getBtc()+"");
        eth.setText(crypto.getEth()+"");



        return convertView;

    }
}
