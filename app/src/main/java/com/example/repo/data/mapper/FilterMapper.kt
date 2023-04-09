package com.example.repo.data.mapper

import com.example.repo.data.model.FilterEntity
import com.example.repo.domain.model.Filter
import javax.inject.Inject

class FilterMapper @Inject constructor() : Mapper<FilterEntity, Filter> {
    override fun mapFromEntity(type: FilterEntity): Filter = Filter(
        id = type.id,
        name = type.name
    )

    override fun mapToEntity(type: Filter): FilterEntity = FilterEntity(
        id = type.id,
        name = type.name
    )
}