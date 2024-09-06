package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgBasicQuestionsDao;
import com.example.demo4.mapper.BgDishesDao;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgBasicQuestions;
import com.example.demo4.pojo.po.BgDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class BgBasicQuestionsServer extends ServiceImpl<BgBasicQuestionsDao, BgBasicQuestions> {
    @Autowired
    private BgBasicQuestionsDao bgBasicQuestionsDao;
    public IPage<BgBasicQuestions> findQuestion(Search info){
        IPage<BgBasicQuestions> topage = new Page<>(info.getCurrentPage(), info.getPageSize());
        LambdaQueryWrapper<BgBasicQuestions> wr = new LambdaQueryWrapper<>();
        var aLong = bgBasicQuestionsDao.selectCount(wr);
        var questionsDaoQuestion = bgBasicQuestionsDao.findQuestion(topage,info);
        questionsDaoQuestion.getRecords().stream().forEach(v -> {
            var s = removeHtmlTags(v.getQuestionsName());
            v.setQuestionsName(s);
            v.setId(v.getQuestionsId().toString());
        });
        questionsDaoQuestion.setCurrent(aLong);
        return questionsDaoQuestion;
    }
    private static String removeHtmlTags(String htmlString) {
        // 定义正则表达式
        String regex = "<[^>]+>";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 使用 replaceAll 方法去掉 HTML 标签
        return pattern.matcher(htmlString).replaceAll("");
    }
}
