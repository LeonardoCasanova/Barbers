package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Barber;

/**
 * Created by Leonardo on 23/02/2017.
 */

public class EsperaAdapter  extends RecyclerView.Adapter<EsperaAdapter.EsperaViewHolder> {
    protected static final String TAG = "livroandroid";

    private final List<Barber> espera;

    private final Context context;



    public EsperaAdapter(List<Barber> espera, Context context) {
        this.espera = espera;
        this.context = context;
    }


    @Override
    public EsperaAdapter.EsperaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro, viewGroup, false);
        EsperaViewHolder holder = new EsperaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final EsperaAdapter.EsperaViewHolder holder, final int position) {

     Barber b = espera.get(position);
     holder.progress.setVisibility(View.VISIBLE);

        Picasso.with(context).load(b.UrlFoto).fit().into(holder.img2, new Callback() {
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return espera.size();
    }

    public static class EsperaViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;

        ImageView img2;
        ProgressBar progress;

        public EsperaViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder


            img2 = (ImageView) view.findViewById(R.id.img2);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
        }

    }

}





