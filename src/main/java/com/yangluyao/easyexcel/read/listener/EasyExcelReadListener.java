package com.yangluyao.easyexcel.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yangluyao.easyexcel.mapper.EasyExcelMapper;
import com.yangluyao.easyexcel.model.EasyExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YLY
 * @version 1.0
 * @className EasyExcelReadListener
 * @date 2020/7/19 11:47
 */
@Slf4j
public class EasyExcelReadListener extends AnalysisEventListener<EasyExcelModel> {

    @Autowired
    private EasyExcelMapper easyExcelMapper;

    private List<EasyExcelModel> easyExcelModels = new ArrayList<>();

    public EasyExcelReadListener(EasyExcelMapper easyExcelMapper) {
        this.easyExcelMapper = easyExcelMapper;
    }

    @Override
    public void invoke(EasyExcelModel easyExcel, AnalysisContext analysisContext) {
        log.info("开始读取文章：{}", easyExcel.toString());
        easyExcelModels.add(easyExcel);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("进入到：doAfterAllAnalysed  方法中");
        easyExcelModels.forEach(easyExcelModel -> {
            easyExcelMapper.insert(easyExcelModel);
        });
    }
}
