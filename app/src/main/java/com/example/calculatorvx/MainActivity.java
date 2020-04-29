package com.example.calculatorvx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.sql.Struct;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ButtonStats> buttonStats = new ArrayList<>();

    private class ButtonStats {
        public String id;
        public String text;
        public String tag;
        public boolean alwaysVisible;

        public ButtonStats(String id, String text, String tag, boolean alwaysVisible) {
            this.alwaysVisible = alwaysVisible;
            this.id = id;
            this.text = text;
            this.tag = tag;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void buildButtonPanel() {
        GridLayout buttonPanel = findViewById(R.id.buttonPanelSimple);
        Button b = new Button(this);
        buttonPanel.addView(b);

    }

}
