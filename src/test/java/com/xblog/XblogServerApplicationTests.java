package com.xblog;

import com.xblog.core.component.FileComponent;
import com.xblog.core.component.RedisComponent;
import com.xblog.core.model.po.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class XblogServerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private FileComponent fileComponent;

    @Test
    void contextLoads() {
    }



    @Test
    public void redisListTest(){
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Menu menu = new Menu();
            menu.setId(++i);
            menu.setIcons("icon-" + i);
            menu.setEnabled(true);
            menu.setIsAuth(true);
            menu.setMenuDes("菜单描述："+ i);
            menu.setMenuName("菜单名称：" + i);
            menu.setMenuPath("/menu/" + i);
            menu.setParentId(i);
            menus.add(menu);
        }

        boolean b = redisComponent.setForList("menus", menus);

        //Long aLong = redisTemplate.opsForList().rightPushAll("menu1", menus);

        if (b){
            List<?> listAll = redisComponent.getForListAll("menus");

            List<Menu> menuList = (List<Menu>) listAll;

            menuList.stream();
        }


    }

    @Test
    public void testFileComment(){
        String s = fileComponent.makeAvatar();
        System.out.println(s);
    }



}
