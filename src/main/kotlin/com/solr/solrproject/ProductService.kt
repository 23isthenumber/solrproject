package com.solr.solrproject

import com.solr.solrproject.exception.not_found.ProductNotFoundException
import com.solr.solrproject.solr.Product
import com.solr.solrproject.solr.ProductRepository
import org.springframework.data.domain.Pageable

class ProductService(private val productRepository: ProductRepository) {

    fun save(product: Product) = productRepository.save(product)

    fun update(requestBody: Product, id: String) : Product =
        save(findById(id).also {
            it.name = requestBody.name
        })

    fun findByName(name: String) = productRepository.findByName(name)

    fun findById(id: String) : Product {
        val product = productRepository.findById(id)
        if (product.isPresent) return product.get()
        throw ProductNotFoundException(id)
    }

    fun findByCustomQuery(searchTerm: String, pageable: Pageable) = productRepository.findByCustomQuery(searchTerm, pageable)

    fun findByNamedQuery(searchTerm: String, pageable: Pageable) = productRepository.findByNamedQuery(searchTerm, pageable)

    fun deleteAll () = productRepository.deleteAll()
}