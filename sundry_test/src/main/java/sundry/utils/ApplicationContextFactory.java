package sundry.utils;

import lombok.Data;
import org.springframework.context.ApplicationContext;

//@Data
public class ApplicationContextFactory {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        ApplicationContextFactory.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static <T>T getBean(Class<T> beanClass){
        return applicationContext.getBean(beanClass);
    }

    public static <T>T getBean(String beanName,Class<T> beanClass){
        return applicationContext.getBean(beanName,beanClass);
    }
}
