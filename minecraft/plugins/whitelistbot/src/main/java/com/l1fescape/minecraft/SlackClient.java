package com.l1fescape.minecraft;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.plugin.Plugin;
import java.io.IOException;
import java.util.logging.Logger;

public class SlackClient {
  private App app;
  private SlackAppServer server;
  private Logger logger;

  public SlackClient() {
    init();
  }

  private void init() {
    app = new App();
    logger = Bukkit.getServer().getLogger();

    app.command("/help", (req, ctx) -> {
      ctx.respond(res -> res
        .responseType("ephemeral")
        .text("oh fuck!")
      );
      return ctx.ack();
    });

    app.command("/whitelist", (req, ctx) -> {
      String username = req.getPayload().getText();
      logger.info(String.format("Request to whitelist %s", username));
      String command = String.format("whitelist add %s", username);
      // todo: fetch plugin instance by name
      Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
      Bukkit.getScheduler().runTask(plugins[0], () -> {
        try {
          try {`
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
            ctx.respond(res -> res
              .responseType("in_channel")
              .text(String.format("whitelisted %s", username))
            );
          } catch (CommandException e) {
            ctx.respond(res -> res
              .responseType("in_channel")
              .text(String.format("error whitelisting %s", username))
            );
          }
        } catch (IOException e) {
          logger.warning(String.format("There was an error whitelisting %s: %s", username, e.toString()));
        }
      });
      return ctx.ack();
    });
  }

  public void start() {
    try {
      server = new SlackAppServer(app);
      server.start();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
