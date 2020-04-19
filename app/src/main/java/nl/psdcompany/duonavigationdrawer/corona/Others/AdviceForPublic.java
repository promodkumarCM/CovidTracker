package nl.psdcompany.duonavigationdrawer.corona.Others;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import nl.psdcompany.duonavigationdrawer.example.R;


public class AdviceForPublic extends Fragment {

    private ListView lsPublicAdvice;
    ArrayList<String> advices;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advice_public, container, false);
        lsPublicAdvice=view.findViewById(R.id.lsPublicAdvice);

        advices=new ArrayList<>();
        advices.add("ASK WHO");
        advices.add("Be Ready for coronavirus");
        advices.add("Protect yourself and others from getting sick");
        advices.add("COVID-19 Home care");
        advices.add("COVID-19: Pregnancy & breastfeeding");
        advices.add("How to cope with stress during 2019-nCoV outbreak");
        CustomAdapter adapter=new CustomAdapter(getActivity(),advices);
        lsPublicAdvice.setAdapter(adapter);

        lsPublicAdvice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                }

        });



        return view;

    }
}