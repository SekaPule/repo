package com.example.repo.presentation.base

interface Mapper<D, V> {

    fun mapFromDomainModel(type: D): V

    fun mapToDomainModel(type: V): D
}