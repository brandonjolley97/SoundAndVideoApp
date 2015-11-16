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
    private MediaPlayer madPlayer;
    private SeekBar soundSeekBar;
    private Thread soundThread;
    private TextView songTitleView;
    private Button songChangeButton;
    int songNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sound);

        songTitleView = (TextView) findViewById(R.id.songTitleView);
        startButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        videoButton = (Button) findViewById(R.id.videoButton);
        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
        madPlayer = MediaPlayer.create(this.getBaseContext(),R.raw.madworld);
        songChangeButton = (Button) findViewById(R.id.songChangeButton);


        setupListeners();

        soundThread = new Thread(this);
        soundThread.start();

    }

    private void setupListeners()
    {

        songChangeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View currentView)
            {
                if(songNumber == 1)
                {
                    madPlayer = MediaPlayer.create(getBaseContext(),R.raw.rising);
                    songTitleView.setText("House Of The Rising Sun");
                }
                else if(songNumber == 2)
                {
                    madPlayer =MediaPlayer.create(getBaseContext(),R.raw.misery);
                    songTitleView.setText("I Miss The Misery");
                }
                else if(songNumber == 3)
                {
                    madPlayer = MediaPlayer.create(getBaseContext(),R.raw.madworld);
                    songTitleView.setText("Mad World");
                    songNumber = 0;
                }
                songNumber++;
            }


        });

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                madPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                madPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                madPlayer.stop();
                madPlayer = MediaPlayer.create(getBaseContext(), R.raw.madworld);
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View currentView) {
                Intent myIntent = new Intent(currentView.getContext(), VideoActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });

        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    madPlayer.seekTo(progress);
                }
            }


        });
    }

        public void run()
        {
            int currentPosition = 0;
            int soundTotal = madPlayer.getDuration();
            soundSeekBar.setMax(soundTotal);

            while (madPlayer !=null && currentPosition < soundTotal)
            {
                try
                {
                    Thread.sleep(300);
                    currentPosition = madPlayer.getCurrentPosition();
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



}
