package com.jinguizi;

import com.jinguizi.bean.Authority;
import com.jinguizi.mapper.AuthMapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Test01
 * @Description:
 * @Auther: hancoong
 * @Version: 1.0
 * @create 2020/12/23  11:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Test
    public void test2() throws IOException {
//        0.准备数据
        /*User user = new User();
        user.setUserId(1);
        user.setUserName("hhhh");
        user.setUserSex("男");*/

        String[] titles = {"编号","名字","性别"};

        /**
         * 先写入 标题栏数据
         */
//        1.创建文件对象   创建HSSFWorkbook只能够写出为xls格式的Excel
//        要写出 xlsx 需要创建为 XSSFWorkbook 两种Api基本使用方式一样
        HSSFWorkbook workbook = new HSSFWorkbook();

//        2.创建表对象
        HSSFSheet sheet = workbook.createSheet("users");

//        3.创建标题栏（第一行）  参数为行下标  行下标从0开始
        HSSFRow titleRow = sheet.createRow(0);

//        4.在标题栏中写入数据
        for (int i = 0; i < titles.length; i++) {
//            创建单元格
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }
        /**
         * 写入用户数据
         */
//       5 创建行 如果是用户数据的集合 需要遍历
        HSSFRow row = sheet.createRow(1);

//       6 将用户数据写入到行中
        row.createCell(0).setCellValue("1");
        row.createCell(1).setCellValue("张三");
        row.createCell(2).setCellValue("男");

//        文件保存到本地 参数为要写出的位置
        workbook.write(new FileOutputStream("D://test.csv"));




    }


    @Test
    public void test03(){
        ArrayList<List<String>> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("张三");
        list1.add(list2);
        for (List<String> strings : list1) {
            for (String string : strings) {
                System.out.println(string);
            }
        }

        list2.add("李四");
        for (List<String> strings : list1) {
            for (String string : strings) {
                System.out.println(string);
            }
        }
    }



    @Autowired
    private AuthMapper authmapper;

    @Test
    public void loadMenu(){
        List<Authority> list = authmapper.findAuthorityByParentId(0,1);
        List<Map<String, Object>> resultList = findAuthority(list,1);
        System.out.println(resultList);
    }

    public List<Map<String, Object>> findAuthority(List<Authority> list,Integer id){
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Authority authority : list) {
            HashMap<String, Object> map = new HashMap<>();
            List<Map<String, Object>> children = new ArrayList<>();
            map.put("id",authority.getId());
            map.put("component",authority.getComponent());
            map.put("name",authority.getName());
            map.put("path",authority.getPath());
            map.put("type",authority.getType());
            map.put("parent_id",authority.getParentId());
            map.put("order",authority.getOrder());
            map.put("icon",authority.getIcon());
            List<Authority> authorityList = authmapper.findAuthorityByParentId(authority.getId(),id);
            if (authorityList.size()>0){
                children = findAuthority(authorityList,id);
            }
            map.put("children",children);
            maps.add(map);
        }
        return maps;
    }
}
