package com.fsegt.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNom, etNaissance, etDest;
    Spinner spinnerMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // norbtu les elements
        etNom = findViewById(R.id.et_nom_sima);
        etNaissance = findViewById(R.id.et_naissance_sima);
        etDest = findViewById(R.id.et_dest_sima);
        spinnerMotif = findViewById(R.id.spinner_motif_sima);
        btnInspecter = findViewById(R.id.btn_inspecter_sima);

        // naamlu liste deroulante
        String[] motifs = {"Tourisme", "Modification du passé", "Fuite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, motifs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMotif.setAdapter(adapter);

        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = etNom.getText().toString();
                String naissanceStr = etNaissance.getText().toString();
                String destStr = etDest.getText().toString();

                if (nom.isEmpty() || naissanceStr.isEmpty() || destStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                int anneeNaissance = Integer.parseInt(naissanceStr);
                int anneeDest = Integer.parseInt(destStr);
                String motif = spinnerMotif.getSelectedItem().toString();

                // naamlu intent pour lancer act2
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
                intent.putExtra(Constantes.KEY_NOM, nom);
                intent.putExtra(Constantes.KEY_NAISSANCE, anneeNaissance);
                intent.putExtra(Constantes.KEY_DEST, anneeDest);
                intent.putExtra(Constantes.KEY_MOTIF, motif);

                startActivity(intent);
            }
        });
    }
}