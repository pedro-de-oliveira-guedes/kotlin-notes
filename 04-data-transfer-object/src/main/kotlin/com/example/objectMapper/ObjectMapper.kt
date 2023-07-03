package com.example.objectMapper

import org.modelmapper.ModelMapper

object ObjectMapper {

    private val mapper: ModelMapper = ModelMapper()

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