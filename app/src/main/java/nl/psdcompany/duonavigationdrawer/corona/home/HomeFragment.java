package nl.psdcompany.duonavigationdrawer.corona.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import nl.psdcompany.duonavigationdrawer.example.R;
import nl.psdcompany.duonavigationdrawer.corona.utils.BasilHelper;

public class HomeFragment extends Fragment {

    private TextView tvTotalConfirmed, tvTotalDeaths, tvTotalRecovered, tvLastUpdated, tvLabelTimeUpdated;
    private ProgressBar progressBar;
    private LinearLayout bottom_sheet;
    private BottomSheetBehavior sheetBehavior;
    private FloatingActionButton fabOpenSheetBtn;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_new, container, false);


        // call view
        tvTotalConfirmed = root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered = root.findViewById(R.id.tvTotalRecovered);
        tvLastUpdated = root.findViewById(R.id.tvLastUpdated);
        tvLabelTimeUpdated = root.findViewById(R.id.tvLabelTimeUpdated);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshHome);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData();

            }
        });

        bottom_sheet = root.findViewById(R.id.bottom_sheet1);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        fabOpenSheetBtn = root.findViewById(R.id.fab_openSheet);

        fabOpenSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    fabOpenSheetBtn.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_down_arrow_lg));
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    fabOpenSheetBtn.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_up_arrow_lg));
                }
            }
        });

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    fabOpenSheetBtn.setBackgroundResource(R.drawable.ic_down_arrow_lg);
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                    fabOpenSheetBtn.setBackgroundResource(R.drawable.ic_down_arrow_lg);

                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        WebView myWebView = (WebView) root.findViewById(R.id.home_webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public");


        // call Volley
        getData();

        return root;
    }

    private String getDate(long milliSecond) {
        // Mon, 23 Mar 2020 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, \tdd MMM yyyy \thh:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);

        BasilHelper.showProgress(getActivity());

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://corona.lmao.ninja/v2/all";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                BasilHelper.hideProgress();
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    String Cases = commaAdd(jsonObject.getString("cases"));//+"\nToday's case:\n(+" + jsonObject.getString("todayCases") + ")";
                    String dead = commaAdd(jsonObject.getString("deaths"));//+"\nToday's case:\n(+" + jsonObject.getString("todayDeaths") + ")";
                    tvTotalConfirmed.setText(Cases);
                    tvTotalDeaths.setText(dead);
                    tvTotalRecovered.setText(commaAdd(jsonObject.getString("recovered")));
                    tvLastUpdated.setText("Last Updated" + "\n" + getDate(jsonObject.getLong("updated")));
                    tvLabelTimeUpdated.setText("Last updated on:\t" + milliSecToMinute(jsonObject.getString("updated")));
                } catch (JSONException e) {
                    e.printStackTrace();
                    BasilHelper.hideProgress();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
                swipeRefreshLayout.setRefreshing(false);
                BasilHelper.hideProgress();
            }
        });

        queue.add(stringRequest);


    }

    private String commaAdd(String number) {


        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatted = formatter.format(amount);

        return formatted;
    }

    private String milliSecToMinute(String millisec) {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy \t HH:mm:ss");

        long milliSeconds = Long.parseLong(millisec);
        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}




