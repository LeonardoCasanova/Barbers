package br.com.livroandroid.carros.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.CustomGridViewAdapter;
import br.com.livroandroid.carros.domain.Barber;
import br.com.livroandroid.carros.domain.BarberService;


public class ImagensFragment extends BaseFragment {

    private  GridView gridView;
    private List<Barber> barber;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskBarber();
    }

    private void taskBarber(){
        new GetImagesTask().execute();
    }


   private class GetImagesTask extends AsyncTask<Void,Void,List<Barber>>{

       @Override
       protected List<Barber> doInBackground(Void... params) {

       try{
           return BarberService.getBarbers(getContext());
       }catch (IOException e){
           Log.e("projeto",e.getMessage(),e);
           return null;
       }

       }


     protected void onPostExecute(List<Barber>barbers){

     }


   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_layout, container, false);

        gridView   = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(new CustomGridViewAdapter(getContext(), barbers));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
              Barber b = barber.get(position);

                Toast.makeText(getContext(), "Cliente em Espera: " + b.clientes_espera ,Toast.LENGTH_SHORT).show();

            }
        });





        return view;

    }

}
