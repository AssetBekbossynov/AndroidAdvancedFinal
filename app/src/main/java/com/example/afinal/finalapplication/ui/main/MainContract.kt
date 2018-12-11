package com.example.afinal.finalapplication.ui.main

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.ui.common.BasePresenter
import com.example.afinal.finalapplication.ui.common.BaseView

interface MainContract {

    interface View: BaseView<Presenter>{
        fun onGetSuccess(contacts: ArrayList<Contact>, groupName: String)
        fun onGetError()
    }

    interface Presenter: BasePresenter<View>{
        fun getContacts(contactDao: ContactDao, groupDao: GroupDao)
    }
}