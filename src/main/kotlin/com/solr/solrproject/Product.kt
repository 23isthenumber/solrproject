package com.solr.solrproject

import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

@SolrDocument(solrCoreName = "solrproject")
class Product {

    @Id
    @Indexed(name = "id", type = "string")
    lateinit var id : String

    @Indexed(name = "name", type = "string")
    lateinit var name : String
}