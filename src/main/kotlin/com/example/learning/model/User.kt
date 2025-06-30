package com.example.learning.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

enum class ROLE {
    SUPERADMIN,
    ADMIN,
    AUTHOR,
    USER
}

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"]), UniqueConstraint(columnNames = ["email"])]
)
data class User(

    @Column(name = "username")
    var username: String,

    @Column(length = 255, nullable = false)
    var name: String = "",

    @Column(length = 255, nullable = false)
    var email: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonIgnore
    var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: ROLE = ROLE.USER
) : BaseModel() {

}