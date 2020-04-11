package sundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sundry.serve.TestServe;
import sundry.utils.ApplicationContextFactory;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class SundryApplication {
    public static void main(String[] args) {
        //SpringApplication.run(SundryApplication.class, args);
        ConfigurableApplicationContext app = SpringApplication.run(SundryApplication.class, args);
        System.out.println("mainClass");
        ApplicationContextFactory.setApplicationContext(app);
        TestServe testServe = (TestServe) ApplicationContextFactory.getBean("testServe");
        testServe.printStr();


        //启动WEB项目
        // 利用SpringBootApplication的run方法返回的Context，存到项目静态变量中使用
/*        SpringApplication application = new SpringApplication(SundryApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        System.out.println(111);
        TestServe testServe1 = context.getBean(TestServe.class);
        testServe1.printStr();*/


    }

}
