package sundry.serve;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sundry.config.UploadFileConfig;

@Service
public class UploadFileServe {
    // 日志信息
    private static final Logger log = LoggerFactory.getLogger(UploadFileServe.class);

    @Autowired
    private UploadFileConfig uploadFileConfig;

    /**
     * 文件上传
     * */
    public String uploadFileOfLoc(MultipartFile file, String fileId){

        String status = null;
        return status;
    }

    /**
     * 保存文件
     * */

    /**
     * 更新文件
     * */
}
