package com.github.yingzhuo.spring.cloud.examples.provider

import java.lang.reflect.Method
import java.time.Duration
import java.util.Collections

import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.{CachingConfigurerSupport, EnableCaching}
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.{RedisCacheConfiguration, RedisCacheManager}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.{RedisSerializationContext, RedisSerializer}

@Configuration
@EnableCaching
@AutoConfigureAfter(Array(classOf[ApplicationCnfRedis]))
class ApplicationCnfCache(redisConnectionFactory: RedisConnectionFactory, objectRedisSerializer: RedisSerializer[Object])
  extends CachingConfigurerSupport {

  override def keyGenerator(): KeyGenerator = ApplicationCnfCache.DefaultKeyGenerator

  override def cacheManager(): CacheManager = {

    val cnf = RedisCacheConfiguration.defaultCacheConfig
      .entryTtl(Duration.ofSeconds(3600))
      .disableCachingNullValues
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(objectRedisSerializer))

    val builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)

    builder
      .withInitialCacheConfigurations(Collections.emptyMap())
      .cacheDefaults(cnf) // 不同的cache-name可以有不同的配置，我这里不需要
      .build
  }

}

object ApplicationCnfCache {

  object DefaultKeyGenerator extends KeyGenerator {
    override def generate(target: scala.Any, method: Method, params: AnyRef*): AnyRef = {
      val sb = new StringBuilder
      sb.append(target.getClass.getName)
      sb.append(method.getName)
      sb.append("#")
      for (obj <- params) {
        sb.append(obj.toString)
      }
      sb.toString()
    }
  }

}
