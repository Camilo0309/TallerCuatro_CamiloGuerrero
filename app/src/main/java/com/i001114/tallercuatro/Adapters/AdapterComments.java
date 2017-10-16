package com.i001114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i001114.tallercuatro.MainActivity;
import com.i001114.tallercuatro.Models.Comments;
import com.i001114.tallercuatro.Models.Post;
import com.i001114.tallercuatro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 14/10/2017.
 */

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.ViewHolder> {
    List<Comments> commentsList = new ArrayList<>();
    Context context;
    // Constructor de la clase
    public AdapterComments(List<Comments> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }
    @Override
    public AdapterComments.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Configuracion del ViewAdapter
// Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcomments, parent, false);
// Pasar la vista (item.xml) al ViewHolder
        AdapterComments.ViewHolder viewHolder = new AdapterComments.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterComments.ViewHolder holder, int position) {
// Encargado de trabajar con el item.xml y sus componentes


        holder.textViewpostid.setText(Integer.toString(commentsList.get(position).getPostid()));
        holder.textViewid.setText(Integer.toString(commentsList.get(position).getId()));
        holder.textViewemail.setText(commentsList.get(position).getEmail());
        holder.textViewbody.setText(commentsList.get(position).getBody());
    }
    @Override
    public int getItemCount() {
        return commentsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewpostid;
        TextView textViewid;
        TextView textViewemail;
        TextView textViewbody;
        ImageView imageView;


        public ViewHolder(View item) {
            super(item);
            textViewpostid = (TextView) item.findViewById(R.id.id_tv_postid3);
            textViewid = (TextView) item.findViewById(R.id.id_tv_item_id3);
            textViewemail = (TextView) item.findViewById(R.id.id_tv_item_email);
            textViewbody = (TextView) item.findViewById(R.id.id_tv_item_body3);
            imageView = (ImageView) item.findViewById(R.id.id_img_item_cardview);

        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("principalid2", commentsList.get(getLayoutPosition()).getId());
            context.startActivity(intent);

        }

    }
}
