package com.zhazhapan.util.model;

import com.alibaba.fastjson.JSONObject;

import java.io.File;

/**
 * @author pantao
 * @since 2018/9/12
 **/
public class SimpleMultipartFile {

    private String filename;

    private String originalFilename;

    private String storagePath;

    private Long size;

    private String md5;

    private JSONObject values = new JSONObject();

    public SimpleMultipartFile put(String key, Object value) {
        values.put(key, value);
        return this;
    }

    public JSONObject getValues() {
        return values;
    }

    public String getMd5() {
        return md5;
    }

    public SimpleMultipartFile setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public SimpleMultipartFile setSize(Long size) {
        this.size = size;
        return this;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public SimpleMultipartFile setStoragePath(String storagePath) {
        this.storagePath = storagePath + (storagePath.endsWith(File.separator) ? "" : File.separator);
        return this;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public SimpleMultipartFile setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public SimpleMultipartFile setFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
