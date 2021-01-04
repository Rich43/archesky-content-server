package com.archesky.content.repository

import com.arangodb.springframework.repository.ArangoRepository
import com.archesky.content.dto.ContentMapping

interface ContentMappingRepository : ArangoRepository<ContentMapping, String> {
}
