package com.savin.simplespeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech mTTs;
    private EditText mEditText;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSpeak = findViewById(R.id.button_speak);

        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    mTTs.setLanguage(Locale.ENGLISH);
                    mButtonSpeak.setEnabled(true);

                }

            }
        });

        mEditText = findViewById(R.id.edit_text);

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    private void speak(){
        String text = mEditText.getText().toString();

        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    protected void onDestroy() {

        if(mTTs != null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
}
