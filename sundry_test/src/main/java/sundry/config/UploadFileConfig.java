package sundry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:uploadFile.yml")
public class UploadFileConfig {

    @Value("uploadFile.path")
    private String filePathOfYml;

    /**
     * 获取配置文件中的路径
     * */
    public String getUploadFilePath(){
        String filePath = null;
        filePath = filePathOfYml;
        return filePath;
    }

}
