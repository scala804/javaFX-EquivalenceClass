package com.autoTest.javaFXEquivalenceClass;

import com.autoTest.javaFXEquivalenceClass.controller.JavaFxMain;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangbihua
 */
@SpringBootApplication
public class JavaFxEquivalenceClassApplication extends AbstractJavaFxApplicationSupport implements CommandLineRunner  {
	/**
	 * SpringBoot 的入口，这里关闭了启动时会出现的 banner 图形
	 *
	 * @param args 启动时输入的参数
	 */
	public static void main(String[] args)  {
		SpringApplication app = new SpringApplication(JavaFxEquivalenceClassApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	/**
	 * SpringBoot 初始化完成后立即执行该方法，在这里制定窗体程序的入口类
	 *
	 * @param args 启动时输入的参数
	 */
	@Override
	public void run(String[] args) {
		System.out.println("SpringBoot javaFX桌面应用");
		Application.launch(JavaFxMain.class, args);
	}
}
