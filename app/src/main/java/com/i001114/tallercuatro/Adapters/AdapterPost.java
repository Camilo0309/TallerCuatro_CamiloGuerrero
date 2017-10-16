package com.i001114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i001114.tallercuatro.CommentsActivity;
import com.i001114.tallercuatro.MainActivity;
import com.i001114.tallercuatro.Models.Post;
import com.i001114.tallercuatro.Models.User;
import com.i001114.tallercuatro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 14/10/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {

    List<Post> postList = new ArrayList<>();
    Context context;
    // Constructor de la clase
    public AdapterPost(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }
    @Override
    public AdapterPost.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Configuracion del ViewAdapter
// Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempost, parent, false);
// Pasar la vista (item.xml) al ViewHolder
        AdapterPost.ViewHolder viewHolder = new AdapterPost.ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPost.ViewHolder holder, int position) {
// Encargado de trabajar con el item.xml y sus componentes
        String[] lista1;

        lista1 = new String[10];
        lista1[0]= "https://i.pinimg.com/originals/bc/3c/7b/bc3c7b772bd446870a34c8ef17bc4a6a.jpg";
        lista1[1] = "https://i1.wp.com/img02.deviantart.net/5964/i/2012/177/7/f/f_c_barcelona_wallpaper_mes_que_un_club_by_great_design-d54xfum.png";
        lista1[2] = "https://i.blogs.es/5881f1/fifa-18-cabecera/original.jpg";
        lista1[3] = "http://www.dlcompare.com/upload/gameimage/file/35720.jpeg";
        lista1[4] = "http://cdn.gamers.vg/wp-content/uploads/2016/07/FIFA-17-portada-Marco-Reus.jpg";
        lista1[5] = "https://clashroyale.com/uploaded-images/CR_facebook_share_02.jpg?mtime=20160104014218";
        lista1[6] ="http://nintenworld.com/wp-content/uploads/2017/07/3184635-resident-evil-7-review-thumb.jpg";
        lista1[7] = "https://redbarrelsgames.com/wp-content/uploads/2016/04/ScreenShotOutlastII-01.jpg";
        lista1[8] = "https://zenbyte.net/~blog2017/wp-content/uploads/2016/06/uncharted4.jpg";
        lista1[9] = "https://xbhalo.blob.core.windows.net/wallpapers/halo-1980x1080.jpg";

        int random = (int) (Math.random()*9);


        Picasso.with(context).load(lista1[random]).into(holder.imageView);

        holder.textViewuserid.setText(Integer.toString(postList.get(position).getUserid()));
        holder.textViewid.setText(Integer.toString(postList.get(position).getId()));
        holder.textViewtitle.setText(postList.get(position).getTitle());
        holder.textViewbody.setText(postList.get(position).getBody());
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewuserid;
        TextView textViewid;
        TextView textViewtitle;
        TextView textViewbody;
        ImageView imageView;


        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            textViewuserid = (TextView) item.findViewById(R.id.id_tv_userid);
            textViewid = (TextView) item.findViewById(R.id.id_tv_item_id2);
            textViewtitle = (TextView) item.findViewById(R.id.id_tv_item_title1);
            textViewbody = (TextView) item.findViewById(R.id.id_tv_item_body1);
            imageView = (ImageView) item.findViewById(R.id.id_img_item_cardview);

        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra("principalid2", postList.get(getLayoutPosition()).getId());
            context.startActivity(intent);

        }
    }

}
