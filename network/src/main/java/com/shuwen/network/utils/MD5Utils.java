package com.shuwen.network.utils;

import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Utils {
    public static String md5(String content) {
        if (content == null) {
            return "";
        }
        try {
            byte[] b = content.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuilder outStrBuf = new StringBuilder(32);
            for (byte value : hash) {
                int v = value & 0xFF;
                if (v < 16) {
                    outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (Exception e) {
            Log.e("MD5Utils", "md5 error");
        }
        return "";
    }

}