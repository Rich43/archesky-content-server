package com.archesky.content.repository
import com.arangodb.springframework.repository.ArangoRepository;
import com.archesky.content.resolvers.Content

public interface ContentRepository: ArangoRepository<Content, String> {
}
