package com.pynguins.content.service

import com.pynguins.auth.library.CheckTokenQuery
import org.springframework.stereotype.Service

@Service
data class TokenMappingService(val userTokenMap: MutableMap<String, CheckTokenQuery.CheckToken> = HashMap())
