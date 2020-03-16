package com.pynguins.content.repository
import com.arangodb.springframework.repository.ArangoRepository;
import com.pynguins.content.resolvers.Content

public interface ContentRepository: ArangoRepository<Content, String> {
}
