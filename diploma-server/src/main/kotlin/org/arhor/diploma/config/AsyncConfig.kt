package org.arhor.diploma.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@EnableScheduling
@Configuration
class AsyncConfig(private val props: TaskExecutionProperties) : AsyncConfigurer {

  companion object {
    @JvmStatic
    private val log: Logger = LoggerFactory.getLogger(AsyncConfig::class.java)
  }

  @Bean(name = ["taskExecutor"])
  override fun getAsyncExecutor(): Executor {
    log.debug("Creating Async Task Executor")
    return ThreadPoolTaskExecutor().apply {
      corePoolSize = props.pool.coreSize
      maxPoolSize = props.pool.maxSize
      setQueueCapacity(props.pool.queueCapacity)
      setThreadNamePrefix(props.threadNamePrefix)
    }
  }

  @Override
  override fun getAsyncUncaughtExceptionHandler() = SimpleAsyncUncaughtExceptionHandler()
}
