package app.myapp.userkotlin.PartMain

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.myapp.userkotlin.PartMain.Item
import app.myapp.userkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class MainActivityPart : AppCompatActivity() {

    lateinit var rec: RecyclerView

    lateinit var id: String
    lateinit var list:ArrayList<Item>
    lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_part)



        rec = findViewById(R.id.rec_food)


        context=this
        list= ArrayList()

        id = intent.getStringExtra("id")

        var database = FirebaseDatabase.getInstance().getReference(id)

        
        
        
        
        
        
        
        
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot!!.exists()){

                    list.clear()
                    for (f in snapshot.children){

                 
                        var fm=f.getValue(Item::class.java)



                        list.add(fm!!)

                        }


                    var a= AdabterPart(list,context)
                    rec.adapter=a
                    rec.layoutManager= LinearLayoutManager(baseContext)
                    rec.setHasFixedSize(true)
                }
            }

        })


    }


    }
    

