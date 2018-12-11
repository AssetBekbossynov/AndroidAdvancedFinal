package com.example.afinal.finalapplication.ui.description

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.afinal.finalapplication.MyApp
import com.example.afinal.finalapplication.R
import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.helper.Logger
import com.example.afinal.finalapplication.models.Contact
import kotlinx.android.synthetic.main.activity_description.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DescriptionActivity : AppCompatActivity(), DescriptionContract.View {

    private lateinit var groupDao: GroupDao
    private lateinit var contactDao: ContactDao

    override val presenter: DescriptionContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        groupDao = (applicationContext as MyApp).database.groupDao()
        contactDao = (applicationContext as MyApp).database.contactDao()

        presenter.getContactById(intent.getIntExtra("position", -1), contactDao, groupDao)

    }

    override fun onGetSuccess(contact: Contact, group: String) {
        runOnUiThread {
            name.text = contact.name
            mobilePhone.text = contact.mobilePhone
            homePhone.text = contact.homePhone
            workPhone.text = contact.workPhone
            groupName.text = group
        }
    }

    override fun onError(msg: String) {
        Logger.msg("Description activity error " + msg)
    }
}
