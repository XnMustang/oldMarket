package com.wlrss.oldmarket.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author jamesBond
 * @createTime 2020/7/1/001 19:57
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl service;

    @Autowired
    GoodsMapper goodsMapper;


    /**
     * @param goodsname
     * @param price
     * @param described
     * @param sellmassage
     * @return
     */
    @RequestMapping("/addGood")
    public String addGood(String goodsname, BigDecimal price, String described, String sellmassage) {

        Goods goods = new Goods();
        goods.setGoodsname(goodsname);
        goods.setPrice(price);
        goods.setDescribed(described);
        goods.setSellmassage(sellmassage);

        service.insertGoods(goods);
        return "redirect:/dash-items.html";

    }


    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println("文件名" + uuid);
                String fileName = uuid + file.getOriginalFilename();
                File f1 = new File("D:/AAtemp" + "/" + fileName);
                System.out.println(f1);
                if (!f1.getParentFile().exists()) {
                    f1.getParentFile().mkdirs();
                }
                System.out.println("这是文件名-----------" + file.getName());
                outputStream.write(file.getBytes());
                file.transferTo(f1);
                outputStream.flush();
                outputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败" + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败" + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败 文件不能为空";
        }
    }

    /**
     * 导航-最新商品列表
     *
     * @return
     */
    @RequestMapping("queryAll")
    public IPage<Goods> getGoodsList(Model model) {
        model.addAttribute("goodsList", service.findAll());
        return service.findAll();
    }


}
