package com.example.demo4.servers;

import com.example.demo4.pojo.po.BgExerciseStore;
import com.example.demo4.pojo.po.BgUsersGroup;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DomeSerces {

    public void addWord(List<BgExerciseStore> list) throws IOException,IllegalArgumentException,IllegalAccessError {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();

        // 设置段落左对齐
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        int i = 0;
        for (BgExerciseStore e:list){
            i = i+1;
            if (e.getNewKeyType() == 1){// 添加换行符
                XWPFRun run1 = paragraph.createRun();
                run1.setFontFamily("setFontFamily");
                String htmlContent = e.getTitle();
                // 正则表达式模式，用于匹配 img 标签的 src 属性值
                String regex = "<img\\s+src=['\"]([^'\"]+)['\"]";
                // 编译正则表达式
                Pattern pattern = Pattern.compile(regex);
                // 创建匹配器对象
                Matcher matcher = pattern.matcher(htmlContent);
                run1.setText("（单选题）题干：");
                if (matcher.find()){
                    var stringList = sliceData(e.getTitle());
                    stringList.forEach(imageUrl -> {
                        if (imageUrl.contains("http")){
                            // 提取 src 属性值
                            imageUrl = matcher.group(1);
                            if(Objects.nonNull(imageUrl)){
                                //上传图片
                                try {
                                    imgas(imageUrl,run1);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        }else {
                            run1.setText(imageUrl);
                        }
                    });

                }else {
                    run1.setText(e.getTitle());
                }


                XWPFRun run = paragraph.createRun();
                run.addCarriageReturn();
                if (Objects.nonNull(e.getA())) run.setText("题目id:"+e.getExerid());
                run.addCarriageReturn();
                if (e.getA().contains("http")){
                    run.setText("选项A:");
                    var s = imgaszhuan(e.getA());
                    imgas(s,run);
                }else {
                    if (Objects.nonNull(e.getA()) && e.getA() != null) run.setText("选项A:"+e.getA());
                }
                run.addCarriageReturn();
                if (e.getB().contains("http")){
                    run.setText("选项B:");
                    var s = imgaszhuan(e.getB());
                    imgas(s,run);
                }else {
                    if (Objects.nonNull(e.getB()) && e.getB() != null) run.setText("选项B:"+e.getB());
                }
                run.addCarriageReturn();
                if (e.getC().contains("http")){
                    run.setText("选项C:");
                    var s = imgaszhuan(e.getC());
                    imgas(s,run);
                }else {
                    if (Objects.nonNull(e.getC()) && e.getC() != null) run.setText("选项C:"+e.getC());
                }

                run.addCarriageReturn();
                if (e.getD().contains("http")){
                    run.setText("选项D:");
                    var s = imgaszhuan(e.getD());
                    imgas(s,run);
                    run.addCarriageReturn();
                }else {
                    if (Objects.nonNull(e.getD()) && !e.getD().isEmpty()) run.setText("选项D:"+e.getD());
                    run.addCarriageReturn();
                }
                if (e.getE().contains("http")){
                    run.setText("选项E:");
                    var s = imgaszhuan(e.getE());
                    imgas(s,run);
                    run.addCarriageReturn();  // 添加换行符
                }else {
                    if (Objects.nonNull(e.getE()) && e.getE() != null && !e.getE().isEmpty()) run.setText("选项E:"+e.getE());
                }
                run.addCarriageReturn();  // 添加换行符
                if (e.getRightkey().contains("http")){
                    run.setText("答案:");
                    var s = imgaszhuan(e.getRightkey());
                    imgas(s,run);
                    run.addCarriageReturn();  // 添加换行符
                }else {
                    if (!e.getRightkey().isEmpty()) run.setText("答案:"+e.getRightkey());
                }

                run.addCarriageReturn();  // 添加换行符
                if (e.getAnalyze().contains("http")){
                    run.setText("答案解析:");
                    var s = imgaszhuan(e.getAnalyze());
                    imgas(s,run);
                    run.addCarriageReturn();  // 添加换行符
                }else {
                    run.setText("答案解析:"+e.getAnalyze());
                }

                run.addCarriageReturn();  // 添加换行符
                run.addCarriageReturn();  // 添加换行符
                run.addCarriageReturn();  // 添加换行符

            }
        }
        ClassPathResource resource = new ClassPathResource("/template");
        String filePath = resource.getFile().getPath();
        try (FileOutputStream out = new FileOutputStream(filePath+"/users.docx")) {
            document.write(out);
        }
    }
    public List<String> sliceData(String data) {

        // 正则表达式模式，用于匹配 <img> 标签中的 src 属性值
        String regex = "<img\\s+src=\"(.*?)\"\\s*/>";
        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);
        // 创建 matcher 对象
        Matcher matcher = pattern.matcher(data);
        List<String> result = new ArrayList<>();
        int lastIndex = 0;

        while (matcher.find()) {
            // 添加匹配前的文本
            if (matcher.start() > lastIndex) {
                result.add(data.substring(lastIndex, matcher.start()));
            }
            // 添加匹配的图片地址
            result.add(matcher.group(1));

            // 更新 lastIndex
            lastIndex = matcher.end();
        }
        // 添加最后一个匹配后的文本
        if (lastIndex < data.length()) {
            result.add(data.substring(lastIndex));
        }
        return result;
    }
    public void imgas(String imageUrl,XWPFRun run1) throws  IOException,IllegalArgumentException,IllegalAccessError{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(imageUrl);
        HttpResponse response = null;
        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream  is = entity.getContent();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        is.close();
        byte[] imageBytes = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(bais);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        bais.close();

        bais = new ByteArrayInputStream(imageBytes);


        try {
            run1.addPicture(bais, XWPFDocument.PICTURE_TYPE_PNG, "image.png",
                    Units.toEMU(width), Units.toEMU(height)); // Adjust the size as needed
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

    }

    public String imgaszhuan(String text){

        // 正则表达式模式，用于匹配 <img> 标签的 src 属性
//        String regex = "<img\\s+src=\"([^\"]+)\"\\s*/>";
        String regex = "<img\\s+src=['\"]?(.*?)['\"]?\\s+/>";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);

        // 创建 matcher 对象
        Matcher matcher = pattern.matcher(text);
        String imageUrl1 = null;
        while (matcher.find()) {
            // 提取 src 属性值
             imageUrl1 = matcher.group(1);
        }
        return imageUrl1;
    }


}
