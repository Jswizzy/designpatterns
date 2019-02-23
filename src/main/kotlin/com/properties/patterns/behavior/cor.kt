package com.properties.patterns.behavior

import java.lang.IllegalStateException

interface HeadersChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HeadersChain? = null) :
    HeadersChain {
    override fun addHeader(inputHeader: String): String {
        token ?: throw  IllegalStateException("Token should not be null")
        return inputHeader + "Authorization: Bearer $token\n"
            .let { next?.addHeader(it) ?: it }
    }
}

class ContentTypeHeader(val contentType: String, var next: HeadersChain? = null) :
    HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + "ContentType: $contentType\n"
            .let { next?.addHeader(it) ?: it }
}

class BodyPayload(val body: String, var next: HeadersChain? = null) :
    HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + body
            .let { next?.addHeader(it) ?: it }
}

fun main() {
    // create chain elements
    val authenticationHeader = AuthenticationHeader("123456")
    val contentTypeHeader = ContentTypeHeader("json")
    val messageBody = BodyPayload(
        """
            Body:
            {
            "username"="dbacinski"
            }
            """.trimIndent()
    )

    // construct chain elements
    authenticationHeader.next = contentTypeHeader
    contentTypeHeader.next = messageBody

    //execute chain
    val messageWithAuthentication =
        authenticationHeader.addHeader("Headers with Authentication:\n")
    println(messageWithAuthentication)

    val messageWithoutAuth =
        contentTypeHeader.addHeader("Headers:\n")
    println(messageWithoutAuth)
}