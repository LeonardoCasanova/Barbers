package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.HttpHelper;

/**
 * Created by Leonardo on 15/02/2017.
 */

public class BarberService {
    private static final String URL = "http://casanovadigital.96.lt/api/cliente_atendimento.php";
    private static final boolean LOG_ON = false;
    private static final String TAG = "BarberService";

    /*
    public static List<Barber> getBarbers(Context context) throws IOException {
        String url = URL;
        String json = HttpHelper.doGet(url);
        List<Barber> barbers = parserJSON(context, json);
        return barbers;
    }
*/

    public static List<Barber> getBarbers(Context context) throws IOException {
        String url = "http://casanovadigital.96.lt/api/cliente_espera.php";
        String json = HttpHelper.doGet(url);
        List<Barber> espera = parserJSON(context, json);
        return espera;
    }


    private static List<Barber> parserJSON(Context context, String json) throws IOException {

        List<Barber> espera = new ArrayList<Barber>();
        try {


            JSONObject jsonObject = new JSONObject(json);

            Barber b = new Barber();

            int teste = Integer.parseInt(jsonObject.getString("clientes_espera"));

            for (int i = 0; i <teste; i++) {
                // Lê as informações de cada carro
                b.clientes_atendimento = jsonObject.optString("clientes_espera");
                b.UrlFoto = jsonObject.optString("imagem_espera");

                if (LOG_ON) {
                    Log.d(TAG, "Carro " + b.clientes_espera + " > " + b.UrlFoto);
                }
                espera.add(b);
            }
            if (LOG_ON) {
                Log.d(TAG, espera.size() + " encontrados.");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return espera;
    }


/*
    private static List<Barber>  parserJSON(Context context, String json) throws IOException {

        List<Barber> barbers = new ArrayList<Barber>();
        try {



            JSONObject jsonObject = new JSONObject(json);

            Barber b = new Barber();

            int teste = Integer.parseInt(jsonObject.getString("clientes_atendimento"));

            for (int i = 0; i <teste; i++) {

                // Lê as informações de cada carro
                b.clientes_atendimento = jsonObject.optString("clientes_atendimento");
         //       b.clientes_espera = jsonBarber.optString("clientes_espera");
                b.UrlFoto = jsonObject.optString("imagem_atendimento");

                if (LOG_ON) {
                    Log.d(TAG, "Carro " + b.clientes_atendimento + " > " + b.UrlFoto);
                }
                barbers.add(b);
            }
            if (LOG_ON) {
                Log.d(TAG, barbers.size() + " encontrados.");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return barbers;
    }
*/
}
