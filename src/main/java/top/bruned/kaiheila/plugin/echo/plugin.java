package top.bruned.kaiheila.plugin.echo;

import top.bruned.kaiheila.framework.plugin.JavaPlugin;
import top.bruned.kaiheila.framework.plugin.annotation.PluginINFO;

@PluginINFO(PluginName = "TestPlugin",Version = "0.0.0",Author = "bruned")
public class plugin extends JavaPlugin {
    @Override
    public void onLoad(){
        log.info("测试插件加载");

    }

    @Override
    public void onEnable() {
        log.info("测试插件启动");
        eventChannel.registerEventClass(new Event(this));
    }

    @Override
    public void onDisable() {
        log.info("测试插件关闭");
    }

}
