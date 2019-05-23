package top.wello.health.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.wello.health.dao.WXRunDAO;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WXRunServiceTest {

//    private static

    @Resource
    WXRunService wxRunService;

    @Test
    public void decrypt() {
        long start = System.currentTimeMillis();
        String encryptedData = "qH4p4JrE6294vENLLenOagy2A8/h9XalupHvmty884TEd3lzqgvdEhxKsibD3vAJI9aWNJPqhMpir5xpt8azwCIuVVg65wlni8M9VZrQOE8FatgSP7nagUF4fvJ1IKzZ32BqQ8dV6iiRCqSemVE4B9xjVMJkvqY5hiToIVMVJ/kzkFG1ynTZWYTKThd8tKJriB2PS1DNnnaCO55l4OZuCfBujptZg1+HJm7jZUT3Ay4T8hAlbWLyiY82z2v3mZOU9gW7/9guU5xNOnM60hIxG6crD1F+59RobVKWQGO443GO365X3codBnhUNBTvbCv5MF1v97Znwn1rZCWJ3IxsZ+GFoZRAj0juP2l0P6KbZYPa5EaZJdZH6gdQThr/xl07HSux3ZC6rGCGp/ZOL+vPhbyHmgLVqzF0zF+nPeki4g/w0R3qiKDItr7DEMm3e9538enwIQaQlx1hSypTxvdQFs9EdO9o2wurW1AMLUKWKw3uOQk53P2rsfUr9I4vpyYZ6ggMqqmDLdQkGxKqbfHd0ay5X7qaNJ6gIGXqdg8D6He2rhCsCikmJ4qEFz7PU8YVOLCsBOgO+RuC+hufwaa6NrOJoJL/v1txmKLaB0X6gBo3op8uXG4jPGR1ypBZJjm6j/uTSxI14N2MUI6Gk+THnpNtigY9x98YuACT5rMHnbMRF6JIIuhVYgok9LNeQit7hUsw8cLSdpRgjfRHZ8NDrdq0MkZKqdA31b03J+Cl3Go9okZ4urAyyEpJo2PI0SLPswt7U4Hfqz3B34E3glSjPod5yMHyJ3R2MgAumt7U1vZ4Usuf9iVsaYG+BjFEr9QijbEHG9/EGSSj1nEgJtkdshfcbjSJLcjDp2DnGQNDeJj/no+qbLKyOTuL0bgXDb5R0qkeVeKFStBLQoGPkUnsYSHZGReo13WGtlveB5EaPKoJwnu3zPrRZZOlzn9XtKEbgwKtPNGc1wNE1K528fY23ZsTdehjyXosGfWFLj40BZhSmi9U7cySCAnskVpPzXmGWq+XTmEFK5Fy1NhO3KGdCp8pbOUVcSV+9NYy7PEtg8OZ6bwtO74+vdISFEtFefUeUGQ+F6Kcc8mVOzeWmT3A/s1PKNDrnK2m/Vn4o5Y0eKC4EKQm/1noMcPYWH9kHwUpoAH+/b5sXsVTWiu7DxJB4k1A2KC7XVvv3UrL2e5p/C8roKhA5rHXU5ZxhNGGKd8HojyKGBiJ/I2yyEUpGNEFUVFNhnfRQn875IXfdKmh/+3B3YLXnxEyhRGiakWSbd2Kd+w9k2kGIv+m+3FDBsmZdBoFm5XQmJWkCpS80m7AXZ8oeH6xIi5PNniTxrM5ZEP2SbVTMXxyPTh/gvr4w1vSjmEdt7WOZYeSp8z6ns+gOmuvYz6JyAWBRchfMKmtYbGJb8w/pwERd+Kb7iZiHTaZb6RqkFQqc/1k1BwxoeFRlJYOdx5y+JH7VDPHyG6vfE7AZMu6B+JGM6x71jTWQlIdHNcBUOb92rvIHm7zFc6oinrYBa89hd7R0rnWtoIgBf5f";
        String iv = "lKs5YJyYK/DbqrsZ5KzO6w==";
        String session = "+Elf1j/wCIgpdtBeyTa7Lg==";
        WXRunDAO res = wxRunService.decrypt(encryptedData, iv, session);
        Assert.assertNotNull(res);

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long startOfToday = calendar.getTimeInMillis() / 1000;
        int stepToday = 0;

        for (WXRunDAO.StepInfoListBean step: res.getStepInfoList()) {
            if (step.getTimestamp()  >= startOfToday) {
                stepToday += step.getStep();
            }
        }
        System.out.println(stepToday);
        System.out.println("cost " + (System.currentTimeMillis() - start));
    }

}