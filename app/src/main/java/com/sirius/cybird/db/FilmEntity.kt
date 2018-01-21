package com.sirius.cybird.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

/**
 *
 *Create By Botasky 21/01/2018
 **/
@Entity
data class FilmEntity(
        @Id var id: Long = 0,
        var text: String? = null,
        var comment: String? = null,
        var date: Date? = null
)
