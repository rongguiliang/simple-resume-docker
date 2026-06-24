/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.17.128
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : 192.168.17.128:3306
 Source Schema         : heber

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 19/09/2025 10:44:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NOT NULL,
  `blog` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `career` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `education` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `school` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seniority` int(11) NOT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `skill` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `speciality` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `workfour` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `workone` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `workthree` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `worktwo` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'CQ', 28, 'http://47.121.207.32:8081', '运维工程师', '大专', 'heber@xx.com', 'heber', 'e10adc3949ba59abbe56e057f20f883e', '1234567890', '123456789', 'xxx科技学院', 6, '男', '熟练掌握 Linux 安装初始化配置，常用命令，熟练运用 shell 撰写维护脚本。<br/>\r\n熟悉 mysql 数据库管理，主从复制，熟练使用 sql 语句;熟悉 redis 部署，持久化，主从、哨兵模式。<br/>\r\n熟悉 Docker 相关技术栈，docker-compose 编写和修改，熟悉 dockerfile 编写，熟练运用容器部署和相关支撑维护工作等。<br/>\r\n熟悉 nginx 部署，负载均衡，反向代理，Ivs+keepalived 高可用。<br/>\r\n熟悉 kubernetes 搭建，熟悉 Git、Gitlab、Jenkins、Harbor 等工具实现自动化构建、部署。<br/>\r\n熟悉监控工具 zabbix，Prometheus 能够进行邮件报警、微信报警、自定义监控项以及能够使用 elk 日志收集工具，保证线上环境的健康运行。', '计算机', 'heber', '', '<div class=\"experience-item\">\r\n                <div class=\"experience-period\">2022年XX月 - XXXX年XX月</div>\r\n                <div class=\"experience-company\">YYY科技有限公司</div>\r\n                <div class=\"experience-position\">系统运维工程师</div>\r\n                <ul class=\"experience-duties\">\r\n                    <li>管理公司数据中心基础设施，确保99.99%的可用性。</li>\r\n                    <li>实施Zabbix监控系统，覆盖所有关键业务指标。</li>\r\n                    <li>设计并部署ELK日志分析平台，提升故障排查效率。</li>\r\n                    <li>负责系统安全加固，通过ISO27001认证。</li>\r\n                </ul>\r\n</div>', '<div class=\"experience-item\">\r\n                    <div class=\"experience-company\">项目一：<span class=\"experience-position\">智慧医药系统          2024年XX月 - XXXX年XX月</span> </div>\r\n                    <div class=\"experience-company\">项目背景：<span class=\"experience-position\">智慧医药服务平台专注于用户线上医疗诊断，ai辅助诊断以及药品信息查询。</span> </div>\r\n                    <div class=\"experience-company\">项目职责</div>\r\n                    <ul class=\"experience-duties\">\r\n                        <li>负责与开发协商服务器资源申请，中间件的需要以及版本的定位</li>\r\n                        <li>负责服务器采购，并对服务器进行初始化配置(如常用软件安装，防火墙配置，系统环境变量的配置)</li>\r\n                        <li>负责中间件mysqi5.7的部署，数据库创建，数据导入，主从复制，数据库备份脚本编写并添加定时备份任务，以及工作中用户需要的数据通过sql语句进行查询</li>\r\n                        <li>nginx服务部署，配置反向代理去代理后端]ava项目，并通过nginx负载均衡对后端java做集群</li>\r\n                    </ul>\r\n                </div>', '<div class=\"experience-item\">\r\n                <div class=\"experience-period\">2024年XX月 - XXXX年XX月</div>\r\n                <div class=\"experience-company\">YYY科技有限公司</div>\r\n                <div class=\"experience-position\">系统运维工程师</div>\r\n                <ul class=\"experience-duties\">\r\n                    <li>管理公司数据中心基础设施，确保99.99%的可用性。</li>\r\n                    <li>实施Zabbix监控系统，覆盖所有关键业务指标。</li>\r\n                    <li>设计并部署ELK日志分析平台，提升故障排查效率。</li>\r\n                    <li>负责系统安全加固，通过ISO27001认证。</li>\r\n                </ul>\r\n</div>', '2022-07-01 10:23:36');

SET FOREIGN_KEY_CHECKS = 1;
