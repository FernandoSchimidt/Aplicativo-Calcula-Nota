package com.example.appunipteste;

import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Constantes usadas ao se salvar/ restaurar estado;
    private static final String NP1 = "NP1";
    private static final String PIM = "PIM";

    //atributos que armazenam os valores que devem ser mantidos quando o aplicativo reinicia

    private double np1;
    private double pim;

    //armazena as referencias dos componentes da interface grafica;
    private EditText np1EditText;
    private EditText np250EditText;
    private EditText np275EditText;
    private EditText np2100EditText;
    private SeekBar pimSeekBar;
    private EditText pimEditText;
    private EditText np2EditText;

    //metodo chamado quando a Activity é criada ou reativada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtem referencias aos componentes de tel
        np1EditText = (EditText) findViewById(R.id.np1EditText);
        np250EditText = (EditText) findViewById(R.id.np250EditText);
        np275EditText = (EditText) findViewById(R.id.np275EditText);
        np2100EditText = (EditText) findViewById(R.id.np2100EditText);
        pimSeekBar = (SeekBar) findViewById(R.id.pimSeekBar);
        pimEditText = (EditText) findViewById(R.id.pimEditText);
        np2EditText = (EditText) findViewById(R.id.np2EditText);

        //Criar os ouvintes de eventos para as views interativas
        np1EditText.addTextChangedListener(ouvinteNp1EditText);
        pimSeekBar.setOnSeekBarChangeListener(ouvintePimSeekBar);
//Verifica se o aplicativo acabou de ser iniciado ou se esta sendo restaurado
        if(savedInstanceState == null){
            np1 = 5.0;
            pim = 7.5;
        }else{
            //o aplicativo esta sendo rstaurado da memoria nao esta
            //sendo executado a partir do zero assim os valores de
            //np1 e pim sao restaurados
            np1 = savedInstanceState.getDouble(NP1);
            pim = savedInstanceState.getDouble(PIM);

        }
        //Atualiza os componentes graficos com os valores atualizados
        np1EditText.setText(String.format("%.1f",np1));
        pimSeekBar.setProgress((int) (pim * 10));
    }
    //Atualiza o valor das portas NP@ para Pim 5.0, 7.5 e 10.0

    private void atualizaNp2Padrao(){
        np250EditText.setText(Calculadora.calculaNP2(np1, 5.0));
        np275EditText.setText(Calculadora.calculaNP2(np1, 7.5));
        np2100EditText.setText(Calculadora.calculaNP2(np1, 10.0));
    }

    //Atualiza o valor da nota NP@ para PIM personalizado
    private void atualizaNp2Personalizado(){
        np2EditText.setText(Calculadora.calculaNP2(np1, pim));
    }
    //define o objeto ouvinte de mudança de texto do np1edittext

    private TextWatcher ouvinteNp1EditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           /* try{
                np1 = Double.parseDouble(np1EditText.getText().toString());
            }catch (NumberFormatException e){
                np1 = 0.0;
            }
            atualizaNp2Padrao();
            atualizaNp2Personalizado();*/
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //Define o objeto ouvinte de mudança dno pimSeekBar
    private SeekBar.OnSeekBarChangeListener ouvintePimSeekBar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          /*  pim = (double) pimSeekBar.getProgress() / 10;
            pimEditText.setText(String.format("%.1f", pim));
            atualizaNp2Personalizado();*/
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState){
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putDouble(NP1, np1);
        outState.putDouble(PIM, pim);
    }
}
