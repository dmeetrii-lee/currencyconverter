package com.dmeetriilee.currencyconverter.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.dmeetriilee.currencyconverter.model.cbr.CbrModel
import com.dmeetriilee.currencyconverter.app.CurrencyConverterApp
import com.dmeetriilee.currencyconverter.network.JSONCbrApi
import com.dmeetriilee.currencyconverter.room.CurrencyDatabase
import com.dmeetriilee.currencyconverter.room.dao.CbrModelDao


class CbrRepository {
    private val cbrModelDao: CbrModelDao
    private val db: CurrencyDatabase = CurrencyConverterApp.instance.database

    init {
        cbrModelDao = db.cbrApiModelDao()
    }

    fun loadFromApi(): Single<CbrModel> {
        return JSONCbrApi.getInstance().getAllData()
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun writeToDb(cbrModel: CbrModel): Completable {
        return cbrModelDao.clearTable()
            .andThen(cbrModelDao.insert(cbrModel))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadFromDb(): Single<CbrModel?> {
        return cbrModelDao.getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}