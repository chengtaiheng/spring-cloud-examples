package com.github.yingzhuo.spring.cloud.examples.provider

import com.fasterxml.jackson.annotation.{JsonAutoDetect, PropertyAccessor}
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.{RedisTemplate, StringRedisTemplate}
import org.springframework.data.redis.serializer.{Jackson2JsonRedisSerializer, RedisSerializer, StringRedisSerializer}

@Configuration
class ApplicationCnfRedis {

  @Bean
  def redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate[String, Object] = {
    val template = new RedisTemplate[String, Object]()
    template.setConnectionFactory(redisConnectionFactory)
    template.setKeySerializer(objectRedisSerializer())
    template.setValueSerializer(objectRedisSerializer())
    template.setHashKeySerializer(objectRedisSerializer())
    template.setHashValueSerializer(objectRedisSerializer())
    template.afterPropertiesSet()
    template
  }

  @Bean
  def objectRedisSerializer(): RedisSerializer[Object] = {
    val om = new ObjectMapper()
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
    val serializer = new Jackson2JsonRedisSerializer[Object](classOf[Object])
    serializer.setObjectMapper(om)
    serializer
  }

  @Bean
  def stringRedisTemplate(redisConnectionFactory: RedisConnectionFactory): StringRedisTemplate = {
    val template = new StringRedisTemplate(redisConnectionFactory)
    template.setStringSerializer(stringRedisSerializer())
    template
  }

  @Bean
  def stringRedisSerializer(): StringRedisSerializer = {
    new StringRedisSerializer()
  }

}
