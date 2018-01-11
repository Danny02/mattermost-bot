package mattermost.bot.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author Daniel Heinrich
  * @since 11.01.2018
  */
class MattermostConfig(config: Config) {
  def this() = this(ConfigFactory.load())

  val accessToken = config.getString("mattermost.access-token")
  val incomingHookToken = config.getString("mattermost.incoming-token")
  val uri = config.getString("mattermost.uri")
}
