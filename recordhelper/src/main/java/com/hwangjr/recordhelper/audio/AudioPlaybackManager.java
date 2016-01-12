package com.hwangjr.recordhelper.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

import com.hwangjr.recordhelper.video.MediaPlayerManager;
import com.hwangjr.recordhelper.video.PlaybackHandler;
import com.hwangjr.recordhelper.visualizer.VisualizerView;

/*
 * Controls audio playback 
 */
public class AudioPlaybackManager implements OnGlobalLayoutListener, OnPreparedListener, MediaPlayerControl, OnCompletionListener {
    private VisualizerView visualizerView;
    private MediaPlayerManager playerManager;
    private MediaController controller;
    private PlaybackHandler playbackHandler;
    private boolean isPlayerPrepared, isSurfaceCreated;

    public AudioPlaybackManager(Context ctx, VisualizerView visualizerView, PlaybackHandler playbackHandler) {
        this.playerManager = new MediaPlayerManager();
        this.playerManager.getPlayer().setOnPreparedListener(this);
        this.playerManager.getPlayer().setOnCompletionListener(this);

        this.visualizerView = visualizerView;
        this.visualizerView.link(this.playerManager.getPlayer());
        this.visualizerView.getViewTreeObserver().addOnGlobalLayoutListener(this);

        this.controller = new MediaController(ctx);
        this.controller.setMediaPlayer(this);
        this.controller.setAnchorView(visualizerView);

        this.playbackHandler = playbackHandler;
    }

    public void setupPlayback(String fileName) {
        playerManager.setupPlayback(fileName);
    }

    public void showMediaController() {
        if (!controller.isEnabled()) {
            controller.setEnabled(true);
        }
        controller.show();
    }

    public void hideMediaController() {
        controller.hide();
        controller.setEnabled(false);
    }

    private void releaseVisualizer() {
        visualizerView.release();
        visualizerView = null;
    }

    public void dispose() {
        playerManager.releasePlayer();
        releaseVisualizer();
        controller = null;
        playbackHandler = null;
    }

    //visualizer setup callback *****************************************************************

    @Override
    public void onGlobalLayout() {
        isSurfaceCreated = true;
        if (isPlayerPrepared && isSurfaceCreated) {
            playbackHandler.onPreparePlayback();
        }
    }

    //media player and controller callbacks *****************************************************

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPlayerPrepared = true;
        if (isPlayerPrepared && isSurfaceCreated) {
            playbackHandler.onPreparePlayback();
        }
    }

    @Override
    public void start() {
        playerManager.startPlaying();
    }

    @Override
    public void pause() {
        playerManager.pausePlaying();
    }

    @Override
    public void seekTo(int arg0) {
        playerManager.seekTo(arg0);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playerManager.seekTo(0);
    }

    @Override
    public boolean isPlaying() {
        return playerManager.isPlaying();
    }

    @Override
    public int getCurrentPosition() {
        return playerManager.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return playerManager.getDuration();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }
}
