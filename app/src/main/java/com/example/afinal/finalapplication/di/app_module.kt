package com.example.afinal.finalapplication.di

import com.example.afinal.finalapplication.ui.addContact.AddContactContract
import com.example.afinal.finalapplication.ui.addContact.AddContactPresenter
import com.example.afinal.finalapplication.ui.description.DescriptionContract
import com.example.afinal.finalapplication.ui.description.DescriptionPresenter
import com.example.afinal.finalapplication.ui.main.MainContract
import com.example.afinal.finalapplication.ui.main.MainPresenter
import org.koin.dsl.module.module

val appModule = module {

    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
    factory { (view: AddContactContract.View) -> AddContactPresenter(view) as AddContactContract.Presenter }
    factory { (view: DescriptionContract.View) -> DescriptionPresenter(view) as DescriptionContract.Presenter }

}

val finalApp = listOf(appModule)
