package com.pynguins.content.repository
import com.arangodb.springframework.repository.ArangoRepository;
import com.pynguins.content.arango_data.ArangoContent

public interface ContentRepository: ArangoRepository<ArangoContent, String> {
}
