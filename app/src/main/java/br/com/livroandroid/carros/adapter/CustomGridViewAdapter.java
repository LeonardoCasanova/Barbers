package br.com.livroandroid.carros.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Barber;


/**
 * Created by Leonardo on 17/02/2017.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Barber> {

    private Context context;
    private int layoutResourceId;
     private List<Barber> barbers;


    public CustomGridViewAdapter(Context context, int layoutResourceId, List<Barber> barbers) {
        super(context, layoutResourceId, barbers);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.barbers = barbers;

    }


    public void setGridData(ArrayList<Barber> barbers) {
        this.barbers = barbers;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.barbers != null ? this.barbers.size() : 0;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.item_text);
            holder.imageView = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }


        Barber b = barbers.get(position);

        holder.titleTextView.setText(Html.fromHtml(b.clientes_atendimento));

        Picasso.with(context).load(b.UrlFoto).into(holder.imageView);
        return row;


    }
}