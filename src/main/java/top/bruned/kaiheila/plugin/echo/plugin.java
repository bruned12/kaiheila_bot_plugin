package top.bruned.kaiheila.plugin.echo;

import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.annotation.PluginINFO;

@PluginINFO(PluginName = "EchoPlugin",Version = "0.0.0",Author = "bruned")
public class plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        pluginConfig.getJsonObject().put("test","test");
        pluginConfig.saveConfig();
        log.info("测试插件启动");
        bot.api.Message_create("1767539110347849","Bot上线");
        registerEvent(new Event());

    }

    @Override
    public void onDisable() {
        log.info("测试插件关闭");
    }

}
