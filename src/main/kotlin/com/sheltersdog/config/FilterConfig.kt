package com.sheltersdog.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig @Autowired constructor(val apiInfos: ApiInfos) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            this.route {
                this.path(apiInfos.shelterService.gatewayPath)
                    .filters {
                        it.addRequestHeader(apiInfos.key, apiInfos.value)
                        it.rewritePath(
                            apiInfos.shelterService.realPath + Replacement.REGEX.value,
                            Replacement.REPLACEMENT.value
                        )
                    }
                    .uri(apiInfos.shelterService.url)
            }
        }
    }


}