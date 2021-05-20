
package com.shuwen.http.bean;

import java.util.Date;


public class OssInfo {

    private String securityToken;
    private String accessKeySecret;
    private String accessKeyId;
    private Date expiration;
    private String bucketName;
    private String endPoint;
    private String uploadPath;
    private String ossFileUrl;

    public String getOssFileUrl() {
        return ossFileUrl;
    }

    public void setOssFileUrl(String ossFileUrl) {
        this.ossFileUrl = ossFileUrl;
    }


    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUploadPath() {
        return uploadPath;
    }

}