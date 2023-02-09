package com.example.objectMapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {

    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <Origin, Destine> parseObject(origin: Origin, destine: Class<Destine>?): Destine {
        return mapper.map(origin, destine)
    }

    fun <Origin, Destine> parseObjectList(origin: List<Origin>, destine: Class<Destine>?): List<Destine> {
        val returnedObject: ArrayList<Destine> = ArrayList()

        for (obj in origin)
            returnedObject.add(mapper.map(obj, destine))

        return returnedObject
    }
}