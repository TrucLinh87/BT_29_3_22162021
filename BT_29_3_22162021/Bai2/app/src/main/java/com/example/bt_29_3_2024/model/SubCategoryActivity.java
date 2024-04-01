package com.example.bt_29_3_2024.model;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.bt_29_3_2024.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class SubCategoryActivity extends AppCompatActivity {

    public static List<SubCategory> subCategoryList;
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;
    ImageButton imageButton;
    ArrayList<SubCategory> temp = new ArrayList<>();
    Executor executor;
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        rcvCategory = findViewById(R.id.rcv_category);
        imageButton = findViewById(R.id.imgBtn_syc);

        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://www.themealdb.com/images/media/meals/020z181619788503.jpg",
                "1",
                "1"
        ));
        subCategoryAdapter = new SubCategoryAdapter(this, temp);
        rcvCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(
                        getApplicationContext(),
                        2
                );
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        RecyclerView.LayoutManager layoutManager =
//                new LinearLayoutManager(
//                        getApplicationContext(),
//                        LinearLayoutManager.VERTICAL,
//                        false
//                );

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);

        imageButton.setOnClickListener(v -> getData());
        subCategoryAdapter.notifyDataSetChanged();
        MeowBottomNavigation bottomNavigation = findViewById(R.id.me);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.infoprofile));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.shopping));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.blurcircular));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.settings));
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Toast.makeText(SubCategoryActivity.this, "Item click" + model.getId(), Toast.LENGTH_SHORT).show();
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                String name;
                if(model.getId() == 1){
                    name="Home";
                }else if(model.getId() == 2){
                    name="Profile";
                }else if(model.getId() == 3){
                    name="Shopping";
                }
                else if(model.getId() == 4){
                    name="Support";
                }else if(model.getId() == 5){
                    name="Settings";
                }
//                bottomNavigation.setCount(5, "9");
                return null;
            }
        });
    }


    public void getData() {
        new AllSubCategories(1).getAllSubCategories();
        temp.clear();
        temp.addAll(AllSubCategories.ans);
        if (cnt != 0) {
            subCategoryAdapter.notifyDataSetChanged();
        }
        cnt++;
    }
}