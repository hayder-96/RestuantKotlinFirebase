package app.myapp.userkotlin.bascket

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import app.myapp.userkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class AdapterUser(var list:ArrayList<ItemBascket>, var context: Context) : RecyclerView.Adapter<AdapterUser.MyHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUser.MyHolder {


        var item=LayoutInflater.from(parent.context).inflate(R.layout.split_bascket,parent,false)

        return MyHolder(item)
    }

    override fun getItemCount()=list.size





    override fun onBindViewHolder(holder: AdapterUser.MyHolder, position: Int) {


        var item:ItemBascket=list[position]

        holder.fname.text=item.name



        var uri: Uri =Uri.parse(item.image)
        Picasso.get().load(uri).resize(300,300).into(holder.image)






        holder.del.setOnClickListener{

            var s=SqlDataBase(context)
            s.deleted(item.id)


        }






    }


    class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        var fname:TextView=itemView.findViewById(R.id.t_name_bascket)
       var image=itemView.findViewById<ImageView>(R.id.imageView_split_bascket)
        var del:Button=itemView.findViewById(R.id.but_delete_bascket)

    }



}