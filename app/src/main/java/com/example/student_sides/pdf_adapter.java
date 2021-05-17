package com.example.student_sides;

import android.app.DownloadManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.staff_navigation.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class pdf_adapter extends RecyclerView.Adapter<pdf_adapter.mypdff> {
    List<String> namee,url;

    public pdf_adapter(List<String> namee, List<String> url) {
        this.namee = namee;
        this.url = url;
    }

    @NonNull
    @Override
    public mypdff onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_pdf_video,parent,false);
        mypdff s= new mypdff(v);
        return s;
    }

    @Override
    public void onBindViewHolder(@NonNull final mypdff holder, final int position) {
        holder.name.setText(namee.get(position));
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager dm=(DownloadManager)holder.v.getContext().getSystemService(holder.v.getContext().DOWNLOAD_SERVICE);
                Uri urll= Uri.parse(url.get(position));
                DownloadManager.Request request= new DownloadManager.Request(urll);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(holder.v.getContext(),DIRECTORY_DOWNLOADS,namee.get(position));

                dm.enqueue(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  namee.size();
    }

    public class mypdff extends RecyclerView.ViewHolder{

        TextView name;
        ImageView download;
        View v;
        public mypdff(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            name=itemView.findViewById(R.id.pdf_video_name);
            download=itemView.findViewById(R.id.download);
        }
    }
}
