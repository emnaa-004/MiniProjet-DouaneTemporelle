package com.fsegt.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // norbet les elements bil interface
        LinearLayout layout = findViewById(R.id.layout_result_sima);
        TextView tvMessage = findViewById(R.id.tv_message_sima);
        Button btnNouveau = findViewById(R.id.btn_nouveau_sima);

        // njib les donne mil act2
        Intent intent = getIntent();
        boolean verdict = intent.getBooleanExtra(Constantes.KEY_VERDICT, false);
        String nom = intent.getStringExtra(Constantes.KEY_NOM);

        // changer couleur de background selon le changement
        if (verdict) {
            layout.setBackgroundColor(Color.parseColor("#4CAF50"));
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            layout.setBackgroundColor(Color.parseColor("#F44336"));
            tvMessage.setText(nom + " est en état d'arrestation temporelle.");
        }

        // button bch narj3u lil act1
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRetour = new Intent(ResultActivity.this, MainActivity.class);
                intentRetour.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentRetour);
                finish();
            }
        });
    }
}