package sundry.serve;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TimingBaseServe {

    //@Scheduled(cron = "*/2 * * * * ?")
    public void test(){
        java.util.Date javaDate = new java.util.Date();
        Date sqlDate = new Date(javaDate.getTime());
        System.out.println(sqlDate);
    }
}
