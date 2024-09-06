package com.example.demo4.pojo.JIU;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;

/**
 * b2_agency
 * @author 
 */
@Data
@TableName(value = "b2_agency")
public class B2Agency implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Integer agencyid;
    /**
     * 名称
     */
    private String name;

    private String shortname;

    /**
     * 标题
     */
    private String title;

    private String logo;

    /**
     * 域名
     */
    private String domain;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String tel;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 手机
     */
    private String mobile;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 截止时间
     */
    private Date enddate;

    /**
     * 主营项目
     */
    private String mainproject;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区/县
     */
    private String area;

    /**
     * 状态 1：正式；0：试用；
     */
    private Integer status;

    /**
     * 支付宝 合作者身份Id
     */
    private String alipayid;

    /**
     * 支付宝账号
     */
    private String alipayemail;

    /**
     * 支付宝安全密钥
     */
    private String alipaykey;

    private String icpnumber;

    /**
     * 资询热线
     */
    private String footertel;

    /**
     * 咨询QQ
     */
    private String footerqq;

    /**
     * 联系地址
     */
    private String footeradr;

    /**
     * 咨询邮箱
     */
    private String footeremail;

    /**
     * 站点介绍
     */
    private String siteintro;

    /**
     * Cnzz统计ID
     */
    private String cnzzstatiskey;

    /**
     * Cnzz图标是否显示
     */
    private Boolean cnzzstatisisshow;

    /**
     * 百度统计Key
     */
    private String baidustatiskey;

    /**
     * 百度统计图标是否显示
     */
    private Boolean baidustatisisshow;

    /**
     * 水印图片
     */
    private String watermark;

    /**
     * 代理商类型，1机构，2个人，3区域，4推广
     */
    private Integer agencytype;

    /**
     * LOGO链接
     */
    private String logolink;

    /**
     * 登录展示图
     */
    private String loginimage;

    /**
     * 注册展示图
     */
    private String registerimage;

    /**
     * 浮动边栏电话
     */
    private String sidebartel;

    /**
     * 浮动边栏二维码
     */
    private String sidebarqrcode;

    /**
     * 浮动边栏客服类型 1个人QQ，2企业QQ，3乐语，4百度商桥，5微信号
     */
    private Integer sidebarqqtype;

    /**
     * 浮动边栏客服号码Id或密钥
     */
    private String sidebarqq;

    /**
     * 浮动边栏客服邮箱
     */
    private String sidebaremail;

    /**
     * 是否提醒过:1:是0:否
     */
    private Integer isremind;

    /**
     * 1:开通 0:不开通--答疑
     */
    private Integer isqa;

    /**
     * 微信APPID
     */
    private String wxpayappid;

    /**
     * 微信支付商户号
     */
    private String wxpaymchid;

    /**
     * 微信支付密钥
     */
    private String wxpaykey;

    /**
     * 微信APPSecret
     */
    private String wxpaysecret;

    /**
     * 支付宝RSA私钥
     */
    private String alipayprivatekey;

    /**
     * 手机站域名
     */
    private String mdomain;

    /**
     * 是否显示APP下载二维码
     */
    private Boolean isshowappqrcode;

    /**
     * 是否允许用户注册
     */
    private Boolean isallowregister;

    private Boolean isdownloadvideo;

    /**
     * 微信公众号
     */
    private String wechatofficialaccounts;

    /**
     * 是否显示提问信息功能
     */
    private Boolean isshowquestion;

    /**
     * 是否有串讲开通权限
     */
    private Boolean isreview;

    /**
     * 是否扣费,客户在OEM后台开通精讲及串讲将不会从代理商费用账户扣费
     */
    private Boolean isfee;

    /**
     * 是否自答疑
     */
    private Boolean isselfanswers;

    /**
     * 是否可登陆定制app
     */
    private Boolean iscustomization;

    /**
     * 阿里云短信通信签名
     */
    private String alimsgsign;

    /**
     * 阿里云短信通信模板
     */
    private String alimsgtemplate;

    /**
     * 阿里云短信通信accesskeyid
     */
    private String alimsgaccesskeyid;

    /**
     * 阿里云短信通信accesskeysecret
     */
    private String alimsgaccesssecret;

    /**
     * 水印位置 1左上 2右上 3左下 4右下
     */
    private Integer watermarkposition;

    /**
     * 安卓版本信息,定制
     */
    private String appvno;

    /**
     * Ios版本信息-定制
     */
    private String appvnoios;

    /**
     * 是否公开用户的提问
     */
    private Boolean isopenquestion;

    /**
     * 上级机构ID
     */
    private Integer parentid;

    /**
     * 试用账号是否发送验证码（针对APP有效）
     */
    private Boolean istrysendcode;

    /**
     * 是否显示html5的浮动图标
     */
    private Boolean isshowbar;

    /**
     * 站点标题图标
     */
    private String titleicon;

    /**
     * 是否充许试用
     */
    private Boolean isallowtrail;

    /**
     * 是否显示模考答案0:不显示 1:显示
     */
    private Boolean isshowanswer;

    private String app;

    /**
     * 定制APP 0待定 -1非定制 1已定制
     */
    private Integer appcustom;

    /**
     * 所属管理员用户名
     */
    private String adminname;

    /**
     * 是否导入直播数据
     */
    private Boolean isimportlivedata;

    /**
     * 隐私政策
     */
    private String hiddenpolicy;

    private Integer isshowproductvalid;

    private String managementarea;

    private Integer updatemode;

    private Integer downpaymentratio;

    private String coffeecode;

    private String coffeetoken;

    private String coffeesign;

    private String coffeeagentid;

    private String collectionqrcode;

    private Integer paytype;

    private String paytel;

    private String alicollectionqrcode;

    private Integer istry;

    private String networkrecord;

    private String certificate;

    private String companyname;

    private Integer isshowname;

    private String weixinverifytxt;

    private String region;

    private String ysupdatetime;

    private String privacyprotocolname;

    private Integer rateviewppt;

    private String programappid;

    private String programappsecret;

    private Integer uploadlimit;

    /**
     * 客服浮窗位置：1-左上；2-左中；3-左下；4-右上；5-右中；6-右下；    
     */
    private Integer barposition;

    private Integer iosupdatemode;

    /**
     * 0:不能购买1:能购买
     */
    private Byte isbuy;

    /**
     * 管理员集
     */
    private String adminid;

    /**
     * logo反白图片
     */
    private String logofb;

    /**
     * OEM客服浮窗开关: 0 关闭, 1 开启
     */
    private Integer isfloating;

    /**
     * pc咨询浮窗：0-关；1-开
     */
    private Boolean ispcshowbar;

    private String regionalmanager;

    /**
     * 商品课程试看: 0-不支持试看, 1-支持试看
     */
    private Integer tryandsee;

    /**
     * seo关键词
     */
    private String seokeywords;

    private String domainrecordid;

    private String mdomainrecordid;

    private Integer joinmyselflive;

    /**
     * 展示商品划线价/零售价：0-不展示；1-展示；
     */
    private Boolean ifshowmarketprice;

    /**
     * 就否可以api注册账号1:可以0:不可以
     */
    private Integer isapiregister;

    /**
     * 是否展示vid：0-不展示；1-展示；
     */
    private Integer isshowvid;

    /**
     * 域名协议
     */
    private String domainhttp;

    /**
     * 用户协议(富文本)
     */
    private String useragreement;

    /**
     * 机构更新告示
     */
    private String report;

    /**
     * 机构升级状态(0-未升级，1-升级中，2-已升级)
     */
    private Boolean upgradestatus;

    /**
     * 客户分类:ABCD
     */
    private String customertype;

    /**
     * 在新必过的默认用户的登录名
     */
    private String gbusername;

    /**
     * 在新必过的默认用户的密码
     */
    private String gbpassword;

    /**
     * 区域经理ID，逗号间隔，例: 136,251,71
     */
    private String areamanager;

    private static final long serialVersionUID = 1L;
}