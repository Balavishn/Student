package com.example.student_sides.ui.academic.Subjects.Studycontent;

import android.app.DownloadManager;
import android.content.Context;
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

public class pdf_video_adapter extends RecyclerView.Adapter<pdf_video_adapter.mypdf> {
    List<String> namee,url;
    com.example.student_sides.ui.academic.Subjects.Studycontent.pdf.pdf pdf;
    com.example.student_sides.ui.academic.Subjects.Studycontent.video.video video;

    public pdf_video_adapter(Context cx,List<String> name, List<String> url) {
        this.namee = name;
        this.url = url;
    //    pdf=(com.example.studentside.ui.Academic.Subjects.Studycontent.pdf.pdf)cx;
    //    video=(com.example.studentside.ui.Academic.Subjects.Studycontent.video.video)cx;
    }

    @NonNull
    @Override
    public mypdf onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_pdf_video,parent,false);
        mypdf s= new mypdf(v);
        return s;
    }

    @Override
    public void onBindViewHolder(@NonNull final mypdf holder, final int position) {
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
        return namee.size();
    }

    public class mypdf  extends RecyclerView.ViewHolder{
        TextView name;
        ImageView download;
        View v;
        public mypdf(@NonNull View itemView) {
            super(itemView);
            v=itemView;
            name=itemView.findViewById(R.id.pdf_video_name);
            download=itemView.findViewById(R.id.download);
           /* download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/
        }
    }
}
