package org.ieszaidinvergeles.dam.chaterbot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.ieszaidinvergeles.dam.chaterbot.BaseDatos.Ayudante;
import org.ieszaidinvergeles.dam.chaterbot.BaseDatos.Contrato;
import org.ieszaidinvergeles.dam.chaterbot.BaseDatos.Gestor;
import org.ieszaidinvergeles.dam.chaterbot.POJO.Conversacion;
import org.ieszaidinvergeles.dam.chaterbot.api.ChatterBot;
import org.ieszaidinvergeles.dam.chaterbot.api.ChatterBotFactory;
import org.ieszaidinvergeles.dam.chaterbot.api.ChatterBotSession;
import org.ieszaidinvergeles.dam.chaterbot.api.ChatterBotType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

//https://github.com/pierredavidbelanger/chatter-bot-api

public class MainActivity extends AppCompatActivity {

    private Button btSend, btRecu;
    private EditText etTexto;
    private ScrollView svScroll;
    private TextView tvTexto;

    private ChatterBot bot;
    private ChatterBotFactory factory;
    private ChatterBotSession botSession;

    private Conversacion conver;
    private Gestor gestor = new Gestor(this, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
        btSend = findViewById(R.id.btSend);
        btRecu = findViewById(R.id.btRecu);
        etTexto = findViewById(R.id.etTexto);
        svScroll = findViewById(R.id.svScroll);
        tvTexto = findViewById(R.id.tvTexto);
        if(startBot()) {
            setEvents();
        }
    }

    private void chat(final String text) {
        String response;
        try {
            response = getString(R.string.bot) + " " + botSession.think(text);
            conver = new Conversacion("bot>", botSession.think(text), Calendar.getInstance().getTime());
        } catch (final Exception e) {
            response = getString(R.string.exception) + " " + e.toString();
        }
        tvTexto.post(showMessage(response));
        gestor.insertarConversacion(conver);
    }

    private void setEvents() {
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = getString(R.string.you) + " " + etTexto.getText().toString().trim();
                conver = new Conversacion("you>", etTexto.getText().toString().trim(),Calendar.getInstance().getTime());
                btSend.setEnabled(false);
                etTexto.setText("");
                tvTexto.append(text + "\n");
                new Thread(){
                    @Override
                    public void run() {
                        chat(text);
                    }
                }.start();
            }
        });

        btRecu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Conversacion> conversaciones = Gestor.getConversaciones();
                for (Conversacion conversacion:conversaciones) {
                    tvTexto.setText(conversacion.toString());
                }

            }
        });
    }

    private boolean startBot() {
        boolean result = true;
        String initialMessage;
        factory = new ChatterBotFactory();
        try {
            bot = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477");
            botSession = bot.createSession();
            initialMessage = getString(R.string.messageConnected) + "\n";
        } catch(Exception e) {
            initialMessage = getString(R.string.messageException) + "\n" + getString(R.string.exception) + " " + e.toString();
            result = false;
        }
        tvTexto.setText(initialMessage);
        return result;
    }

    private Runnable showMessage(final String message) {
        return new Runnable() {
            @Override
            public void run() {
                etTexto.requestFocus();
                tvTexto.append(message + "\n");
                svScroll.fullScroll(View.FOCUS_DOWN);
                btSend.setEnabled(true);
                hideKeyboard();
            }
        };
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}