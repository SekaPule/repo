package com.example.repo.presentation.base.mapper

import com.example.repo.domain.model.Filter
import com.example.repo.presentation.base.model.FilterView
import javax.inject.Inject

class FilterViewMapperImpl @Inject constructor() : FilterViewMapper {

    override fun mapFromDomainModel(type: Filter): FilterView = FilterView(
        id = type.id,
        name = type.name,
    )

    override fun mapToDomainModel(type: FilterView): Filter = Filter(
        id = type.id,
        name = type.name,
    )
}