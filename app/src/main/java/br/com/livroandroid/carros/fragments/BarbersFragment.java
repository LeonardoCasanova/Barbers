package br.com.livroandroid.carros.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.activity.CarroActivity;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Barber;
import br.com.livroandroid.carros.domain.BarberService;
import livroandroid.lib.utils.AndroidUtils;

public class BarbersFragment extends BaseFragment {
    protected RecyclerView recyclerView;

    private List<Barber> barbers;
    private GridLayoutManager mLayoutManager;
    private String tipo;
    private SwipeRefreshLayout swipeLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barbers, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);




     swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
     swipeLayout.setOnRefreshListener(OnRefreshListener());

        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);


        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Atualiza ao fazer o gesto Swipe To Refresh
                if (AndroidUtils.isNetworkAvailable(getContext())) {
                    taskBarbers(true);
                } else {
                    alert(R.string.error_conexao_indisponivel);
                }
            }
        };
    }



    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskBarbers(false);
    }

    private void taskBarbers(boolean pullToRefresh) {
        startTask("carros", new GetBarbersTask(), pullToRefresh ? R.id.swipeToRefresh : R.id.progress);
    }


    private class GetBarbersTask implements TaskListener<List<Barber>> {
        @Override
        public List<Barber> execute() throws Exception {

            Thread.sleep(200);
            // Busca os carros em background (Thread)
            return BarberService.getBarbers(getContext());
        }


        @Override
        public void updateView(List<Barber> barbers) {

            if (barbers != null) {
                BarbersFragment.this.barbers = barbers;
                // Atualiza a view na UI Thread
                recyclerView.setAdapter(new CarroAdapter(getContext(), barbers, onClickBarber()));
                //toast("update ("+carros.size()+"): " + carros);
            }


        }



        @Override
        public void onError(Exception exception) {

        }

        @Override
        public void onCancelled(String cod) {

        }


        private CarroAdapter.CarroOnClickListener onClickBarber() {
            return new CarroAdapter.CarroOnClickListener() {
                @Override
                public void onClickCarro(View view, int idx) {
                    Barber b = barbers.get(idx);

                   Toast.makeText(getContext(), "Cliente em Espera: " + b.clientes_atendimento,Toast.LENGTH_SHORT).show();



                    /*
                    Intent intent = new Intent(getContext(), CarroActivity.class);
                    intent.putExtra("carro", c);
                    startActivity(intent);
                    */
                }
            };
        }

    }
}