package nl.psdcompany.duonavigationdrawer.example.country;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import nl.psdcompany.duonavigationdrawer.example.R;

public class CountryFragment extends Fragment implements OnRvClick {

    RecyclerView rvCovidCountry;
    ProgressBar progressBar;
    TextView tvTotalCountry;

    private static final String TAG = CountryFragment.class.getSimpleName();
    ArrayList<CountryApiModel> covidCountries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        // call view
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);
        tvTotalCountry = root.findViewById(R.id.tvTotalCountries);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidCountry.getContext(), DividerItemDecoration.VERTICAL);
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        // call Volley method
        getDataFromServer();


    }

    private void showRecyclerView() {
        CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(covidCountries, this, getActivity());
        rvCovidCountry.setAdapter(covidCountryAdapter);
    }

    private void showSelectedCovidCountry(CountryApiModel covidCountry) {
        Intent covidCovidCountryDetail = new Intent(getActivity(), CovidCountryDetail.class);
        covidCovidCountryDetail.putExtra("EXTRA_COVID", covidCountry);
        startActivity(covidCovidCountryDetail);
    }

    private void getDataFromServer() {
        String  url = "https://www.trackcorona.live/api/countries/";

        covidCountries = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);


                    try {
                        JSONObject countryResponse = new JSONObject(response);

                        JSONArray jsonArray = countryResponse.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                      /*      covidCountries.add(new CovidCountry(
                                    data.getString("country"), data.getString("cases"),
                                    data.getString("todayCases"), data.getString("deaths"),
                                    data.getString("todayDeaths"), data.getString("recovered"),
                                    data.getString("active"), data.getString("critical"),
                                    data.getJSONObject("countryInfo")
                            ));*/

                            covidCountries.add(new CountryApiModel(
                                    data.getString("location"), data.getString("country_code"),
                                    data.getDouble("latitude"), data.getDouble("longitude"),
                                    data.getInt("confirmed"), data.getInt("dead"),
                                    data.getInt("recovered"), data.getString("updated")
                            ));

                        }
                        tvTotalCountry.setText(jsonArray.length() + " countries");
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void afterClick(CountryApiModel country) {

        showSelectedCovidCountry(country);

    }
}
