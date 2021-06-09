package com.example.calendar;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

public class AjustesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    private ListPreference idioma, letra;
    private SwitchPreference tema;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferencias);


        idioma = findPreference("idioma");
        tema =  findPreference("tema");
        letra = findPreference("letra");



        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this.getContext());

        actualizarCampo(sharedPreferences,"idioma");
        actualizarCampo(sharedPreferences,"letra");

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        guardarDato(sharedPreferences,key);
        actualizarCampo(sharedPreferences,key);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void actualizarCampo(SharedPreferences sharedPreferences,String key){
        if(key.equals("idioma"))
            idioma.setSummary(idioma.getEntry());
        if(key.equals("letra"))
            letra.setSummary(letra.getEntry());
    }


    private void guardarDato(SharedPreferences sharedPreferences, String key){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(key.equals("idioma")) {
            editor.putString("idioma", idioma.getValue());
        }
        else if(key.equals("letra")){
            editor.putString("letra",letra.getValue());

        }else if(key.equals("tema")){
            editor.putBoolean("tema",tema.isChecked());
        }

        else if(key.equals("gmail")){
        editor.putBoolean("gmail",tema.isChecked());
     }else if(key.equals("twitter")){
        editor.putBoolean("twitter",tema.isChecked());
        }


        editor.apply();
        reiniciar();
    }

    public void reiniciar(){
        if(getActivity() instanceof Preferencias ){
            Preferencias preferencias = (Preferencias) getActivity();
            preferencias.reload();
        }
    }


    }

