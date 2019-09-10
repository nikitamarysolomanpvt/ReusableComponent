package com.rt4914.reusablecomponent;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rt4914.reusablecomponent.component.audio.AudioComponent;
import com.rt4914.reusablecomponent.component.audio.AudioInterface;
import com.rt4914.reusablecomponent.component.contentCard.ContentCardComponent;
import com.rt4914.reusablecomponent.model.VoiceoverModel;

import java.util.HashMap;
import java.util.Map;

public class ExplorationPlayerFragment extends Fragment {

    private final static String TAG = ExplorationPlayerFragment.class.getSimpleName();

    private AudioComponent audioComponent = null;
    private AudioInterface audioInterface = null;
    private FrameLayout flRoot;
    private LinearLayout llRoot;
    private ScrollView svRoot;
    private Toolbar mToolbar;

    private Context context;

    private String sDummyContent1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\n" + "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.";
    private String sDummyContent2 = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)." +
            "\n" + "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc." +
            "\n" + "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.";
    private Map<String, VoiceoverModel> audioMapping;

    private Map<String, ContentCardComponent> contentIdToContentDataMap;

    public ExplorationPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        contentIdToContentDataMap = new HashMap<>();
        createDummyAudioMapping();

        View view = inflater.inflate(R.layout.fragment_exploration_player, container, false);

        flRoot = view.findViewById(R.id.flRoot);
        llRoot = view.findViewById(R.id.llRoot);
        svRoot = view.findViewById(R.id.svRoot);

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle("The Meaning of \"Equal Parts\"");
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        } else {
            return view;
        }
        setHasOptionsMenu(true);

        addContentCard("content_1", sDummyContent1);
        addContentCard("content_2", sDummyContent2);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exploration_player, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: " + id);

        switch (id) {
            case R.id.action_audio:
                if (audioComponent == null) {
                    addAudioComponent();
                    item.setIcon(R.drawable.ic_volume_up_white_24dp);
                } else {
                    removeAudioComponent();
                    item.setIcon(R.drawable.ic_volume_off_white_24dp);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDummyAudioMapping() {
        VoiceoverModel voiceoverModel1 = new VoiceoverModel();
        voiceoverModel1.setFileName("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
        voiceoverModel1.setNeedsUpdate(false);
        voiceoverModel1.setFileSize(15550);

        VoiceoverModel voiceoverModel2 = new VoiceoverModel();
        voiceoverModel2.setFileName("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3");
        voiceoverModel2.setNeedsUpdate(false);
        voiceoverModel2.setFileSize(15550);

        audioMapping = new HashMap<>();
        audioMapping.put("en", voiceoverModel1);
        audioMapping.put("hi", voiceoverModel2);
    }

    private void addAudioComponent() {
        audioInterface = new AudioInterface() {
            @Override
            public void audioPlayerCallback(boolean isAudioPlaying, String sContentId) {
                if (contentIdToContentDataMap.containsKey(sContentId)) {
                    ContentCardComponent contentCardComponent = contentIdToContentDataMap.get(sContentId);
                    assert contentCardComponent != null;
                    contentCardComponent.isAudioPlaying(isAudioPlaying);

                    svRoot.scrollTo(0, (int)contentCardComponent.getY());
                }
            }
        };

        audioComponent = new AudioComponent(context, audioInterface, "content_1", audioMapping);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        flRoot.addView(audioComponent, params);

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        params2.setMargins(0, 100, 0, 0);
        svRoot.setLayoutParams(params2);

    }

    private void removeAudioComponent() {
        ((ViewGroup) audioComponent.getParent()).removeView(audioComponent);
        audioComponent = null;
        audioInterface = null;

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        params2.setMargins(0, 0, 0, 0);
        svRoot.setLayoutParams(params2);
    }

    private void addContentCard(String sContentId, String sContent) {
        ContentCardComponent contentCardComponent = new ContentCardComponent(context, sContent, sContent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, dpToPx(8),0,dpToPx(8));

        llRoot.addView(contentCardComponent, params);

        contentIdToContentDataMap.put(sContentId, contentCardComponent);
    }

    private void removeContentCard() {
        //This method won't be needed.
        //Though the approach for the implementation can be same as removeAudioComponent
        //but ContentCardComponent count can be indefinite, therefore whenever
        //a new ContentCardComponent is created add that to stack and
        //remove the top item from the object.
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
