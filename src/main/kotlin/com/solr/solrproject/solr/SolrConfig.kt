package com.solr.solrproject.solr

import com.solr.solrproject.ProductService
import org.apache.solr.client.solrj.SolrClient
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.solr.core.SolrTemplate
import org.springframework.data.solr.repository.config.EnableSolrRepositories

@Configuration
@EnableSolrRepositories(
    basePackages = ["com.solr.solrproject"],
    namedQueriesLocation = "classpath:solr-named-queries.properties"
)
@ComponentScan
class SolrConfig {
    @Bean
    fun productService(productRepository: ProductRepository): ProductService =
        ProductService(productRepository)

    @Bean
    fun solrClient(): SolrClient = HttpSolrClient.Builder("http://localhost:8983/solr").build()

    @Bean
    fun solrTemplate(solrClient: SolrClient): SolrTemplate =
        SolrTemplate(solrClient)
}