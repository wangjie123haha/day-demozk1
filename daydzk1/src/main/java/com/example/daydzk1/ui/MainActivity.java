package com.example.daydzk1.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.daydzk1.R;
import com.example.daydzk1.fragment.frag1;
import com.example.daydzk1.fragment.frag2;
import com.example.daydzk1.fragment.frag3;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.fram_layout)
    FrameLayout framLayout;
    private FragmentManager manager;
    private RadioGroup rap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rap = findViewById(R.id.rap);
        //创建对象
        final frag1 frag1 = new frag1();
        final frag2 frag2 = new frag2();
        final frag3 frag3 = new frag3();
        //创建默认提交事务
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fram_layout, frag1).commit();
        rap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (i){
                    case  R.id.rap1:
                        transaction.replace(R.id.fram_layout, frag1);
                        break;
                    case  R.id.rap2:
                        transaction.replace(R.id.fram_layout, frag2);
                        break;
                    case  R.id.rap3:
                        transaction.replace(R.id.fram_layout, frag3);
                        break;
                }
                //提交事务
                transaction.commit();
            }
        });
    }
}

