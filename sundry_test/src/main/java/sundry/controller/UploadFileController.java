package sundry.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sundry.serve.UploadFileServe;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    private UploadFileServe uploadFileServe;

    /**
    * 上传文件
    * 文件类型在前端限制
    * */
    @RequestMapping("/file")
    public String uploadFile(MultipartFile file,String fileId){
        String status = null;
        status = uploadFileServe.uploadFileOfLoc(file,fileId);

        return status;
    }
}
