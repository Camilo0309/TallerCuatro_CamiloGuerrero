package com.i001114.tallercuatro.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i001114.tallercuatro.Models.User;
import com.i001114.tallercuatro.PostActivity;
import com.i001114.tallercuatro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 12/10/2017.
 *
 *
 */

    public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder>{
        List<User> userList = new ArrayList<>();
        Context context;
        // Constructor de la clase
        public AdapterUser(List<User> userList, Context context) {
            this.userList = userList;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Configuracion del ViewAdapter
// Obtener la vista (item.xml)
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemuser, parent, false);
// Pasar la vista (item.xml) al ViewHolder
            ViewHolder viewHolder = new ViewHolder(item);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
// Encargado de trabajar con el item.xml y sus componentes

            holder.textViewid.setText(Integer.toString(userList.get(position).getId()));
            holder.textViewName.setText(userList.get(position).getName());
            holder.textViewusername.setText(userList.get(position).getUsername());
            holder.textViewaddress.setText(userList.get(position).getAddress());
            holder.textViewcompany.setText(userList.get(position).getCompany());
        }
        @Override
        public int getItemCount() {
            return userList.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView textViewid;
            TextView textViewName;
            TextView textViewusername;
            TextView textViewaddress;
            TextView textViewcompany;
            ImageView imageView;

            public ViewHolder(View item) {
                super(item);

                item.setOnClickListener(this);

                imageView = (ImageView) item.findViewById(R.id.img1);
                textViewid = (TextView) item.findViewById(R.id.id_tv_id);
                textViewName = (TextView) item.findViewById(R.id.id_tv_item_nameuser);
                textViewusername = (TextView) item.findViewById(R.id.id_tv_item_username);
                textViewaddress = (TextView) item.findViewById(R.id.id_tv_item_ubicacion);
                textViewcompany = (TextView) item.findViewById(R.id.id_tv_item_company);

            }

                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PostActivity.class);
                    intent.putExtra("principalid", userList.get(getLayoutPosition()).getId());
                    context.startActivity(intent);

                }
            }
        }



