package com.dmeetriilee.currencyconverter.repository

import com.dmeetriilee.currencyconverter.app.CurrencyConverterApp
import com.dmeetriilee.currencyconverter.model.FavoritePair
import com.dmeetriilee.currencyconverter.room.CurrencyDatabase
import com.dmeetriilee.currencyconverter.room.dao.PairsDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PairsRepository {
    private val pairsDao: PairsDao
    private val db: CurrencyDatabase = CurrencyConverterApp.instance.database

    init {
        pairsDao = db.pairsDao()
    }

    fun loadFromDb(): Single<List<FavoritePair>> {
        return pairsDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun writeToDb(pair: FavoritePair): Completable {
        return pairsDao.insert(pair)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteFromDb(pair: FavoritePair): Single<Int> {
        return pairsDao.delete(pair)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}