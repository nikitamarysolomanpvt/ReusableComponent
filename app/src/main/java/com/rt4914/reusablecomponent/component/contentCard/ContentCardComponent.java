package com.rt4914.reusablecomponent.component.contentCard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rt4914.reusablecomponent.R;

public class ContentCardComponent extends LinearLayout {
    private String TAG = ContentCardComponent.class.getSimpleName();

    private LinearLayout llRoot;
    private LayoutInflater layoutInflater;
    private TextView tvContent;

    private String sContent;
    private String sContentId;

    public ContentCardComponent(Context context,String sContentId, String sContent) {
        super(context);
        this.sContent = sContent;
        this.sContentId = sContentId;
        initView(context);
    }

    public ContentCardComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ContentCardComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        layoutInflater.inflate(R.layout.component_content_card, this);

        llRoot = findViewById(R.id.llRoot);

        tvContent = findViewById(R.id.tvContent);
        setContent(sContent);
    }

    public void setContent(String sContent) {
        tvContent.setText(sContent);
    }

    public void appenContent(String sContent) {
        tvContent.append(sContent);
    }

    public void isAudioPlaying(boolean isAudioPlaying) {
        if (isAudioPlaying) {
            llRoot.setBackgroundResource(R.drawable.component_content_card_with_audio_background);
        } else {
            llRoot.setBackgroundResource(R.drawable.component_content_card_background);
        }
    }

}
