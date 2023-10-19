package com.solr.solrproject

import com.solr.solrproject.exception.not_found.ProductNotFoundException
import com.solr.solrproject.solr.Product
import com.solr.solrproject.solr.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {

    private final val productRepositoryMock = Mockito.mock(ProductRepository::class.java)
    private val productService : ProductService = ProductService(productRepositoryMock)

    @Test
    fun save() {
        //Given
        val product = Product().apply {
            id = "1"
            name = "name1"
        }
        Mockito.`when`(productRepositoryMock.save(product)).thenReturn(product)
        //When
        val actual = productService.save(product)
        //Then
        assertEquals(product, actual)
    }

    @Test
    fun updateThrowException() {
        //Given
        val product : Product= Product().apply {
            id = "1"
            name = "nameUpdated"
        }
        //When 
        //Then
        assertThrows<ProductNotFoundException> { productService.update(product, "1") }
    }

    @Test
    fun findByName() {
        //Given
        val productName = "name1"
        val expected = Product().apply {
            id = "1"
            name = "name1"
        }
        Mockito.`when`(productRepositoryMock.findByName(productName)).thenReturn(listOf(expected))
        //When
        val actual = productService.findByName(productName)
        //Then
        assertEquals(listOf(expected), actual)
    }

    @Test
    fun findByIdThrowException() {
        //Given
        val iD = "1"
        val product : Product= Product().apply {
            id = iD
            name = "nameUpdated"
        }
        //When
        //Then
        assertThrows<ProductNotFoundException> { productService.update(product, iD) }
    }
}