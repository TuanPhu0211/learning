package com.example.learning.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "blogs")
data class Blog(
    @Column(length = 255, nullable = false)
    @field:NotBlank
    var title: String = "",

    @Column(length = 255, nullable = false)
    @field:NotBlank
    var slug: String = "",

    @Column(columnDefinition = "TEXT", nullable = false)
    @field:NotBlank
    var description: String = "",

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "author_id")
    var author: User? = null
) : BaseModel() {

}





