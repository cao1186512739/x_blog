package com.xblog.core.component;

import com.github.afkbrb.avatar.Avatar;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileComponent {

    @Value("${xblog.domain}")
    private String DOMAIN;

    @Value("${xblog.upload.dir}")
    private String UPLOAD_DIR;

    @Value(("${xblog.upload.url}"))
    private String UPLOAD_URL;

    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;

    /**
     * 上传路径，如E:/upload
     * 上传的文件访问url前缀，如http://www.image.com/upload/image
     * @param file  上传的文件
     * @return 文件访问url
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new NullPointerException("上传的文件不能为空！");
        }
        String realName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
        String datePath = datePath();
        String path = UPLOAD_DIR + "/" + datePath;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String filename = path + "/" + realName;
        file.transferTo(new File(filename));
        return urlPrefix() + "/" + datePath + "/" + realName;
    }

    public  String makeAvatar(){
        String avatarName = UUID.randomUUID().toString() + ".png";
        String avatarPath = UPLOAD_DIR + "/" + datePath() + "/" + avatarName;
        Avatar avatar = new Avatar();
        avatar.saveAsPNG(avatarPath);
        return urlPrefix() + "/" + datePath() + "/" + avatarName;
    }

    //地址前缀
    private String urlPrefix(){
        if(StringUtils.isBlank(CONTEXT_PATH) || CONTEXT_PATH.equals("/") || CONTEXT_PATH.equals("\\")) {
            CONTEXT_PATH = "";
        }
        return DOMAIN + CONTEXT_PATH + UPLOAD_URL;
    }

    //获取时间路径
    private String datePath(){
        Date date = new Date();
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
}
