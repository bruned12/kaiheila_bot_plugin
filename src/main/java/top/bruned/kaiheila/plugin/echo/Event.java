package top.bruned.kaiheila.plugin.echo;

import top.bruned.kaiheila.framework.plugin.EventClass;
import top.bruned.kaiheila.framework.plugin.annotation.EventHandler;

import top.bruned.kaiheila.sdk.bot.api.message_create.message_create_data;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;


@EventHandler(recvBotMessage = true)
public class Event extends EventClass {
    @Override
    public void onEvent(GroupTextMessageEvent event){
        if(event.getContent().startsWith("@bruned_bot#2901688749")){
            String card = "[{\"type\":\"card\",\"theme\":\"secondary\",\"size\":\"lg\",\"modules\":[{\"type\":\"header\",\"text\":{\"type\":\"plain-text\",\"content\":\"信息查询结果\"}},{\"type\":\"section\",\"text\":{\"type\":\"plain-text\",\"content\":\"你的ID：%s\"}},{\"type\":\"section\",\"text\":{\"type\":\"plain-text\",\"content\":\"频道ID：%s\"}},{\"type\":\"section\",\"text\":{\"type\":\"plain-text\",\"content\":\"消息ID：%s\"}}]}]";
            bot.message_create(new message_create_data(event.getTarget_id(),String.format(card,event.getAuthor_id(),event.getTarget_id(),event.getMsg_id())).setType(10));
        }
        else if (event.getContent().startsWith("/echo ")&&!event.getContent().substring(6).trim().isEmpty()){
            bot.message_create(new message_create_data(event.getTarget_id(),event.getContent().substring(6).trim()));
            bot.message_delete(event.getMsg_id());
        }
        else if(event.getContent().startsWith("/card ")){
            bot.message_create(new message_create_data(event.getTarget_id(),event.getContent().substring(6).trim()).setType(10));
        }
    }
}
