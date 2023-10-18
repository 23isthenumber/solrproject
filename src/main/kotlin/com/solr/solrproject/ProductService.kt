package com.solr.solrproject

import org.springframework.data.domain.Pageable

class ProductService(private val productRepository: ProductRepository) {

    fun save(product: Product) = productRepository.save(product)

    //TODO: niepowinno tworzyc nowego jak stary nieistnieje
    fun update(requestBody: Product, id: String) {
        productRepository.findById(id).get().name = requestBody.name
    }

    fun findByName(name: String) = productRepository.findByName(name)

    fun findById(id: String) = productRepository.findById(id)

    fun findByCustomQuery(searchTerm: String, pageable: Pageable) = productRepository.findByCustomQuery(searchTerm, pageable)

    fun findByNamedQuery(searchTerm: String, pageable: Pageable) = productRepository.findByNamedQuery(searchTerm, pageable)

    fun deleteAll () = productRepository.deleteAll()
}