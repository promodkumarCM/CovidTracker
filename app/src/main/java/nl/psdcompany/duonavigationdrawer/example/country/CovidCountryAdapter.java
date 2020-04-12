package nl.psdcompany.duonavigationdrawer.example.country;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nl.psdcompany.duonavigationdrawer.example.R;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> {

    private ArrayList<CountryApiModel> covidCountries;
    private OnRvClick listener;
    private Context context;

    public CovidCountryAdapter(ArrayList<CountryApiModel> covidCountries, CountryFragment listener, Context context){
        this.covidCountries = covidCountries;
        this.listener = listener;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryApiModel covidCountry = covidCountries.get(position);


        holder.tvCountryName.setText(covidCountry.getLocation());
        holder.tvAffected.setText("Confirmed :"+covidCountry.getConfirmed());
        holder.tvDead.setText("Dead :"+covidCountry.getDead());
        holder.tvRecovered.setText("Recovered :"+covidCountry.getRecovered());
        try
        {
            Glide.with(context).load(covidCountry.getCountryFlag()).into(holder.ivCountryFlag);
            Log.d("promod", "onBindViewHolder: ");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases, tvCountryName,tvAffected,tvDead,tvRecovered;
        ImageView ivCountryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
            tvAffected=itemView.findViewById(R.id.tvAffected);
            tvDead=itemView.findViewById(R.id.tvDead);
            tvRecovered=itemView.findViewById(R.id.tvRecovered);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   listener.afterClick(covidCountries.get(getAdapterPosition()));
                }
            });
        }
    }
}
