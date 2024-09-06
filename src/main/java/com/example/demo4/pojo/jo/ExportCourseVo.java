package com.example.demo4.pojo.jo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 课程excel导出模型
 *
 * @author cjf
 * @date 2022年11月10日 14:56
 */
@Data
@ToString(callSuper = true)
@HeadRowHeight(32)
@ContentRowHeight(16)
@ColumnWidth(20)
public class ExportCourseVo {

    /**
     * 序号
     */
    @ExcelProperty(value = "序号", index = 0)
    private Integer index;

    /**
     * 课程代码
     */
    @ExcelProperty(value = "课程代码", index = 1)
    private String courseCode;

    /**
     * 课程名称
     */
    @ExcelProperty(value = "课程名称", index = 2)
    private String courseName;

    /**
     * 预设学分
     */
    @ExcelProperty(value = "预设学分", index = 3)
    private Integer defaultCredits;

    /**
     * 预设考试类型
     */
    @ExcelProperty(value = "预设考试类型", index = 4)
    private String defaultExamType;

    /**
     * 预设考试方式
     */
    @ExcelProperty(value = "预设考试方式", index = 5)
    private String defaultExamWay;

    /**
     * 预设合格分
     */
    @ExcelProperty(value = "预设合格分", index = 6)
    private Integer defaultPassScore;

    /**
     * 关联专业信息(个)
     */
    @ExcelProperty(value = "关联专业信息(个)", index = 7)
    private Integer majorNumBer;

    /**
     * 创建时间
     */
    @ColumnWidth(25)
    @ExcelProperty(value = "创建时间", index = 8)
    private LocalDateTime createTime;

}