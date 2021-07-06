package app.myapp.userkotlin.Notifiction

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.text.format.DateUtils
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
import app.myapp.userkotlin.NotyifayUser
import app.myapp.userkotlin.PartMain.MainActivityPart
import app.myapp.userkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AdapterNotifi(var list:ArrayList<NotyifayUser>) : RecyclerView.Adapter<AdapterNotifi.MyHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNotifi.MyHolder {


        var item=LayoutInflater.from(parent.context).inflate(R.layout.split_noti,parent,false)

        return MyHolder(item)
    }

    override fun getItemCount()=list.size





    override fun onBindViewHolder(holder: AdapterNotifi.MyHolder, position: Int) {


        var item:NotyifayUser=list[position]

        holder.name.text=item.name


       holder.content.text=item.content

        holder.time.text=time(item.time)


    }


    class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        var name:TextView=itemView.findViewById(R.id.text_noty_name)
        var content=itemView.findViewById<TextView>(R.id.text_noty_content)
        var time=itemView.findViewById<TextView>(R.id.text_noty_time)


    }


    fun time(t:String):String{

        var inputFormat = SimpleDateFormat("dd/MM/yyyy H:m:s")

      

            var date=inputFormat.parse(t);
           var niceDateStr = DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);

      
        return niceDateStr.toString();


     
    }



}