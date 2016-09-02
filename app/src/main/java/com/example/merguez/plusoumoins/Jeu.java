package com.example.merguez.plusoumoins;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class Jeu extends AppCompatActivity {

    int alea;
    int choix;
    int compteur;
    int compteurDebut;
    Button jeuBtnValider;
    Button jeuBtnRejouer;
    Button jeuBtnQuitter;
    EditText jeuItSaisieValeur;
    TextView jeuTvTentativesRestantes;
    TextView jeuTvReponse;
    TextView jeuTvAfficherReponse;
    Switch jeuSwAfficherReponse;
    Spinner jeuSpChoixDifficulte;
    RelativeLayout jeuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);

        jeuLayout = (RelativeLayout) findViewById(R.id.jeuLayout);
        jeuBtnValider = (Button) findViewById(R.id.jeuBtnValider);
        jeuBtnRejouer = (Button) findViewById(R.id.jeuBtnRejouer);
        jeuBtnRejouer.setVisibility(View.GONE);
        jeuBtnQuitter = (Button) findViewById(R.id.jeuBtnQuitter);
        jeuBtnQuitter.setVisibility(View.GONE);
            jeuSpChoixDifficulte = (Spinner) findViewById(R.id.jeuSpChoixDifficulte);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.jeuSpChoixDifficulte, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jeuSpChoixDifficulte.setAdapter(adapter);
        jeuSpChoixDifficulte.setSelection(1);
        jeuItSaisieValeur = (EditText)findViewById(R.id.jeuTxtEntrerValeur);
        jeuTvTentativesRestantes = (TextView) findViewById(R.id.jeuTvTentativesRestantes);
        jeuTvReponse = (TextView) findViewById(R.id.jeuTvReponse);
        jeuTvTentativesRestantes.setText("Vous avez " + compteur + " essais");
        jeuSwAfficherReponse = (Switch) findViewById(R.id.jeuSwAfficherReponse);
        jeuTvAfficherReponse = (TextView) findViewById(R.id.jeuTvAfficherReponse);
        jeuTvAfficherReponse.setText("");
        alea  = new Random().nextInt(100)+1;

        jeuBtnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    jeuSpChoixDifficulte.setEnabled(false);
                    choix = Integer.parseInt(jeuItSaisieValeur.getText().toString());
                        if (choix > 100) {
                            Toast.makeText(getApplicationContext(),"Veuillez entrer un nombre entre 1 et 100",Toast.LENGTH_SHORT).show();
                        } else {
                            verification(choix);
                        }
                    } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un nombre entre 1 et 100",Toast.LENGTH_SHORT).show();
                }
            }
        });

        jeuBtnRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejouer();
            }
        });

        jeuBtnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        jeuSwAfficherReponse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    jeuTvAfficherReponse.setText("" + alea);
                } else {
                    jeuTvAfficherReponse.setText("");
                }
            }
        });

        jeuSpChoixDifficulte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (jeuSpChoixDifficulte.getSelectedItem().equals("Facile")) {
                    compteur = 10;
                    compteurDebut = 10;
                    jeuTvTentativesRestantes.setText("Il vous reste " + compteur + " essais");
                    rejouer();
                } else if (jeuSpChoixDifficulte.getSelectedItem().equals("Intermédiaire")) {
                    compteur = 7;
                    compteurDebut = 7;
                    jeuTvTentativesRestantes.setText("Il vous reste " + compteur + " essais");
                    rejouer();
                } else if (jeuSpChoixDifficulte.getSelectedItem().equals("Difficile")) {
                    compteur = 5;
                    compteurDebut = 5;
                    jeuTvTentativesRestantes.setText("Il vous reste " + compteur + " essais");
                    rejouer();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                compteur = 7;
                compteurDebut = 7;
                jeuTvTentativesRestantes.setText("Il vous reste " + compteur + " essais");
                rejouer();
            }
        });
    }

    public void verification(int choix) {
        jeuItSaisieValeur.setEnabled(false);
        compteur--;
        if (compteur > 0) {
                if (choix > alea) {
                    jeuTvReponse.setText("C'est plus petit");
                    continueJeu();
                } else if (choix < alea) {
                    jeuTvReponse.setText("C'est plus grand");
                    continueJeu();
                } else {
                    finJeuGagne();
                }
            } else if (choix == alea) {
            finJeuGagne();
            } else {
            finJeuPerdu();
            }
    }

    public void finJeuGagne() {
        Log.w("TAG", "" + compteurDebut);
        Log.w("TAG", "" + compteur);
        jeuTvTentativesRestantes.setText("Vous avez trouvé en " + (compteurDebut - compteur) + " essais");
        jeuTvReponse.setText("Félicitations !!!");
        jeuBtnRejouer.setVisibility(View.VISIBLE);
        jeuBtnQuitter.setVisibility(View.VISIBLE);
        jeuBtnValider.setClickable(false);
        jeuBtnValider.setEnabled(false);
        jeuLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
    }

    public void finJeuPerdu() {
        jeuTvReponse.setText("Vous avez perdu\nIl fallait trouver " + alea);
        jeuTvTentativesRestantes.setText("Fini");
        jeuBtnRejouer.setVisibility(View.VISIBLE);
        jeuBtnQuitter.setVisibility(View.VISIBLE);
        jeuBtnValider.setClickable(false);
        jeuBtnValider.setEnabled(false);
        jeuLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
    }

    public void continueJeu() {
        jeuItSaisieValeur.setEnabled(true);
        jeuTvTentativesRestantes.setText("Il vous reste " + compteur + " essais");
        jeuItSaisieValeur.setText("");
    }

    public void rejouer() {
        jeuBtnRejouer.setVisibility(View.GONE);
        jeuBtnQuitter.setVisibility(View.GONE);
        jeuBtnValider.setClickable(true);
        jeuBtnValider.setEnabled(true);
        jeuItSaisieValeur.setEnabled(true);
        jeuItSaisieValeur.setText("");
        jeuTvTentativesRestantes.setText("Vous avez " + compteurDebut + " essais");
        jeuTvReponse.setText("");
        jeuSwAfficherReponse.setChecked(false);
        jeuSpChoixDifficulte.setEnabled(true);
        alea  = new Random().nextInt(100)+1;
        jeuLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
    }
}


