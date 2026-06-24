package com.resume.service.impl;

import com.resume.pojo.User;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class WordExportService {

    public void exportUserToWord(User user, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition", "attachment; filename=个人简历.docx");

        // 创建Word文档
        XWPFDocument document = new XWPFDocument();

        // 标题
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        title.setSpacingAfter(200);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("个人简历");
        titleRun.setFontSize(24);
        titleRun.setBold(true);
        titleRun.setFontFamily("微软雅黑");

        // 联系信息
        XWPFParagraph contactPara = document.createParagraph();
        contactPara.setAlignment(ParagraphAlignment.CENTER);
        contactPara.setSpacingAfter(300);
        XWPFRun contactRun = contactPara.createRun();
        contactRun.setText("邮箱：" + (user.getEmail() != null ? user.getEmail() : "") + "    ");
        contactRun.setText("电话：" + (user.getPhone() != null ? user.getPhone() : ""));
        contactRun.setFontFamily("微软雅黑");
        contactRun.setFontSize(12);

        // 基本信息
        createSectionTitle(document, "基本信息");
        createBasicInfoTable(document, user);

        // 求职岗位
        createSectionTitle(document, "求职岗位");
        createJobInfo(document, user);

        // 教育背景
        createSectionTitle(document, "教育背景");
        createEducationInfo(document, user);

        // 个人优势
        createSectionTitle(document, "个人优势");
        createSkillInfo(document, user);

        // 工作经历
        createSectionTitle(document, "工作经历");
        createWorkExperience(document, user);

        // 项目经验
        createSectionTitle(document, "项目经验");
        createProjectExperience(document, user);

        // 证书详情
        createSectionTitle(document, "证书详情");
        createCertificateInfo(document);

        // 自我评价
        createSectionTitle(document, "自我评价");
        createSelfEvaluation(document);

        // 页脚
        XWPFParagraph footerPara = document.createParagraph();
        footerPara.setAlignment(ParagraphAlignment.CENTER);
        footerPara.setSpacingBefore(300);
        XWPFRun footerRun = footerPara.createRun();
        footerRun.setText("感谢您花时间阅读我的简历，期待与您共事！");
        footerRun.setFontFamily("微软雅黑");
        footerRun.setFontSize(11);

        // 写入响应
        document.write(response.getOutputStream());
        document.close();
    }

    private void createSectionTitle(XWPFDocument document, String titleText) {
        XWPFParagraph sectionTitle = document.createParagraph();
        sectionTitle.setSpacingBefore(200);
        sectionTitle.setSpacingAfter(200);
        XWPFRun titleRun = sectionTitle.createRun();
        titleRun.setText(titleText);
        titleRun.setFontSize(16);
        titleRun.setBold(true);
        titleRun.setFontFamily("微软雅黑");
        titleRun.addBreak();
    }

    private void createBasicInfoTable(XWPFDocument document, User user) {
        XWPFTable table = document.createTable(4, 4);

        // 设置表格宽度
        table.setWidth("80%");

        // 填充数据
        table.getRow(0).getCell(0).setText("姓名：");
        table.getRow(0).getCell(1).setText(user.getName() != null ? user.getName() : "");
        table.getRow(0).getCell(2).setText("性别：");
        table.getRow(0).getCell(3).setText(user.getSex() != null ? user.getSex() : "");

        table.getRow(1).getCell(0).setText("电话：");
        table.getRow(1).getCell(1).setText(user.getPhone() != null ? user.getPhone() : "");
        table.getRow(1).getCell(2).setText("QQ：");
        table.getRow(1).getCell(3).setText(user.getQq() != null ? user.getQq() : "");

        table.getRow(2).getCell(0).setText("工作年限：");
        table.getRow(2).getCell(1).setText(user.getSeniority() + "年");
        table.getRow(2).getCell(2).setText("年龄：");
        table.getRow(2).getCell(3).setText(user.getAge() + "岁");

        table.getRow(3).getCell(0).setText("专业：");
        table.getRow(3).getCell(1).setText(user.getSpeciality() != null ? user.getSpeciality() : "");
        table.getRow(3).getCell(2).setText("学历：");
        table.getRow(3).getCell(3).setText(user.getEducation() != null ? user.getEducation() : "");

        // 设置表格样式
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                XWPFTableCell cell = table.getRow(i).getCell(j);
                cell.setColor("F2F2F2");
                XWPFParagraph paragraph = cell.getParagraphs().get(0);
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run = paragraph.createRun();
                run.setFontFamily("微软雅黑");
                run.setFontSize(11);
            }
        }
    }

    private void createJobInfo(XWPFDocument document, User user) {
        XWPFParagraph jobPara = document.createParagraph();
        jobPara.setIndentationFirstLine(400);
        XWPFRun jobRun = jobPara.createRun();
        jobRun.setText("应聘职位：" + (user.getCareer() != null ? user.getCareer() : ""));
        jobRun.addBreak();
        jobRun.setText("目前属于离职状态，可立即上岗。");
        jobRun.setFontFamily("微软雅黑");
        jobRun.setFontSize(11);
    }

    private void createEducationInfo(XWPFDocument document, User user) {
        XWPFParagraph eduPara = document.createParagraph();
        eduPara.setIndentationFirstLine(400);
        XWPFRun eduRun = eduPara.createRun();

        if (user.getDate() != null) {
            eduRun.setText(new SimpleDateFormat("yyyy年MM月dd日").format(user.getDate()) + " 毕业");
            eduRun.addBreak();
        }

        eduRun.setText("学校：" + (user.getSchool() != null ? user.getSchool() : ""));
        eduRun.addBreak();
        eduRun.setText("专业：" + (user.getSpeciality() != null ? user.getSpeciality() : ""));
        eduRun.addBreak();
        eduRun.setText("学历：" + (user.getEducation() != null ? user.getEducation() : ""));
        eduRun.setFontFamily("微软雅黑");
        eduRun.setFontSize(11);
    }

    private void createSkillInfo(XWPFDocument document, User user) {
        XWPFParagraph skillPara = document.createParagraph();
        skillPara.setIndentationFirstLine(400);
        XWPFRun skillRun = skillPara.createRun();
        skillRun.setText(removeHtmlTags(user.getSkill()));
        skillRun.setFontFamily("微软雅黑");
        skillRun.setFontSize(11);
    }

    private void createWorkExperience(XWPFDocument document, User user) {
        XWPFParagraph work1Para = document.createParagraph();
        work1Para.setIndentationFirstLine(400);
        XWPFRun work1Run = work1Para.createRun();
        work1Run.setText(removeHtmlTags(user.getWorkone()));
        work1Run.setFontFamily("微软雅黑");
        work1Run.setFontSize(11);

        if (user.getWorktwo() != null && !user.getWorktwo().isEmpty()) {
            XWPFParagraph work2Para = document.createParagraph();
            work2Para.setIndentationFirstLine(400);
            XWPFRun work2Run = work2Para.createRun();
            work2Run.setText(removeHtmlTags(user.getWorktwo()));
            work2Run.setFontFamily("微软雅黑");
            work2Run.setFontSize(11);
        }
    }

    private void createProjectExperience(XWPFDocument document, User user) {
        XWPFParagraph project1Para = document.createParagraph();
        project1Para.setIndentationFirstLine(400);
        XWPFRun project1Run = project1Para.createRun();
        project1Run.setText(removeHtmlTags(user.getWorkthree()));
        project1Run.setFontFamily("微软雅黑");
        project1Run.setFontSize(11);

        if (user.getWorkfour() != null && !user.getWorkfour().isEmpty()) {
            XWPFParagraph project2Para = document.createParagraph();
            project2Para.setIndentationFirstLine(400);
            XWPFRun project2Run = project2Para.createRun();
            project2Run.setText(removeHtmlTags(user.getWorkfour()));
            project2Run.setFontFamily("微软雅黑");
            project2Run.setFontSize(11);
        }
    }

    private void createCertificateInfo(XWPFDocument document) {
        XWPFParagraph certPara = document.createParagraph();
        certPara.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun certRun = certPara.createRun();
        certRun.setText("暂无");
        certRun.setFontFamily("微软雅黑");
        certRun.setFontSize(11);
    }

    private void createSelfEvaluation(XWPFDocument document) {
        XWPFParagraph evalPara = document.createParagraph();
        evalPara.setIndentationFirstLine(400);
        XWPFRun evalRun = evalPara.createRun();
        evalRun.setText("自学能力强，抗压能力大；工作认真，具有很强的责任心。");
        evalRun.addBreak();
        evalRun.setText("热爱运维岗位，有一定运维经验；");
        evalRun.setFontFamily("微软雅黑");
        evalRun.setFontSize(11);
    }

    private String removeHtmlTags(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        return html.replaceAll("<[^>]+>", "").trim();
    }
}
