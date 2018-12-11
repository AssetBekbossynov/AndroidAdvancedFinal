package com.example.afinal.finalapplication.ui.addContact

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.models.Group
import com.example.afinal.finalapplication.ui.common.BasePresenter
import com.example.afinal.finalapplication.ui.common.BaseView

interface AddContactContract{

    interface View: BaseView<Presenter>{
        fun onAddSuccess()
        fun onAddError()
        fun onGetGroupSuccess(list: ArrayList<Int>)
        fun onGetGroupError()
    }

    interface Presenter: BasePresenter<View>{
        fun addContact(contact: Contact, contactDao: ContactDao)
        fun getGroups(groupDao: GroupDao)
    }
}