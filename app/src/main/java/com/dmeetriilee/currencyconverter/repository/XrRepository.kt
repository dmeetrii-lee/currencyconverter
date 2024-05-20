package com.dmeetriilee.currencyconverter.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.dmeetriilee.currencyconverter.app.CurrencyConverterApp
import com.dmeetriilee.currencyconverter.model.XrModel
import com.dmeetriilee.currencyconverter.network.XrApi
import com.dmeetriilee.currencyconverter.room.CurrencyDatabase
import com.dmeetriilee.currencyconverter.room.dao.XrModelDao

class XrRepository {
    private val xrModelDao: XrModelDao
    private val db: CurrencyDatabase = CurrencyConverterApp.instance.database

    init {
        xrModelDao = db.xrModelDao()
    }

    fun loadFromApi(base: String): Single<XrModel> {
        return XrApi.getInstance().getRateByBase(base = base)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadFromDbByBase(base: String): Single<XrModel?> {
        return xrModelDao.getByBase(base = base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun writeToDb(xrModel: XrModel): Completable {
        return xrModelDao.deleteByBaseCode(xrModel.baseCode)
            .andThen(xrModelDao.insert(xrModel))
            .subscribeOn(Schedulers.io())
    }
}