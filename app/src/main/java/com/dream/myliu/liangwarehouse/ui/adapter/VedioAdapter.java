package com.dream.myliu.liangwarehouse.ui.adapter;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dream.myliu.liangwarehouse.BaseApplication;
import com.dream.myliu.liangwarehouse.R;
import com.dream.myliu.liangwarehouse.entity.VideoEntity;
import com.dream.myliu.liangwarehouse.view.TextureVideoView;
import com.dream.myliu.liangwarehouse.volley.VolleySingleton;

import java.io.IOException;
import java.util.List;

/**
 * Created by liumingyan on 15/10/28.
 */
public class VedioAdapter extends RecyclerView.Adapter<VedioAdapter.VideoViewHolder> {
    private List<VideoEntity.VideoListEntity> entities;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private Context context;
    private VideoViewHolder lastPlayVideo = null;

    public VedioAdapter(Context context) {
        super();
        volleySingleton = VolleySingleton.getInstance();
        this.imageLoader = volleySingleton.getImageLoader();
        this.context = context;
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private TextureVideoView textureVideoView;
        private ImageView videoPlayContentsIv, videoPlayShareIv, playVideo;
        private NetworkImageView insideVideoView;
        private TextView videoPlayTime, videoPlayCount, videoTitle, videoContent, videoContentsCount;
        ProgressBar pbWaiting;
        ProgressBar pbProgressBar;

        public VideoViewHolder(View itemView) {
            super(itemView);
            textureVideoView = (TextureVideoView) itemView.findViewById(R.id.textureview);
            videoPlayShareIv = (ImageView) itemView.findViewById(R.id.videoPlayShareIv);
            playVideo = (ImageView) itemView.findViewById(R.id.playVideo);
            insideVideoView = (NetworkImageView) itemView.findViewById(R.id.insideVideoView);
            videoPlayTime = (TextView) itemView.findViewById(R.id.videoPlayTime);
            videoPlayCount = (TextView) itemView.findViewById(R.id.videoPlayCount);
            videoTitle = (TextView) itemView.findViewById(R.id.videoTitle);
            videoContent = (TextView) itemView.findViewById(R.id.videoContent);
            videoContentsCount = (TextView) itemView.findViewById(R.id.videoContentsCount);
            pbProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_progressbar);
            pbWaiting = (ProgressBar) itemView.findViewById(R.id.pb_waiting);
        }
    }


    public void addData(List<VideoEntity.VideoListEntity> datas) {
        this.entities = datas;
        notifyDataSetChanged();
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(BaseApplication.getmContext());
        return new VideoViewHolder(inflater.inflate(R.layout.video_item, null));
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
        holder.videoTitle.setText(entities.get(position).getTitle());
        holder.videoPlayCount.setText(entities.get(position).getPlayCount() + "");
        holder.videoPlayTime.setText(Math.floor(entities.get(position).getLength() / 60) + ":" + entities.get(position).getLength() % 60);
        holder.videoContent.setText(entities.get(position).getDescription());
        holder.insideVideoView.setDefaultImageResId(R.mipmap.demo_2);
        holder.insideVideoView.setErrorImageResId(R.mipmap.demo_2);
        holder.insideVideoView.setImageUrl(entities.get(position).getCover(), imageLoader);
        holder.videoContentsCount.setText(entities.get(position).getReplyCount() + "");
        holder.textureVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entities.get(position).getMp4_url().length() == 0) {
                    Toast.makeText(context, "视频地址不能为空，请重新拉取网络视频地址哦", Toast.LENGTH_LONG).show();
                    return;
                }
                if (lastPlayVideo == null) {
                    lastPlayVideo = holder;
                } else {
                    if (!holder.equals(lastPlayVideo)) {
                        lastPlayVideo.textureVideoView.stop();
                        lastPlayVideo.pbWaiting.setVisibility(View.GONE);
                        lastPlayVideo.playVideo.setVisibility(View.VISIBLE);
                        lastPlayVideo = holder;
                    }
                }
                TextureVideoView textureView = (TextureVideoView) v;
                if (textureView.getState() == TextureVideoView.MediaState.INIT || textureView.getState() == TextureVideoView.MediaState.RELEASE) {
                    textureView.play(entities.get(position).getMp4_url());
                    holder.pbWaiting.setVisibility(View.VISIBLE);
                    holder.playVideo.setVisibility(View.GONE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PAUSE) {
                    textureView.start();
                    holder.pbWaiting.setVisibility(View.GONE);
                    holder.playVideo.setVisibility(View.GONE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PLAYING) {
                    textureView.pause();
                    holder.pbWaiting.setVisibility(View.GONE);
                    holder.playVideo.setVisibility(View.VISIBLE);
                } else if (textureView.getState() == TextureVideoView.MediaState.PREPARING) {
                    textureView.stop();
                    holder.pbWaiting.setVisibility(View.GONE);
                    holder.playVideo.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.textureVideoView.setOnStateChangeListener(new TextureVideoView.OnStateChangeListener() {
            @Override
            public void onSurfaceTextureDestroyed(SurfaceTexture surface) {
                holder.textureVideoView.stop();
                holder.pbWaiting.setVisibility(View.GONE);
                holder.playVideo.setVisibility(View.VISIBLE);
                holder.pbProgressBar.setMax(1);
                holder.pbProgressBar.setProgress(0);
                holder.insideVideoView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPlaying() {
                holder.pbWaiting.setVisibility(View.GONE);
                holder.playVideo.setVisibility(View.GONE);
            }

            @Override
            public void onBuffering() {
                holder.pbWaiting.setVisibility(View.VISIBLE);
                holder.playVideo.setVisibility(View.GONE);
            }

            @Override
            public void onSeek(int max, int progress) {
                holder.insideVideoView.setVisibility(View.GONE);
                holder.pbProgressBar.setMax(max);
                holder.pbProgressBar.setProgress(progress);
            }

            @Override
            public void onStop() {
                holder.pbProgressBar.setMax(1);
                holder.pbProgressBar.setProgress(0);
                holder.pbWaiting.setVisibility(View.GONE);
                holder.playVideo.setVisibility(View.VISIBLE);
                holder.insideVideoView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPause() {
                holder.pbWaiting.setVisibility(View.GONE);
                holder.playVideo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextureViewAvaliable() {

            }

            @Override
            public void playFinish() {
                holder.pbProgressBar.setMax(1);
                holder.pbProgressBar.setProgress(0);
                holder.playVideo.setVisibility(View.GONE);
                holder.insideVideoView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPrepare() {
            }
        });

    }

    @Override
    public int getItemCount() {
        return entities != null && entities.size() > 0 ? entities.size() : 0;
    }
}
