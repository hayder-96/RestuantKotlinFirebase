package app.myapp.userkotlin

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyService : Service() {


    companion object{
        var uid=0
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        var thread=Thread(Runnable {

            while (true){

                Thread.sleep(10000)
                Noty()
            }

        })
        thread.start()




        return START_STICKY
    }








    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

















    fun Noty(){



        var userId=FirebaseAuth.getInstance().currentUser!!.uid
        var code="$userId notyNo"

        var database = FirebaseDatabase.getInstance().getReference(code)



        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {



                if (snapshot!!.exists()){


                    for (f in snapshot.children){

                        var fm=f.getValue(NotyifayUser::class.java)

                        if (fm!!.noty.equals("no")){


                            upDate(fm.id,"yes")


                            return
                        }



                    }





                }
            }

        })







    }



    fun upDate(id:String,n:String){

        var userId=FirebaseAuth.getInstance().currentUser!!.uid

        var code="$userId notyNo"

        var value=NotyifayUser(id,"حيدر","تم الاستلام",n,getTime())
        var database = FirebaseDatabase.getInstance().getReference(code)


         database.child(value.id).setValue(value).addOnCompleteListener{

             Toast.makeText(baseContext,"تم التحديث",Toast.LENGTH_LONG).show()
             NotyF()
         }


    }






    fun NotyF(){


        var intent=Intent()
        val pandingintent=PendingIntent.getActivities(this,0, arrayOf(intent),0)


        var notifiction=Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("حيدر")
                .setContentText("تم الاستلام")
            .setDefaults(Notification.DEFAULT_SOUND)
                 notifiction.setContentIntent(pandingintent)

        var notiManger=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notiManger.notify(uid,notifiction.build())



        uid++


    }








    fun getTime():String{
        var calnder= Calendar.getInstance()
        var simble= SimpleDateFormat("dd/MM/yyyy H:m:s")
        var date=simble.format(calnder.time)

        return  date
    }


}