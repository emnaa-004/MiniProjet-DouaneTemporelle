package com.fsegt.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView tvResume;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        tvResume = findViewById(R.id.tv_resume_adem);
        btnApprouver = findViewById(R.id.btn_approuver_adem);
        btnRefuser = findViewById(R.id.btn_refuser_adem);

        // mjib data mil act1
        Intent intent = getIntent();
        String nom = intent.getStringExtra(Constantes.KEY_NOM);
        int anneeNaissance = intent.getIntExtra(Constantes.KEY_NAISSANCE, 0);
        int anneeDest = intent.getIntExtra(Constantes.KEY_DEST, 0);
        String motif = intent.getStringExtra(Constantes.KEY_MOTIF);

        final int ageTemporel = anneeDest - anneeNaissance;

        String resume = "Nom : " + nom + "\n" +
                "Âge temporel : " + ageTemporel + " ans\n" +
                "Motif : " + motif;
        tvResume.setText(resume);

        // annulation de button si il ya un erreur
        if (motif.equals("Modification du passé")) {
            if (estPremier(ageTemporel)) {
                btnApprouver.setEnabled(false);
                btnApprouver.setText("Approuver (Désactivé)");
            }
        }

        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(VerificationActivity.this, ResultActivity.class);
                intentResult.putExtra(Constantes.KEY_VERDICT, true);
                intentResult.putExtra(Constantes.KEY_NOM, nom);
                startActivity(intentResult);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(VerificationActivity.this, ResultActivity.class);
                intentResult.putExtra(Constantes.KEY_VERDICT, false);
                intentResult.putExtra(Constantes.KEY_NOM, nom);
                startActivity(intentResult);
            }
        });
    }

    private boolean estPremier(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}