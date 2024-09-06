package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * bg_exercise_store
 * @author 
 */
@Data
public class BgExerciseStore implements Serializable {
    /**
     * 题目ID
     */
    private Integer exerid;

    /**
     * 题干
     */
    private String title;

    /**
     * 课程ID
     */
    private String courseid;

    /**
     * 目录ID
     */
    private Integer catid;

    /**
     * 一共有几个选项，默认为4个，即ABCD
     */
    private Integer optnum;

    /**
     * 选项A内容
     */
    private String a;

    /**
     * 选项B内容
     */
    private String b;

    /**
     * 选项C内容
     */
    private String c;

    /**
     * 选项D内容
     */
    private String d;

    /**
     * 选项E内容
     */
    private String e;

    /**
     * 选项F内容
     */
    private String f;

    /**
     * 选项G内容
     */
    private String g;

    /**
     * 选项H内容
     */
    private String h;

    /**
     * 正确答案。如果是多选题，则用字符串表示答案。如果是一题多解，则多个正解之间用分隔符区分
     */
    private String rightkey;

    /**
     * 1单选；2多选；3其他
     */
    private String keytype;

    /**
     * 解析
     */
    private String analyze;

    /**
     * 题目来源于教材的页码，如基础知识P16
     */
    private String matref;

    /**
     * 1-5,5最难
     */
    private Integer diffcoeff;

    /**
     * 1-5,5最常考
     */
    private Integer freqcoeff;

    /**
     * 1历年试题；2模拟题；3仿真题
     */
    private String exertype;

    /**
     * 题目的来源，如2013年5月真题第26题
     */
    private String source;

    /**
     * 1有效；0无效
     */
    private Boolean isvalid;

    private Boolean ismultiexer;

    private Integer multiexerid;

    /**
     * 是否包含了HTML标签，1是，0否。主要用于传送给IOS，判断是否有标签，方便TableView显示
     */
    private Boolean ishtmltag;

    /**
     * 该题目所有人做的总次数
     */
    private Integer practimesamount;

    /**
     * 该题目所有人做的正确的次数
     */
    private Integer correcttimesamount;

    /**
     * 该题目所有人做的总正确率
     */
    private BigDecimal accuracyamount;

    /**
     * 串讲题组ID
     */
    private Integer reviewexergroupid;

    private Integer exergroupid;

    private String subtitle;

    /**
     * 习题详情
     */
    private String econtent;

    /**
     * 解析附件ID
     */
    private String jcontent;

    /**
     * 机构ID
     */
    private Integer agencyid;

    /**
     * 0：未审 1:已审
     */
    private Boolean isaudit;

    private Integer adminid;

    /**
     * 审核人
     */
    private Integer auditid;

    /**
     * 审核时间
     */
    private Date audittime;

    /**
     * 创建时间
     */
    private Date createtime;

    private Integer sourceexerid;

    /**
     * 多选题得分规则 输入0代表少选不得分，输入1代表少选固定得分，输入2代表少选每个选项得分，多选、错选、没选不得分；该规则仅多选题、不定项选择题生效
     */
    private Boolean scoreRules;

    /**
     * 多选题少选得分,若选择固定得分，则该分数为少选时的得分，若选择每个选项得分，则该分数为少选时每个选项的得分
     */
    private BigDecimal scoreLess;

    /**
     * 新加题目类型字段：1单选，2多选 3主观题 4判断题 5填空题 6不定项选择题 7材料题
     */
    private Byte newKeyType;

    /**
     * 是否乱序匹配答案(仅限填空题)
     */
    private Boolean isUnorderedMate;

    /**
     * 是否属于材料题的小题(0=不是,1=是)
     */
    private Integer isMaterial;

    /**
     * 旧必过三级目录ID
     */
    private Integer oldCatId;

    private static final long serialVersionUID = 1L;
}