package com.solr.solrproject

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository


interface ProductRepository : SolrCrudRepository<Product?, String?> {
    fun findByName(name: String?): List<Product?>?

    @Query("id:*?0* OR name:*?0*")
    fun findByCustomQuery(searchTerm: String?, pageable: Pageable?): Page<Product?>?

    @Query(name = "Product.findByNamedQuery")
    fun findByNamedQuery(searchTerm: String?, pageable: Pageable?): Page<Product?>?
}