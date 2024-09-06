package com.example.demo4.config;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class CustomBanner implements Banner {
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println("                            _ooOoo_");
        out.println("                           o8888888o");
        out.println("                           88\" . \"88");
        out.println("                           (| -_- |)");
        out.println("                            O\\ = /O");
        out.println("                        ____/`---'\\____");
        out.println("                      .   ' \\| |// `.");
        out.println("                       / \\||| : |||// \\");
        out.println("                     / _||||| -:- |||||- \\");
        out.println("                       | | \\\\\\ - /// | |");
        out.println("                     | \\_| ''\\---/'' | |");
        out.println("                      \\ .-\\__ `-` ___/-. /");
        out.println("                   ___`. .' /--.--\\ `. . __");
        out.println("                .\"\" '< `.___\\_<|>_/___.' >'\"\".");
        out.println("               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |");
        out.println("                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /");
        out.println("         ======`-.____`-.___\\_____/___.-`____.-'======");
        out.println("                            `=---='");
        out.println("         .............................................");
        out.println("                  佛祖镇楼                  BUG辟易");
        out.println("          佛曰:");
        out.println("                  写字楼里写字间，写字间里程序员；");
        out.println("                  程序人员写程序，又拿程序换酒钱。");
        out.println("                  酒醒只在网上坐，酒醉还来网下眠；");
        out.println("                  酒醉酒醒日复日，网上网下年复年。");
        out.println("                  但愿老死电脑间，不愿鞠躬老板前；");
        out.println("                  奔驰宝马贵者趣，公交自行程序员。");
        out.println("                  别人笑我忒疯癫，我笑自己命太贱；");
        out.println("                  不见满街漂亮妹，哪个归得程序员？");

    }
}
