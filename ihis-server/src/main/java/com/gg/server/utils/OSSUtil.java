package com.gg.server.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

/**
 * @author: GG
 * @date: 2021/5/30 1:40 上午
 */
public class OSSUtil {
    private static String ENDPOINT = "oss-cn-guangzhou.aliyuncs.com";
    private static String ACCESSKEYID = "LTAI5tAVNe2efYFHh4MURUEc";
    private static String ACCESSKEYSECRET = "lbyAShrpMhvAwttmteXIQbRGL7JBL7";
    private static String BUKKETNAME = "gg-ihis";
    private static String DOWNLOADURL = "http://gg-ihis.oss-cn-guangzhou.aliyuncs.com";

    public OSS getClient() {
        OSS oss = new OSSClientBuilder().build(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        return oss;
    }
}
