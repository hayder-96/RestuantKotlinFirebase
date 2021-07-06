package app.myapp.userkotlin.bascket

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.myapp.userkotlin.R
import app.myapp.userkotlin.foodMain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainBascket : AppCompatActivity() {


   lateinit var r:RecyclerView
   lateinit var list:ArrayList<ItemBascket>

    lateinit var firebase:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bascket)


        r=findViewById(R.id.rec_bascket)
        list=ArrayList()

        firebase= FirebaseAuth.getInstance()

        allData()

        var ad=AdapterUser(list,this)

        r.adapter=ad
        r.layoutManager=LinearLayoutManager(this)
        r.setHasFixedSize(true)


    }

    fun allData(){

        list.clear()
        var d=SqlDataBase(this).allData

        if (d.count==0){
            Toast.makeText(baseContext,"empaty",Toast.LENGTH_LONG).show()
        }else{
            while (d.moveToNext()){
                list.add(ItemBascket(d.getString(0),d.getString(1),d.getString(2)))
            }
        }

    }

    fun donesell(view: View) {



        var alert= AlertDialog.Builder(this)

        alert.setTitle("شراء")

        var view= LayoutInflater.from(baseContext).inflate(R.layout.split_alert,null)

        val n=view.findViewById<TextView>(R.id.up_name)

        val a=view.findViewById<TextView>(R.id.up_age)




        alert.setView(view)

        alert.setPositiveButton("شراء",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {



                var database= FirebaseDatabase.getInstance().getReference("request")

                var id=database.push().key.toString()

                val info= InformUser(id,n.text.toString(),a.text.toString(), firebase.currentUser!!.uid)
                database.child(info.id).setValue(info).addOnCompleteListener{

                    var d=SqlDataBase(baseContext).allData
                    var datauser= FirebaseDatabase.getInstance().getReference(id)
                    while (d.moveToNext()){

                       var idi=datauser.push().key.toString()
                        var us=ItemBascket(idi,d.getString(1),d.getString(2))
                        datauser.child(us.id).setValue(us).addOnCompleteListener{

                            Toast.makeText(baseContext,"done while",Toast.LENGTH_LONG).show()
                        }


                    }

                }



            }


        })

        alert.setNegativeButton("الغاء",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {

                dialog!!.dismiss()
            }


        })

        alert.show()

    }





    }
