package com.example.calculatorvx;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculatorPanelActivity extends AppCompatActivity {
    private boolean button2ndOn = false;

    private MainFrame mainFrame = new MainFrame();

    private ArrayList<Button> additionalButtons = new ArrayList<>();

    private View.OnClickListener panellistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String buttonTag = v.getTransitionName();
            mainFrame.buttonPressed(buttonTag);


            if (v.getId() == R.id.button_2nd) {
                swapButton();
            } else if (v.getId() == R.id.button_equal) {
                getResult();
            } else {
                updateExpression();
            }
        }
    };

    private void getResult() {
        TextView view = findViewById(R.id.debug);
        try {
            view.setText(this.mainFrame.printSimply());
        } catch (Exception e) {
            Toast toast = new Toast(this);
            toast.setText("表达式有误");
            toast.show();
        }

    }

    private void updateExpression() {
        TextView view = findViewById(R.id.debug);
        view.setText(this.mainFrame.printSelf());
    }

    private void swapButton() {
        button2ndOn = !button2ndOn;
        if (button2ndOn) {
            Button button = findViewById(R.id.button_sin);
            button.setText("arcsin");
            button = findViewById(R.id.button_cos);
            button.setText("arccos");
            button = findViewById(R.id.button_tan);
            button.setText("arctan");
            button = findViewById(R.id.button_2nd);
            button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            button = findViewById(R.id.button_ln);
            button.setText("log");
        } else {
            Button button = findViewById(R.id.button_sin);
            button.setText("sin");
            button = findViewById(R.id.button_cos);
            button.setText("cos");
            button = findViewById(R.id.button_tan);
            button.setText("tan");
            button = findViewById(R.id.button_2nd);
            button.setTextColor(getResources().getColor(R.color.Black));
            button = findViewById(R.id.button_ln);
            button.setText("ln");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_panel);
        initializeButtons();

    }

    private void initializeButtons() {
        GridLayout buttonPanel = findViewById(R.id.buttonPanelSimple);
        Button button = findViewById(R.id.button_2nd);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_sin);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_cos);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_tan);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_ln);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_xpow2);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_xpowy);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_xfracy);
        button.setOnClickListener(panellistener);
        additionalButtons.add(button);
        button = findViewById(R.id.button_clear);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_oops);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_lbracket);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_rbracket);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_divide);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_add);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_sub);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_mul);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_dot);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_equal);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_1);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_2);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_3);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_4);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_5);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_6);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_7);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_8);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_9);
        button.setOnClickListener(panellistener);
        button = findViewById(R.id.button_0);
        button.setOnClickListener(panellistener);
    }
}