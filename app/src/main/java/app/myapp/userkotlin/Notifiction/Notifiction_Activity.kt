package app.myapp.userkotlin.Notifiction

import android.os.Bundle
import android.text.format.DateUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.myapp.userkotlin.AdapterFood
import app.myapp.userkotlin.NotyifayUser
import app.myapp.userkotlin.R
import app.myapp.userkotlin.foodMain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.ParseException
import java.text.SimpleDateFormat


class Notifiction_Activity : AppCompatActivity() {

    lateinit var list: ArrayList<NotyifayUser>
    lateinit var r: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifiction_)


        r = findViewById(R.id.rec_noty)

        list=ArrayList()
        var userId = FirebaseAuth.getInstance().currentUser!!.uid

        var code = "$userId notyNo"

        var database = FirebaseDatabase.getInstance().getReference(code)

        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {


                if (snapshot!!.exists()) {

                    list.clear()
                    for (f in snapshot.children) {

                        var fm = f.getValue(NotyifayUser::class.java)

                        if (fm!!.noty == "yes") {

                            list.add(fm)
                        }
                    }


                    var a = AdapterNotifi(list)
                    r.adapter = a
                    r.layoutManager = LinearLayoutManager(this@Notifiction_Activity)
                    r.setHasFixedSize(true)
                }
            }

        })


    }
}




