package top.bruned.kaiheila.plugin.echo;

import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.annotation.PluginINFO;
import top.bruned.kaiheila.sdk.bot.api.message_create.message_create_data;

@PluginINFO(PluginName = "EchoPlugin",Version = "0.0.0",Author = "bruned")
public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        pluginConfig.getJsonObject().put("test","test");
        pluginConfig.saveConfig();
        log.info("测试插件启动");
        bot.message_create(new message_create_data("1767539110347849","Bot上线"));
        registerEvent(new Event());

    }

    @Override
    public void onDisable() {
        log.info("测试插件关闭");
    }

}
