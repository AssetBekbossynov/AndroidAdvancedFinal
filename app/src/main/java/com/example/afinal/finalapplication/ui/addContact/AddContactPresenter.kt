package com.example.afinal.finalapplication.ui.addContact

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact

class AddContactPresenter(override var view: AddContactContract.View?) : AddContactContract.Presenter {

    var groupIds : ArrayList<Int> = ArrayList()

    override fun getGroups(groupDao: GroupDao) {
        Thread(Runnable {
            groupIds = groupDao.getAllId() as ArrayList<Int>
        })
    }

    override fun addContact(contact: Contact, contactDao: ContactDao) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}