package com.example.repo.presentation.base.mapper

import com.example.data.model.Filter
import com.example.repo.presentation.base.Mapper
import com.example.repo.presentation.base.model.FilterView

class FilterViewMapperImpl : Mapper<Filter, FilterView> {
    override fun mapFromDomainModel(type: Filter): FilterView = FilterView(
        id = type.id,
        name = type.name,
    )

    override fun mapToDomainModel(type: FilterView): Filter = Filter(
        id = type.id,
        name = type.name,
    )
}