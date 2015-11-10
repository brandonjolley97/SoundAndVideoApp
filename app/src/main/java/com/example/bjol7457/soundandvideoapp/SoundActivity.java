package com.example.bjol7457.soundandvideoapp;

import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.app.Activity;
import android.view.View;
import android.widget.*;


public class SoundActivity extends Activity implements Runnable
{

    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private Button videoButton;
    private MediaPlayer soundPlayer;
    private SeekBar soundSeekBar;
    private Thread soundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sound);

        startButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        videoButton = (Button) findViewById(R.id.videoButton);
        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
        soundPlayer = MediaPlayer.create(this.getBaseContext(),R.raw.pomdeter);

        setupListeners();

        soundThread = new Thread(this);
        soundThread.start();

    }

    private void setupListeners()
    {

        startButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View v)
            {
                soundPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View v)
            {
                soundPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View v)
            {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.pomdeter);
            }
        });




            


    });



    }


}
