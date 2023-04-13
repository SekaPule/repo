package com.example.data.mapper

import com.example.data.model.Filter
import com.example.data.model.FilterEntity
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