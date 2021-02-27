package top.bruned.kaiheila.plugin.echo;

import top.bruned.kaiheila.framework.plugin.EventClass;
import top.bruned.kaiheila.framework.plugin.annotation.EventHandler;

import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;

public class Event extends EventClass {
    public Event(plugin plugin) {
        this.log = plugin.getLog();
        this.bot = plugin.getBot();
    }

    @EventHandler
    public void onGrouptextMessage(GroupTextMessageEvent event){
        if (event.getContent().startsWith("/复读 ")&&!event.getContent().substring(4).trim().isEmpty()){
            bot.api.Message_create(event.getTarget_id(), event.getContent().substring(4).trim());
        }
    }

}
