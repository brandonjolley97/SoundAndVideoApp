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
            public void OnClick(View currentView)
            {
                soundPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View currentView)
            {
                soundPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View currentView)
            {
                soundPlayer.stop();
                soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.pomdeter);
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener()
        {
            public void OnClick(View currentView)
            {
                Intent myIntent = new Intent(currentView.getContext(), VideoActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onStopTrackingTouch(SeekBar seekBar)
            {}

            public void onStartTrackingTouch(SeekBar seekBar)
            {}

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    soundPlayer.seekTo(progress);
                }
            }


        });

        public void run()
        {
            int currentPosition = 0;
            int soundTotal = soundPlayer.getDuration();
            soundSeekBar.setMax(soundTotal);

            while (soundPlayer !=null && currentPosition < soundTotal)
            {
                try
                {
                    Thread.sleep(300);
                    currentPosition = soundPlayer.getCurrentPosition();
                }
                catch(InterruptedException soundException)
                {
                    return;
                }
                catch(Exception otherException)
                {
                    return;
                }
                soundSeekBar.setProgress(currentPosition);

            }

        }

            


    });



}
