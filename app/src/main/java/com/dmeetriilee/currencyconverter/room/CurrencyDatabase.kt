package com.dmeetriilee.currencyconverter.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmeetriilee.currencyconverter.model.cbr.CbrModel
import com.dmeetriilee.currencyconverter.model.FavoritePair
import com.dmeetriilee.currencyconverter.model.XrModel
import com.dmeetriilee.currencyconverter.room.dao.CbrModelDao
import com.dmeetriilee.currencyconverter.room.dao.PairsDao
import com.dmeetriilee.currencyconverter.room.dao.XrModelDao

@Database(entities = [CbrModel::class, XrModel::class, FavoritePair::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun cbrApiModelDao(): CbrModelDao
    abstract fun xrModelDao(): XrModelDao
    abstract fun pairsDao(): PairsDao
}