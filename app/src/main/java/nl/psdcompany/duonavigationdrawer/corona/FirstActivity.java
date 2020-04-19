package nl.psdcompany.duonavigationdrawer.corona;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

import nl.psdcompany.duonavigationdrawer.example.R;


public class FirstActivity extends FancyWalkthroughActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Break The Chain", "Know the Ways of Spreading", R.drawable.covid1);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Break The Chain", "Protect Yourself",R.drawable.covid2);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Break The Chain", "Keep Social Distancing",R.drawable.covid3);
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Break The Chain", "Stop Spreading",R.drawable.covid4);
       FancyWalkthroughCard fancywalkthroughCard5 = new FancyWalkthroughCard("Break The Chain", "Stay Clean",R.drawable.covid5);



        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(1000,1000,0,0,0,0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(1000,1000,0,0,0,0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(1000,1000,0,0,0,0);
        fancywalkthroughCard4.setBackgroundColor(R.color.white);
        fancywalkthroughCard4.setIconLayoutParams(1000,1000,0,0,0,0);
       fancywalkthroughCard5.setBackgroundColor(R.color.white);
       fancywalkthroughCard5.setIconLayoutParams(1000,1000,0,0,0,0);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);
        pages.add(fancywalkthroughCard5);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }


        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.colorAccent);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorAccent);
        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(FirstActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);

    }
}