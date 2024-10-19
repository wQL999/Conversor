package com.wen.conversor;
/*
 *@author: Wendell Valim Mendes
 * @ra: 1110482413005
 */
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner sp_spin;
    private Button btnCalc;
    private EditText txtIn;
    private TextView txtOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sp_spin = findViewById(R.id.sp_items);
        txtIn = findViewById(R.id.txtIn_bits);
        txtOut = findViewById(R.id.txtOut_view);
        btnCalc = findViewById(R.id.btn_calc);

        preecherSpinner();

        btnCalc.setOnClickListener( (obj) -> {
            String item = (String) sp_spin.getSelectedItem();
            long userInput = Long.parseLong(String.valueOf(txtIn.getText()));
            long ans =0;
            if(item.equals("Bytes")) {
                long bytes = userInput / 8;
                ans = bytes;
            } else if (item.equals("KB")) {
                long kb = (userInput / 8) / 1024;
                ans = kb;
            } else if (item.equals("MB")) {
                long mb = ((userInput / 8) / 1024) / 1024;
                ans = mb;
            } else if (item.equals("GB")) {
                long gb = (((userInput / 8) / 1024) / 1024) / 1024;
                ans = gb;
            } else {
                long tb = ((((userInput / 8) / 1024) / 1024) / 1024) / 1024;
                ans = tb;
            }

            txtOut.setText(String.valueOf(ans));
        });


    }

    private void preecherSpinner() {
        ArrayList<String> items = new ArrayList<>();
        items.add("Bytes");
        items.add("KB");
        items.add("MB");
        items.add("GB");
        items.add("TB");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_spin.setAdapter(adapter);
    }
}