package com.example.afinal.finalapplication.ui.main

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact

class MainPresenter(override var view: MainContract.View?) : MainContract.Presenter {

    var contacts : ArrayList<Contact> = ArrayList()

    override fun getContacts(contactDao: ContactDao, groupDao: GroupDao) {
        Thread(Runnable {
            contacts = contactDao.getAllContact() as ArrayList<Contact>
            if (contacts.isEmpty()){
                view?.onGetError()
            }else{
                view?.onGetSuccess(contacts, "GroupName")
            }
        }).start()
    }

}