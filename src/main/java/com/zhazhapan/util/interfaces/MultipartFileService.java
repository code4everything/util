package com.zhazhapan.util.interfaces;

import com.zhazhapan.util.model.SimpleMultipartFile;

/**
 * @author pantao
 * @since 2018/9/12
 **/
public interface MultipartFileService<T> {

    /**
     * 检测当前 {@link SimpleMultipartFile}是否存在，如果文件不存在则写入磁盘，否则不写入。返回 NULL 时会尝试调用 {@link
     * #getBySimpleMultipartFile(SimpleMultipartFile)}
     *
     * @param simpleMultipartFile {@link SimpleMultipartFile}
     *
     * @return 是否存在
     */
    default Boolean existsMultipartFile(SimpleMultipartFile simpleMultipartFile) { return null;}

    /**
     * 获取一条数据库记录，当返回值为 NULL时将文件写入磁盘，否则不写入
     *
     * @param simpleMultipartFile {@link SimpleMultipartFile}
     *
     * @return T 一个实体类
     */
    default T getBySimpleMultipartFile(SimpleMultipartFile simpleMultipartFile) { return null;}

    /**
     * 定义将数据写入数据库中的方法
     *
     * @param simpleMultipartFile {@link SimpleMultipartFile}
     *
     * @return T 实体类
     */
    default T saveEntity(SimpleMultipartFile simpleMultipartFile) {return null;}
}
