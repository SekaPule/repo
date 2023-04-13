package com.example.search_feature.presentation.mapper

interface Mapper<D, V> {

    fun mapFromDomainModel(type: D): V

    fun mapToDomainModel(type: V): D
}