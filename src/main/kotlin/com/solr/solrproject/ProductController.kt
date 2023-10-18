package com.solr.solrproject

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/product")
class ProductController(val productService: ProductService) {
//TODO: dto do pakietow
    @PostMapping
    fun save(@RequestBody product: Product) = ResponseEntity.ok(productService.save(product))

    @PutMapping("/{id}")
    fun update(@RequestBody requestBody: Product, @PathVariable id: String) =
        ResponseEntity.ok(productService.update(requestBody, id))

    @GetMapping("/name/{name}")
    fun findByName(@PathVariable name: String) = ResponseEntity.ok(productService.findByName(name))

    @GetMapping("/id/{id}")
    fun findById(@PathVariable id: String) = ResponseEntity.ok(productService.findById(id))


    @GetMapping("/custom/{searchTerm}")
    fun findByCustomQuery(@PathVariable searchTerm: String) =
        ResponseEntity.ok(productService.findByCustomQuery(searchTerm, PageRequest.of(0, 10)))


    @GetMapping("/named/{searchTerm}")
    fun findByNamedQuery(@PathVariable searchTerm: String) =
     ResponseEntity.ok(productService.findByNamedQuery(searchTerm, PageRequest.of(0, 10)))

    @DeleteMapping
    fun deleteAll() = productService.deleteAll()
}