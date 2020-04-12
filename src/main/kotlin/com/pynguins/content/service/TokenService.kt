package com.pynguins.content.service

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import graphql.GraphQLException
import java.net.URL
import java.security.interfaces.RSAPublicKey
import java.util.*


class TokenService {
    fun validateToken(token: String): DecodedJWT {
        val prop = Properties()
        prop.load(javaClass.classLoader.getResourceAsStream("application.properties"))
        val certificateURL = prop.getProperty("openid.connect.certificate.url")
        val issuer = prop.getProperty("openid.connect.issuer")
        val jwt = JWT.decode(token)
        val provider: JwkProvider = UrlJwkProvider(URL(certificateURL))
        val jwk = provider[jwt.keyId]
        val algorithm: Algorithm = Algorithm.RSA256(jwk.publicKey as RSAPublicKey, null)
        algorithm.verify(jwt)
        if (jwt.issuer != issuer) {
            throw GraphQLException(
                    "Token validation failed: " +
                            "Invalid issuer (Expected: '$issuer' but got '${jwt.issuer}')"
            )
        }
        return jwt
    }
}
