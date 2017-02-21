package br.com.livroandroid.carros.domain;

import java.io.Serializable;

/**
 * Created by Leonardo on 15/02/2017.
 */

public class Barber implements Serializable {
 private static final long serialVersionUID = 6601006766832473959L;

    public String clientes_atendimento;
    public String clientes_espera;
    public String UrlFoto;


    @Override
    public String toString() {
        return "Barber{" +
                "clientes_atendimento='" + clientes_atendimento + '\'' +
                ", clientes_espera='" + clientes_espera + '\'' +
                ", UrlFoto='" + UrlFoto + '\'' +
                '}';
    }
}
