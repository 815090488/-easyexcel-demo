package com.yangluyao.easyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author YLY
 * @version 1.0
 * @className EasyExcel
 * @date 2020/7/19 11:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@TableName("easy_excel")
public class EasyExcelModel {

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore  // 下载的Excel的表头中忽略此字段
    private Long id;

    @ExcelProperty("姓名")
    @TableField("name")
    private String name;

    @ExcelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ExcelProperty("地址")
    @TableField("address")
    private String address;

}
