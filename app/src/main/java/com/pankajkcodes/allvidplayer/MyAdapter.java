package com.pankajkcodes.allvidplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VideoHolder> {
    private Context context;
    ArrayList<File> videoArrayList;

    public MyAdapter(Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int i) {
        try {

            holder.txtFileName.setText(MainActivity.fileArrayList.get(i).getName());
            Bitmap bitmapThumbnail = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(i).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
            holder.imageThumbnail.setImageBitmap(bitmapThumbnail);
        } catch (Exception e) {
            e.printStackTrace();

        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        if (videoArrayList.size() > 0) {
            return videoArrayList.size();
        } else
            return 1;
    }


    public class VideoHolder extends RecyclerView.ViewHolder {
        TextView txtFileName;
        ImageView imageThumbnail;
        CardView mCardView;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            txtFileName = itemView.findViewById(R.id.txt_videoFileName);
            imageThumbnail = itemView.findViewById(R.id.iv_thmnail);
            mCardView = itemView.findViewById(R.id.myCardview);
        }
    }
}
