package com.sheltersdog.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api-infos")
data class ApiInfos(
    val key: String,
    val value: String,
    val userService: ApiInfo,
    val shelterService: ApiInfo,
    val volunteerService: ApiInfo,
    val foreverdogService: ApiInfo,
    val imageService: ApiInfo,
    val addressService: ApiInfo,
    val appService: ApiInfo,
)