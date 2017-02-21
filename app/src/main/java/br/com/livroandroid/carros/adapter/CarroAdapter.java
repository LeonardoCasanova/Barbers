package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import br.com.livroandroid.carros.domain.Carro;
import livroandroid.lib.utils.MaterialUtils;

// Herda de RecyclerView.Adapter e declara o tipo genérico <CarroAdapterV2.CarrosViewHolder>
public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarrosViewHolder> {
    protected static final String TAG = "livroandroid";
    private final List<Barber> barbers;

    private final Context context;

    private CarroOnClickListener carroOnClickListener;

    public CarroAdapter(Context context, List<Barber> barbers, CarroOnClickListener carroOnClickListener) {
        this.context = context;
        this.barbers = barbers;
        this.carroOnClickListener = carroOnClickListener;
    }





    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro, viewGroup, false);

       // CardView cardView = (CardView) view.findViewById(R.id.card_view);

        // Cria o ViewHolder
        CarrosViewHolder holder = new CarrosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position) {

        // Atualiza a view

        Barber b = barbers.get(position);


        int valor= Integer.parseInt(b.clientes_espera);

        for  (int i = 0; i < valor; i++){


            Picasso.with(context).load(b.UrlFoto).fit().into(holder.img, new Callback() {
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

        holder.tNome.setText(b.clientes_espera);
        holder.progress.setVisibility(View.VISIBLE);




               // Click
        if (carroOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carroOnClickListener.onClickCarro(holder.itemView, position); // A variável position é final
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return barbers.size();
    }

    public interface CarroOnClickListener {
        public void onClickCarro(View view, int idx);
    }

    // ViewHolder com as views
    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ImageView img2;
        ProgressBar progress;


        public CarrosViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder

            img = (ImageView) view.findViewById(R.id.img);

            progress = (ProgressBar) view.findViewById(R.id.progressImg);
        }
    }
}
