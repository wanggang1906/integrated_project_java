package sundry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sundry.serve.TestServe;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestServe testServe;

    @GetMapping("/try")
    public String testTryCache(){
        return testServe.testTryCache();
    }

}
