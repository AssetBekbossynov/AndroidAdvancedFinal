package com.example.afinal.finalapplication.ui.addContact

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.afinal.finalapplication.MyApp
import com.example.afinal.finalapplication.R
import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.helper.Logger
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.models.Group
import kotlinx.android.synthetic.main.activity_add_contact.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AddContactActivity : AppCompatActivity(), AddContactContract.View {

    override val presenter: AddContactContract.Presenter by inject { parametersOf(this) }

    private lateinit var contactDao: ContactDao
    private lateinit var groupDao: GroupDao

    var groupIdList: ArrayList<Int> = ArrayList()

    var groupId : Int  = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        groupDao = (applicationContext as MyApp).database.groupDao()
        contactDao = (applicationContext as MyApp).database.contactDao()

        presenter.getGroups(groupDao)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                groupId = -1
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                groupId = groupIdList.get(position)
            }
        }

        add.setOnClickListener {

            if (groupId != -1){
                val contact = Contact(contactDao.getCount(),
                        name.text.toString(),
                        mobilePhone.text.toString(),
                        homePhone.text.toString(),
                        workPhone.text.toString(),
                        "image",
                        groupId)

                presenter.addContact(contact, contactDao)
            }

        }
    }

    override fun onAddSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onAddError() {
        Logger.msg("error happened")
    }

    override fun onGetGroupSuccess(list: ArrayList<Int>) {
        groupIdList.clear()
        groupIdList.addAll(list)
        spinner.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, groupIdList)
    }

    override fun onGetGroupError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
