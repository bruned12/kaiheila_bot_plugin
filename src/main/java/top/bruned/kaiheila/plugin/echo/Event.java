package top.bruned.kaiheila.plugin.echo;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.bruned.kaiheila.framework.plugin.EventClass;
import top.bruned.kaiheila.framework.plugin.annotation.EventHandler;

import top.bruned.kaiheila.framework.plugin.eventmethod.onGroupTextMessageEvent;
import top.bruned.kaiheila.sdk.wsclient.result.event.GroupTextMessageEvent.GroupTextMessageEvent;

import java.io.IOException;
@EventHandler(recvBotMessage = false)
public class Event extends EventClass implements onGroupTextMessageEvent{
    public static final String baseUrl = "http://127.0.0.1:3000";
    @Override
    public void onEvent(GroupTextMessageEvent event){
        if (event.getContent().startsWith("/echo ")&&!event.getContent().substring(6).trim().isEmpty()){
            bot.api.Message_create(event.getTarget_id(), event.getContent().substring(6).trim());
        }
        else if(event.getContent().startsWith("/song ")){
            String data = getCard(event.getContent().substring(6).trim());
            bot.api.Message_create(10,event.getTarget_id(),data);
        }
        else if(event.getContent().startsWith("/card ")){
            bot.api.Message_create(10,event.getTarget_id(),event.getContent().substring(6).trim());
        }
    }

    public static String getCard(String name){
        String card = "[{\\\"type\\\":\\\"card\\\",\\\"theme\\\":\\\"secondary\\\",\\\"size\\\":\\\"lg\\\",\\\"modules\\\":[{\\\"type\\\":\\\"audio\\\",\\\"title\\\":\\\"%s-%s\\\",\\\"src\\\":\\\"%s\\\",\\\"cover\\\":\\\"%s\\\"}]}]";
        JSONObject song = get(String.format("/cloudsearch?keywords=%s&type=1&limit=1", name)).getJSONObject("result").getJSONArray("songs").getJSONObject(0);
        return String.format(card,song.getString("name"),song.getJSONArray("ar").getJSONObject(0).getString("name"),get(String.format("/song/url?id=%s",song.getIntValue("id"))).getJSONArray("data").getJSONObject(0).getString("url"),song.getJSONObject("al").getString("picUrl"));
    }

    private static JSONObject get(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl+url).get().build();
        try (Response response = client.newCall(request).execute()) {
            return JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}
