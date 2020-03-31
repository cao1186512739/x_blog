package com.xblog.core.utils;

import org.springframework.beans.BeanUtils;

/**
 *
 * @description:dto转换DO工具类 do转换DTO
 *
 * @author: caobing
 */
public class BeanUtil {

    /**
     * dot 转换为Po 工具类
     *
     * @param dtoEntity
     * @param poClass
     * @return
     */
    public static <Po> Po dtoToPo(Object dtoEntity, Class<Po> poClass) {
        // 判断dto是否为空!
        if (dtoEntity == null) {
            return null;
        }
        // 判断poClass 是否为空
        if (poClass == null) {
            return null;
        }
        try {
            Po newInstance = poClass.newInstance();
            BeanUtils.copyProperties(dtoEntity, newInstance);
            // Dto转换Po
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * po转换为Dto 工具类
     *
     * @param poEntity
     * @param dtoClass
     * @return
     */
    public static <Dto> Dto poToDto(Object poEntity, Class<Dto> dtoClass) {
        // 判断dto是否为空!
        if (poEntity == null) {
            return null;
        }
        // 判断poClass 是否为空
        if (dtoClass == null) {
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(poEntity, newInstance);
            // Po转换Dto
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }
}
