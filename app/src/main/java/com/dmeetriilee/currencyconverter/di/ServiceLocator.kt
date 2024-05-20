package com.dmeetriilee.currencyconverter.di

import com.dmeetriilee.currencyconverter.repository.CbrRepository
import com.dmeetriilee.currencyconverter.repository.PairsRepository
import com.dmeetriilee.currencyconverter.repository.XrRepository

class ServiceLocator private constructor() {
    val cbrRepository: CbrRepository = CbrRepository()
    val xrRepository: XrRepository = XrRepository()
    val pairsRepository: PairsRepository = PairsRepository()

    companion object {
        private var instance: ServiceLocator? = null

        fun getInstance(): ServiceLocator {
            if (instance == null) {
                instance = ServiceLocator()
            }
            return instance!!
        }
    }

}