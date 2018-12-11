package com.example.afinal.finalapplication.ui.main

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.example.afinal.finalapplication.MyApp
import com.example.afinal.finalapplication.R
import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.helper.ListAdapter
import com.example.afinal.finalapplication.helper.Logger
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.models.Group
import com.example.afinal.finalapplication.ui.addContact.AddContactActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {

    private val ADD_CONTACT = 1

    private lateinit var groupDao: GroupDao
    private lateinit var contactDao: ContactDao

    lateinit var adapter: ListAdapter

    private var contacts: ArrayList<Contact> = ArrayList()
    private var displayedContacts: ArrayList<Contact> = ArrayList()

    override val presenter: MainContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        groupDao = (applicationContext as MyApp).database.groupDao()
        contactDao = (applicationContext as MyApp).database.contactDao()

        presenter.getContacts(contactDao, groupDao)

        addContact.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT)
        }

        search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s!!.equals("")){
                    displayedContacts.clear()
                    displayedContacts.addAll(contacts)
                }else{
                    Logger.msg("search " + s)
                    displayedContacts.clear()
                    for (i in 0 until contacts.size){
                        if (contacts.get(i).name.contains(s)){
                            displayedContacts.add(contacts.get(i))
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        adapter = ListAdapter(displayedContacts, this)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        addGroup()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.msg("onActivityResult")
        if (requestCode == ADD_CONTACT && resultCode == Activity.RESULT_OK){
            presenter.getContacts(contactDao, groupDao)
            Logger.msg("onActivityResult code = OK")
        }
    }

    override fun onGetSuccess(contacts: ArrayList<Contact>, groupName: String) {
        Logger.msg("MainActivity onGetSuccess " + contacts)
        this.contacts.clear()
        this.contacts.addAll(contacts)
        displayedContacts.clear()
        displayedContacts.addAll(contacts)
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onGetError() {
        Logger.msg("error on get")
    }

    fun addGroup(){
        Thread(Runnable {
            val newGroup1 = Group(0, "firstGroup", "low")
            val newGroup2 = Group(1, "secondGroup", "medium")
            val newGroup3 = Group(2, "thirdGroup", "high")

            groupDao.addGroup(newGroup1)
            groupDao.addGroup(newGroup2)
            groupDao.addGroup(newGroup3)

            Logger.msg("groups " + groupDao.getAllGroup())
        }).start()
    }
}
