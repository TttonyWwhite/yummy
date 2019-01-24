package pers.illiant.yummy.util;

import com.qiniu.util.Auth;

public class TokenCreater {

    public static String getToken(String bucket, String acKey, String seKey) {
        Auth auth = Auth.create(acKey, seKey);
        String token = auth.uploadToken(bucket);

        return token;
    }
}
