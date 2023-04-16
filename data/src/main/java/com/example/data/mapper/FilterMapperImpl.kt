package com.example.data.mapper

import com.example.data.model.Filter
import com.example.data.model.FilterEntity
import javax.inject.Inject

class FilterMapperImpl @Inject constructor() : FilterMapper {
    override fun mapFromEntity(type: FilterEntity): Filter = Filter(
        id = type.id,
        name = type.name
    )

    override fun mapToEntity(type: Filter): FilterEntity = FilterEntity(
        id = type.id,
        name = type.name
    )
}