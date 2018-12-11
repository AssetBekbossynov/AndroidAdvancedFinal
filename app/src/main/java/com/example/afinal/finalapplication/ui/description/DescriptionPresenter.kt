package com.example.afinal.finalapplication.ui.description

import com.example.afinal.finalapplication.dao.ContactDao
import com.example.afinal.finalapplication.dao.GroupDao

class DescriptionPresenter(override var view: DescriptionContract.View?) : DescriptionContract.Presenter {

    override fun getContactById(position: Int, contactDao: ContactDao, groupDao: GroupDao) {
        Thread(Runnable {
            if (position != -1){
                val contact = contactDao.getContactById(position)
                if (!contact.isEmpty()){
                    val groupName = groupDao.getGroupById(contact.get(0).groupId)
                    view?.onGetSuccess(contact.get(0), groupName.get(0).name)
                }else{
                    view?.onError("get data")
                }
            }else{
                view?.onError("position")
            }
        }).start()
    }
}