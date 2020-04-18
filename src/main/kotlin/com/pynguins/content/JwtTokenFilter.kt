package com.pynguins.content

import com.pynguins.auth.library.TokenService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtTokenFilter : GenericFilterBean() {
    private fun resolveToken(req: HttpServletRequest): String {
        return req.getHeader("Authorization")
    }

    private fun getAuthentication(token: String?): Authentication {
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
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val token = resolveToken(req as HttpServletRequest)
        val auth = getAuthentication(token)
        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(req, res)
    }
}