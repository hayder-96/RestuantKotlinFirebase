package app.myapp.userkotlin

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.myapp.userkotlin.Notifiction.Notifiction_Activity
import app.myapp.userkotlin.bascket.ItemBascket
import app.myapp.userkotlin.bascket.MainBascket
import app.myapp.userkotlin.bascket.SqlDataBase
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainFood : AppCompatActivity() {

   lateinit var r:RecyclerView
    lateinit var list:ArrayList<foodMain>

    lateinit var con:Context
    lateinit var firebase:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_food)

        r=findViewById(R.id.rec)



        firebase= FirebaseAuth.getInstance()

        if (firebase.currentUser==null){

            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        var intent=Intent(this,MyService::class.java)

        startService(intent)

        con=this

        list =ArrayList()

       var database = FirebaseDatabase.getInstance().getReference("food")

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {



                if (snapshot!!.exists()){

                    list.clear()
                    for (f in snapshot.children){

                        var fm=f.getValue(foodMain::class.java)



                        list.add(fm!!)


                    }


                    var a= AdapterFood(list,con)
                    r.adapter=a
                    r.layoutManager= LinearLayoutManager(con)
                    r.setHasFixedSize(true)
                }
            }

        })









    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main,menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.log_out->{
                firebase.signOut()
            }

            R.id.noti->{
                var intent=Intent(baseContext,Notifiction_Activity::class.java)

                startActivity(intent)
            }
        }




        return super.onOptionsItemSelected(item)
    }








    fun goBascket(view: View) {

        var intent=Intent(baseContext,MainBascket::class.java)

        startActivity(intent)


    }


    override fun onBackPressed() {

        var d= SqlDataBase(this).allData

        if (d.count!=0){

            var dialog=AlertDialog.Builder(this)
                    .setTitle("سيتم مسح السلة اذا خرجت")
            dialog.setCancelable(false)

            dialog.setPositiveButton("نعم", DialogInterface.OnClickListener { dialog, which ->

                SqlDataBase(this).deletedtable()
                super.onBackPressed()

            })

            dialog.setNegativeButton("لا", DialogInterface.OnClickListener { dialog, which ->

                dialog.dismiss()
            })

            dialog.show()

        }else{
            Toast.makeText(baseContext,"empaty",Toast.LENGTH_SHORT).show()
        }



    }




}