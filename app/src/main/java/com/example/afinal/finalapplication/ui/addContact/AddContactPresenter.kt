package com.example.afinal.finalapplication.ui.addContact

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact

class AddContactPresenter(override var view: AddContactContract.View?) : AddContactContract.Presenter {

    var groupIds : ArrayList<Int> = ArrayList()

    var initialSize : Int = -1

    override fun getGroups(groupDao: GroupDao) {
        Thread(Runnable {
            groupIds = groupDao.getAllId() as ArrayList<Int>
            if (groupIds.isEmpty()){
                view?.onGetGroupError()
            }else{
                view?.onGetGroupSuccess(groupIds)
            }
        }).start()
    }

    override fun addContact(contact: Contact, contactDao: ContactDao) {
        Thread(Runnable {
            initialSize = contactDao.getCount()
            contactDao.addContact(contact)
            if (initialSize < contactDao.getCount() && initialSize != -1)
                view?.onAddSuccess()
            else
                view?.onAddError()
        }).start()
    }
}