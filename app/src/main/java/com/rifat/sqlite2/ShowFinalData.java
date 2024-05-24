package com.rifat.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowFinalData extends AppCompatActivity {
    TextView tvDisplay;
    DataBaseHelper dbHelper;
    SearchView searchView;
    ListView listView;
    EditText edText;
    ArrayList<HashMap<String,String>> arrayList;

    HashMap<String,String>hashMap;

    public static boolean ALLDATA=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_data);
        // tvDisplay=findViewById(R.id.tvDisplay);
        dbHelper=new DataBaseHelper(ShowFinalData.this);
        searchView=findViewById(R.id.searchView);
        listView=findViewById(R.id.listView);
        edText=findViewById(R.id.edText);


        loadData(dbHelper.getAllData());
        //   Cursor cursor=dbHelper.SearchDataByName("rifat");

        //   tvDisplay.setText("Total Data : "+cursor.getCount());



/*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String key=searchView.getQuery().toString();
        loadData(dbHelper.SearchDataByName(key));
        return false;
    }
});*/


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String key=searchView.getQuery().toString();
                loadData(dbHelper.SearchDataByName(key));

                return false;
            }
        });


        edText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String key=edText.getText().toString();
                loadData(dbHelper.SearchDataByName(key));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }// on Create Ends ===============



    //==============================================================

    public void loadData(Cursor cursor){
        arrayList=new ArrayList<>();

        if (cursor !=null && cursor.getCount()>0){

            while(cursor.moveToNext()){
                int id =cursor.getInt(0);
                String name=cursor.getString(1);
                String gmail=cursor.getString(2);
                String password=cursor.getString(3);
                String mobile=cursor.getString(4);
                String semester=cursor.getString(5);

                //       tvDisplay.append("\n\n id : "+id+ "  Name: "+name+ "  Gmail : "+gmail+ " \n password : "+password+ "  mobile : "+mobile);
                hashMap=new HashMap<>();
                hashMap.put("id",""+id);
                hashMap.put("name",""+name);
                hashMap.put("gmail",""+gmail);
                hashMap.put("password",""+password);
                hashMap.put("mobile",""+mobile);
                hashMap.put("semester",""+semester);
                arrayList.add(hashMap);
            }
            listView.setAdapter(new MyAdapter());
        }
    }
    //==============================================================



    //==============================================================
    public class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {

            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View myView=inflater.inflate(R.layout.item,parent,false);

            TextView tvName=myView.findViewById(R.id.tvName);
            TextView tvGmail=myView.findViewById(R.id.tvGmail);
            TextView tvPassword=myView.findViewById(R.id.tvPassword);
            TextView tvMobile=myView.findViewById(R.id.tvMobile);
            TextView tvSemester=myView.findViewById(R.id.tvSemester);

            Button  tvUpdate=myView.findViewById(R.id.tvUpdate);
            Button  tvDelete=myView.findViewById(R.id.tvDelete);

            hashMap=arrayList.get(position);
            String id =hashMap.get("id");
            String name=hashMap.get("name");
            String gmail=hashMap.get("gmail");
            String password=hashMap.get("password");
            String mobile=hashMap.get("mobile");
            String semester=hashMap.get("semester");

            tvName.setText(name);
            tvGmail.setText(gmail);
            tvPassword.setText(password);
            tvMobile.setText(mobile);
            tvSemester.setText(semester);





            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ALLDATA==true) dbHelper.deleteData(id);
                    Toast.makeText(ShowFinalData.this,"Data has Deleted",Toast.LENGTH_LONG).show();

                    loadData(dbHelper.getAllData());
                }
            });




            return myView;
        }
    }

    //==============================================================

    //==============================================================



}