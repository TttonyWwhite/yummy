package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.util.TokenCreater;

@RestController
@EnableAutoConfiguration
public class UtilController {
    //todo create token for upload image
    @RequestMapping("/getToken")
    public String getToken() {
        String bucket = "yummy-bucket";
        String accessKey = "j0dwMMGFcKPhncC7vb_PWXshbpiSMEWB69NiKhn4";
        String secretKey = "2vWVIw3WJfk314YN3e24ZnixdJMbyoZ14FXiqF--";

        String token = TokenCreater.getToken(bucket, accessKey, secretKey);

        return token;
    }
}
