package com.pynguins.content

import com.pynguins.auth.library.TokenService
import org.slf4j.LoggerFactory.getLogger
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter : OncePerRequestFilter() {
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
        try {
            val validateToken = TokenService().validateToken(
                    token!!,
                    "http://localhost:9090/graphql"
            )

            val userDetails: UserDetails = object : UserDetails {
                override fun getAuthorities(): Collection<GrantedAuthority?> {
                    return emptyList()
                }

                override fun getPassword(): String {
                    return ""
                }

                override fun getUsername(): String {
                    return validateToken.username
                }

                override fun isAccountNonExpired(): Boolean {
                    return true
                }

                override fun isAccountNonLocked(): Boolean {
                    return true
                }

                override fun isCredentialsNonExpired(): Boolean {
                    return true
                }

                override fun isEnabled(): Boolean {
                    return true
                }
            }
            return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        } catch (e: Exception) {
            return null
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