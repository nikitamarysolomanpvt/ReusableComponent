package com.rt4914.reusablecomponent.component.audio;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rt4914.reusablecomponent.R;
import com.rt4914.reusablecomponent.model.VoiceoverModel;

import java.util.Map;
import java.util.Set;

public class AudioComponent extends LinearLayout {

    private String TAG = AudioComponent.class.getSimpleName();

    private AudioInterface audioInterface = null;

    private ImageView ivPlayPauseAudio;
    private LayoutInflater layoutInflater;
    private SeekBar sbAudioProgress;
    private TextView tvAudioLanguage;

    private String sContentId;

    private boolean isAudioPlaying = false;

    private Map<String, VoiceoverModel> audioMapping;

    public AudioComponent(Context context, AudioInterface audioInterface, String sContentId, Map<String, VoiceoverModel> audioMapping) {
        super(context);
        this.audioInterface = audioInterface;
        this.audioMapping = audioMapping;
        this.sContentId = sContentId;
        initView(context);
    }

    public AudioComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AudioComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(l, t, r, b);
        }
    }

    private void initView(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        layoutInflater.inflate(R.layout.component_audio, this);

        ivPlayPauseAudio = findViewById(R.id.ivPlayPauseAudio);
        sbAudioProgress = findViewById(R.id.sbAudioProgress);
        tvAudioLanguage = findViewById(R.id.tvAudioLanguage);

        ivPlayPauseAudio.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "playPauseAudioMethod: ");
                isAudioPlaying = !isAudioPlaying;

                if (isAudioPlaying) {
                    playAudio();
                } else {
                    pauseAudio();
                }
            }
        });

        tvAudioLanguage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String sList = "";
                Set<String> audioLaguageCodeSet = audioMapping.keySet();
                for (String s : audioLaguageCodeSet) {
                    sList = sList + s + ", ";
                }
                Toast.makeText(getContext(), sList, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void resetAudio() {
    }

    private void playAudio() {
        ivPlayPauseAudio.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        //Code to start seekbar
        sbAudioProgress.setProgress(50);
        audioInterface.audioPlayerCallback(true, sContentId);
    }

    private void pauseAudio() {
        ivPlayPauseAudio.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        //Code to stop seekbar
        sbAudioProgress.setProgress(0);
        audioInterface.audioPlayerCallback(false, sContentId);
    }
}
