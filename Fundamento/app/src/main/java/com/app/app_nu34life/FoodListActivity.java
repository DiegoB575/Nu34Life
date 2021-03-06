package com.app.app_nu34life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.app_nu34life.retrofit.Food;
import com.app.app_nu34life.retrofit.JsonPlaceHolder;
import com.app.app_nu34life.retrofit.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodListActivity extends AppCompatActivity implements ListaRecipeAdapter.OnNoteListener {
    private static final String TAG ="FOOD";
    private BottomNavigationView mainNav;
    private ArrayList<Food> dataset = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaAllFoodAdapter listacancionAdapter;
    private Button addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        mainNav=(BottomNavigationView) findViewById(R.id.navbar_item);
        recyclerView =(RecyclerView) findViewById(R.id.recycler2);
        listacancionAdapter = new ListaAllFoodAdapter(this);
        addbutton=(Button)findViewById(R.id.btn_AddRecipe);
        recyclerView.setAdapter(listacancionAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        Menu menu = mainNav.getMenu();
        MenuItem menuItem= menu.getItem(1);
        menuItem.setChecked(true);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://nu34life-api-boot.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        addbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddFoodActivity.class);
                startActivity(intent);
            }
        });

        obtenerDatos();

        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_navbar:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.author_navbar:
                        break;
                    case R.id.account_navbar:
                        Intent intent3 = new Intent(getApplicationContext(),AccountActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
        onBackPressed();


    }

    private void obtenerDatos(){
        JsonPlaceHolder jsonPlaceHolderApi=retrofit.create(JsonPlaceHolder.class);
        Call<List<Food>> call=jsonPlaceHolderApi.getFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(response.isSuccessful()) {

                    List<Food> canciones = response.body();
                    ArrayList<Food> listaCancion = (ArrayList) canciones;
                    dataset=(ArrayList) canciones;
                    listacancionAdapter.adicionarListaCancion(listaCancion,FoodListActivity.this::oneNoteClick);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }
    @Override
    public void onBackPressed(){

    }

    @Override
    public void oneNoteClick(int position){
    }
}
