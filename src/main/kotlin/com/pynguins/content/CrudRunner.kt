package com.pynguins.content

import com.arangodb.springframework.core.ArangoOperations
import com.pynguins.content.arango_data.ArangoContent
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.ComponentScan


@ComponentScan("com.pynguins.content")
class CrudRunner : CommandLineRunner {
    @Autowired
    private val operations: ArangoOperations? = null
    @Autowired
    private val repository: ContentRepository? = null

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val content = ArangoContent("From Spring")
        if (repository != null) {
            repository.save(content)
            println("Saved content to Arango")
        }
    }
}
