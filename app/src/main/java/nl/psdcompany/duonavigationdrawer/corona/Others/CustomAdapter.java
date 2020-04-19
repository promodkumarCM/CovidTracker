package nl.psdcompany.duonavigationdrawer.corona.Others;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import nl.psdcompany.duonavigationdrawer.example.R;

class CustomAdapter implements ListAdapter {
   ArrayList<String> arrayList;
   Context context;

   public CustomAdapter(Context context, ArrayList<String> arrayList) {
      this.arrayList=arrayList;
      this.context=context;
   }
   @Override
   public boolean areAllItemsEnabled() {
      return false;
   }
   @Override
   public boolean isEnabled(int position) {
      return true;
   }
   @Override
   public void registerDataSetObserver(DataSetObserver observer) {
   }
   @Override
   public void unregisterDataSetObserver(DataSetObserver observer) {
   }
   @Override
   public int getCount() {
      return arrayList.size();
   }
   @Override
   public Object getItem(int position) {
      return position;
   }
   @Override
   public long getItemId(int position) {
      return position;
   }
   @Override
   public boolean hasStableIds() {
      return false;
   }
   @Override
   public View getView(final int position, View convertView, ViewGroup parent) {


      if(convertView==null) {
         LayoutInflater layoutInflater = LayoutInflater.from(context);
         convertView=layoutInflater.inflate(R.layout.custom_list_who, null);
         convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent=new Intent(context,PdfView.class);
               String filename="";
               switch (position) {
                  case 0:
                     filename = "0.pdf";
                     break;
                  case 1:
                     filename = "1.pdf";
                     break;
                  case 2:
                     filename = "2.pdf";
                     break;
                  case 3:
                     filename = "3.pdf";
                     break;
                  case 4:
                     filename = "4.pdf";
                     break;
                  case 5:
                     filename="5.pdf";
                     break;
                  default:
                     break;
               }
               intent.putExtra("FileName",filename);
               context.startActivity(intent);
            }
         });
         TextView tittle=convertView.findViewById(R.id.who_list);
         tittle.setText(arrayList.get(position));
      }
      return convertView;
   }
   @Override
   public int getItemViewType(int position) {
      return position;
   }
   @Override
   public int getViewTypeCount() {
      return arrayList.size();
   }
   @Override
   public boolean isEmpty() {
      return false;
   }
}