package nl.psdcompany.duonavigationdrawer.example.Others;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import nl.psdcompany.duonavigationdrawer.example.R;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.map_webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadUrl("https://gabrielcesar.github.io/covid/");
//        myWebView.loadUrl("https://www.covid19globalinfo.com/");
        myWebView.loadUrl("https://bing.com/covid");
        return view;

    }
}