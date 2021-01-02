package com.archesky.content.repository

import com.arangodb.springframework.repository.ArangoRepository
import com.archesky.content.dto.ContentRevision

interface ContentRevisionRepository : ArangoRepository<ContentRevision, String> {

}
