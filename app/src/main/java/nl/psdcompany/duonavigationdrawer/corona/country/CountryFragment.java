package nl.psdcompany.duonavigationdrawer.corona.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import nl.psdcompany.duonavigationdrawer.corona.myinterface.OnRvClick;
import nl.psdcompany.duonavigationdrawer.corona.utils.BasilHelper;
import nl.psdcompany.duonavigationdrawer.example.R;

public class CountryFragment extends Fragment implements OnRvClick, SearchView.OnQueryTextListener {

    RecyclerView rvCovidCountry;
    ProgressBar progressBar;
    TextView tvTotalCountry;
    SearchView countrySearch;

    private static final String TAG = CountryFragment.class.getSimpleName();
    ArrayList<CountryApiModel> covidCountries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Details : 183 Countries");
        View root = inflater.inflate(R.layout.fragment_country, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // call view
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);
        //tvTotalCountry = root.findViewById(R.id.tvTotalCountries);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));
        countrySearch=root.findViewById(R.id.edCountrySearch);
        countrySearch.setOnQueryTextListener(this);

        countrySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrySearch.setIconified(false);
            }
        });

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        rvCovidCountry.addItemDecoration(itemDecorator);

        // call Volley method
        getDataFromServer();


    }



    public static Comparator<CountryApiModel> CountrySort = new Comparator<CountryApiModel>() {

        public int compare(CountryApiModel s1, CountryApiModel s2) {

            int affected1 = s1.getTotalCases();
            int affected2 = s2.getTotalCases();

            /*For ascending order*/
            return affected2-affected1;

        }};

    private void showRecyclerView() {
        Collections.sort(covidCountries, CountrySort);
        CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(covidCountries, this, getActivity());
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }

    private void showRecyclerView(ArrayList<CountryApiModel> countryList){
        CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(countryList, this, getActivity());
        rvCovidCountry.setAdapter(covidCountryAdapter);


    }

    private void showSelectedCovidCountry(CountryApiModel covidCountry) {
//        Intent covidCovidCountryDetail = new Intent(getActivity(), CovidCountryDetail.class);
////        covidCovidCountryDetail.putExtra("EXTRA_COVID", covidCountry);
//        startActivity(covidCovidCountryDetail);
    }

    private void getDataFromServer() {

        BasilHelper.showProgress(getActivity());

        String  url = "https://api.thevirustracker.com/free-api?countryTotals=ALL";

        covidCountries = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                BasilHelper.hideProgress();

                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);


                    try {
                        JSONObject countryResponse = new JSONObject(response);
                        JSONArray jsonArray = countryResponse.getJSONArray("countryitems");
                        JSONObject countryObject=jsonArray.getJSONObject(0);

                        for (int i = 1; i <= 182; i++) {
                            JSONObject data = countryObject.getJSONObject("" + i);
                            covidCountries.add(new CountryApiModel(
                                    data.getString("title"), data.getString("code"),
                                    data.getInt("total_cases"), data.getInt("total_recovered"),
                                    data.getInt("total_unresolved"), data.getInt("total_deaths"),
                                    data.getInt("total_new_cases_today"), data.getInt("total_new_deaths_today"),
                                    data.getInt("total_active_cases"), data.getInt("total_serious_cases")
                            ));

                        }
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        BasilHelper.hideProgress();
                    }


                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressBar.setVisibility(View.GONE);
                        BasilHelper.hideProgress();
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void afterClick(CountryApiModel country) {

        showSelectedCovidCountry(country);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchCountry(newText);
        return false;
    }

    private void searchCountry(String keyword){

        CountryApiModel model;
        ArrayList<CountryApiModel> searchList=new ArrayList<>();
        for (int i=0;i<covidCountries.size();i++){
            model=covidCountries.get(i);
            if(model.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                searchList.add(model);
            }

        }

        showRecyclerView(searchList);
    }
}
