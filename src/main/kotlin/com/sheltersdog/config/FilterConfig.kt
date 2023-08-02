package com.sheltersdog.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig @Autowired constructor(val apiInfos: ApiInfos) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes {
            this.route { registerPath(apiInfos.userService) }
            this.route { registerPath(apiInfos.shelterService) }
            this.route { registerPath(apiInfos.volunteerService) }
            this.route { registerPath(apiInfos.foreverdogService) }
            this.route { registerPath(apiInfos.imageService) }
            this.route { registerPath(apiInfos.addressService) }
            this.route { registerPath(apiInfos.appService) }
        }
    }

    private fun PredicateSpec.registerPath(apiInfo: ApiInfo) {
        this.path(apiInfo.gatewayPath)
            .filters {
                it.addRequestHeader(apiInfos.key, apiInfos.value)
                it.rewritePath(
                    apiInfo.realPath + Replacement.REGEX.value,
                    Replacement.REPLACEMENT.value
                )
            }
            .uri(apiInfo.url)
    }


}