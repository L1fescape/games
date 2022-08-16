package com.l1fescape.minecraft;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;

public class SlackClient {
  private App app;
  private SlackAppServer server;

  public SlackClient() {
    init();
  }

  private void init() {
    app = new App();

    app.command("/help", (req, ctx) -> {
      ctx.respond(res -> res
        .responseType("ephemeral")
        .text("oh fuck!")
      );
      return ctx.ack();
    });

    app.command("/whitelist", (req, ctx) -> {
      String username = req.getPayload().getText();
      System.out.println(String.format("Request to whitelist %s", username));
      try {
        String command = String.format("whitelist add %s", username);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
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
