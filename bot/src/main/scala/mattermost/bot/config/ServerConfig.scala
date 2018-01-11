package mattermost.bot.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Daniel Heinrich
  * @since 11.01.2018
  */
class ServerConfig(config: Config) {
  def this() = this(ConfigFactory.load())

  val host = config.getString("http.interface")
  val port = config.getInt("http.port")
}
