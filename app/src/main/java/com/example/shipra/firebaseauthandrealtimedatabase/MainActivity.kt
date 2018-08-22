package com.example.shipra.firebaseauthandrealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var edFirstName:EditText
    lateinit var edLastName :EditText
    lateinit var edAddress :EditText
    lateinit var edDepartment :EditText

    lateinit var btSave:Button
    lateinit var btDatabase:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edFirstName = findViewById(R.id.first_name)
        edLastName = findViewById(R.id.last_name)
        edAddress  = findViewById(R.id.address)
        edDepartment = findViewById(R.id.department)

        btSave =findViewById(R.id.btnSave)
        btDatabase =findViewById(R.id.btnDatabse)


        btSave.setOnClickListener {

            saveData()
        }

    }

    private fun saveData() {

        val firstName = edFirstName.text.toString().trim()
        val lastName = edLastName.text.toString().trim()
        val Address = edAddress.text.toString().trim()
        val Department = edDepartment.text.toString().trim()

        if(firstName.isEmpty()){

            edFirstName.error ="please enter your First Name"
            return
        }


        val ref = FirebaseDatabase.getInstance().getReference("userData")
        val userId =ref.push().key

        val user = User(userId!!,firstName ,lastName ,Address,Department)

        ref.child(userId).setValue(user).addOnCompleteListener {

            Toast.makeText(applicationContext,"data saved successfully",Toast.LENGTH_SHORT).show()

        }





    }
}
