package com.example.repo.presentation.base.mapper

import com.example.repo.domain.model.Filter
import com.example.repo.presentation.base.Mapper
import com.example.repo.presentation.base.model.FilterView

interface FilterViewMapper : Mapper<Filter, FilterView> {

    override fun mapFromDomainModel(type: Filter): FilterView

    override fun mapToDomainModel(type: FilterView): Filter
}