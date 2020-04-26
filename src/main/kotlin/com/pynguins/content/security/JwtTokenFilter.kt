package com.pynguins.content

import com.pynguins.auth.library.TokenService
import com.pynguins.content.service.TokenMappingService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter(
        @Qualifier("customUserDetailsService") private val userDetailsService: UserDetailsService,
        private val tokenMappingService: TokenMappingService
) : OncePerRequestFilter() {
    private val log = getLogger(this.javaClass)

    private fun resolveToken(request: HttpServletRequest): String? {
        val optReq = Optional.of(request)

        return optReq.map {
            req: HttpServletRequest -> req.getHeader("Authorization")
        }.filter {
            token: String ->
            token.isNotEmpty()
        }.map {
            token: String -> token.replace("Bearer ", "")
        }.orElse(null)
    }

    private fun getAuthentication(token: String?): Authentication? {
        return try {
            val validateToken = TokenService().validateToken(
                    token!!,
                    "http://localhost:9090/graphql"
            )
            tokenMappingService.userTokenMap[validateToken.username] = validateToken
            val userDetails = this.userDetailsService.loadUserByUsername(validateToken.username)
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        } catch (e: Exception) {
            null
        }
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = resolveToken(request)
        if (token != null) {
            val auth = getAuthentication(token)
            if (auth == null) {
                log.info("Invalid token")
            } else {
                log.info("Token accepted")
            }
            SecurityContextHolder.getContext().authentication = auth
        } else {
            log.info("No auth token")
        }
        filterChain.doFilter(request, response)
    }
}