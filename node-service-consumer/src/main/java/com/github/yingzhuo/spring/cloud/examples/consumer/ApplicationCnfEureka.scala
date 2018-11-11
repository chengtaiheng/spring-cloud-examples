package com.github.yingzhuo.spring.cloud.examples.consumer

import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
class ApplicationCnfEureka {
}
