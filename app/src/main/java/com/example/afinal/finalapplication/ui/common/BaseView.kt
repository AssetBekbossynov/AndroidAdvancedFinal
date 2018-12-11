/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 09/07/2018.
 */

package com.example.afinal.finalapplication.ui.common

/**
 * Base View interface
 */
interface BaseView<out P : BasePresenter<*>> {
    val presenter: P
}