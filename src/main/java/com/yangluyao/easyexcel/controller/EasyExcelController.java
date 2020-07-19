package com.yangluyao.easyexcel.controller;


import com.alibaba.excel.EasyExcel;
import com.yangluyao.easyexcel.mapper.EasyExcelMapper;
import com.yangluyao.easyexcel.model.EasyExcelModel;
import com.yangluyao.easyexcel.read.listener.EasyExcelReadListener;
import com.yangluyao.easyexcel.service.IEasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * @author YLY
 * @version 1.0
 * @className EasyExcelController
 * @date 2020/7/19 11:46
 */
@Controller
public class EasyExcelController {
    @Autowired
    private IEasyExcelService iEasyExcelService;

    @Autowired
    private EasyExcelMapper easyExcelMapper;

    @GetMapping("/tempalte")
    public void tempalte(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("数据上传模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(),EasyExcelModel.class ).sheet("数据上传模板").doWrite(new ArrayList<EasyExcelModel>());
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(),EasyExcelModel.class , new EasyExcelReadListener(easyExcelMapper)).sheet().doRead();
        return "success";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("数据表格下载", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), EasyExcelModel.class).sheet("数据表格").doWrite(iEasyExcelService.list());
    }

}
