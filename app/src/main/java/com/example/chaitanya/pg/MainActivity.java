package com.example.chaitanya.pg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   static int countt;

    RecyclerView recyclerView;
    JSONArray cc;

    Context context;
    RecyclerViewAdapter recyclerView_Adapter;
    private String name[];
    private String address[];
    private String price[];
    private String accomodation[];
    private String food[];
    private String location[];
    private String gender[];
    private String ac[];
    private String wifi[];
    private String mobileno[];

    JSONArray code;
    Bitmap [] imageid;
    private ProgressDialog pDialog;
    Bitmap decodedByte;
    String newcode;
    JSONParser jParser = new JSONParser();

    private static String url ="https://podgier-woman.000webhostapp.com/pgtable.php";

    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 1);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        if(countt != 1) {
            countt = 0;
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        context = getApplicationContext();

        gro g1 = new gro();
        g1.execute();
    }

    class gro extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please Wait....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            java.util.List<NameValuePair> pr = new ArrayList<NameValuePair>();


            JSONObject json = jParser.makeHttpRequest(url, "GET", pr);
            Log.d("jaaaaaaaaaaaaaaaakkk",""+countt);
            Log.d("hhhhhhhhhhhhhhhhee", " " + json);
            try {
                if(countt == 1){
                    Bundle b = getIntent().getExtras();
                    newcode=b.getString("daattaa");
                    Log.d("jaaaaaaaaaaaaaaaakkk",""+newcode);
                }

                if (countt == 0) {
                    code = json.getJSONArray("code");
                    name = new String[code.length()];
                    address = new String[code.length()];
                    price = new String[code.length()];
                    accomodation = new String[code.length()];
                    food = new String[code.length()];
                    location = new String[code.length()];
                    gender = new String[code.length()];
                    ac = new String[code.length()];
                    wifi = new String[code.length()];
                    mobileno = new String[code.length()];
                    imageid = new Bitmap[code.length()];

                    for (int i = 0; i < code.length(); i++) {
                        JSONObject c = code.getJSONObject(i);
                        //Log.d("codeeee "+i,c.toString());
                        //Log.d("struat", c.getString("base64"));
                        final String encodedString = c.getString("base64");
                        name[i] = c.getString("name");
                        address[i] = c.getString("address");
                        price[i] = c.getString("price");
                        accomodation[i] = c.getString("accomodation");
                        food[i] = c.getString("food");
                        location[i] = c.getString("location");
                        gender[i] = c.getString("gender");
                        ac[i] = c.getString("ac");
                        wifi[i] = c.getString("wifi");
                        mobileno[i] = c.getString("mobileno");

                        final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1);
                        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageid[i] = decodedByte;


                    }
                } else if(countt == 1 ){
                    cc = new JSONArray(newcode);
                    name = new String[cc.length()];
                    address = new String[cc.length()];
                    price = new String[cc.length()];
                    accomodation = new String[cc.length()];
                    food = new String[cc.length()];
                    location = new String[cc.length()];
                    gender = new String[cc.length()];
                    ac = new String[cc.length()];
                    wifi = new String[cc.length()];
                    mobileno = new String[cc.length()];
                    imageid = new Bitmap[cc.length()];

                    for (int i = 0; i < cc.length(); i++) {
                        JSONObject d = cc.getJSONObject(i);
                        //Log.d("codeeee "+i,c.toString());
                        //Log.d("struat", c.getString("base64"));
                        final String encodedString = d.getString("base64");
                        name[i] = d.getString("name");
                        address[i] = d.getString("address");
                        price[i] = d.getString("price");
                        accomodation[i] = d.getString("accomodation");
                        food[i] = d.getString("food");
                        location[i] = d.getString("location");
                        gender[i] = d.getString("gender");
                        ac[i] = d.getString("ac");
                        wifi[i] = d.getString("wifi");
                        mobileno[i] = d.getString("mobileno");

                        final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",") + 1);
                        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageid[i] = decodedByte;

                    }


                }

            } catch (JSONException e) {
                Log.d("fjf", "fgf" + e);
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
                recyclerView_Adapter = new RecyclerViewAdapter(context, address, price, name, imageid);
                recyclerView.invalidate();
                recyclerView_Adapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerView_Adapter);
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.baaar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            Intent i = new Intent(this,filtr.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.srt){
            Toast.makeText(this, "sort items", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
