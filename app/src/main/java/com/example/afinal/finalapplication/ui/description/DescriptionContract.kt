package com.example.afinal.finalapplication.ui.description

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.ui.common.BasePresenter
import com.example.afinal.finalapplication.ui.common.BaseView

interface DescriptionContract {
    interface View: BaseView<Presenter> {
        fun onGetSuccess(contact: Contact, groupName: String)
        fun onError(msg: String)
    }

    interface Presenter: BasePresenter<View>{
        fun getContactById(position: Int, contactDao: ContactDao, groupDao: GroupDao)
    }
}