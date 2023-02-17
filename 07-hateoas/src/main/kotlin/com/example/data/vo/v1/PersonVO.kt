package com.example.data.vo.v1

import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

data class PersonVO (

    @Mapping("id") /*This annotation makes it seems as if the attribute key was named id*/
    var key: Long = 0, /*There is already an attribute called id in the Representation Model. For that, we change this to key*/
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
) : RepresentationModel<PersonVO>()