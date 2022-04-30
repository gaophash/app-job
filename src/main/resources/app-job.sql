/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : app-job

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 29/04/2022 15:26:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位信息',
  `pay_min` int(10) NULL DEFAULT NULL COMMENT '最小薪资',
  `pay_max` int(10) NULL DEFAULT NULL COMMENT '最大薪资',
  `pay_month` int(10) NULL DEFAULT NULL COMMENT '一年机薪',
  `welfare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '福利待遇',
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历信息',
  `working_years` int(11) NULL DEFAULT NULL COMMENT '工作年限',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `work_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作地点',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称(冗余字段)',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司id',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '城市id',
  `area_id` int(11) NULL DEFAULT NULL COMMENT '区县id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job
-- ----------------------------

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES (1, '删除用户', '/usr/del', 1, '2021-11-26 17:08:11', '2021-11-26 17:07:52');
INSERT INTO `t_auth` VALUES (2, '新增用户', '/usr/add', 1, '2021-11-26 17:08:13', '2021-11-26 17:08:09');
INSERT INTO `t_auth` VALUES (3, '添加产品', '/hello', 1, '2021-11-26 17:08:42', '2021-11-26 17:08:29');
INSERT INTO `t_auth` VALUES (4, '下架产品', '/product/del', NULL, NULL, '2021-11-26 17:12:17');
INSERT INTO `t_auth` VALUES (5, '注册', '/user/register', NULL, NULL, '2021-11-26 17:13:32');
INSERT INTO `t_auth` VALUES (6, '注销', '/user/logOff', NULL, NULL, '2021-11-26 17:13:50');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(11) NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_admin', 1, '2021-11-26 17:08:52', '2021-11-26 17:08:51');
INSERT INTO `t_role` VALUES (2, 'ROLE_dba', 1, '2021-11-26 17:09:10', '2021-11-26 17:09:05');
INSERT INTO `t_role` VALUES (3, 'ROLE_vip', 1, '2021-11-26 17:09:32', '2021-11-26 17:09:25');
INSERT INTO `t_role` VALUES (4, 'ROLE_user', 1, '2021-11-26 17:09:45', '2021-11-26 17:09:42');

-- ----------------------------
-- Table structure for t_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_role_auth`;
CREATE TABLE `t_role_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `auth_id` bigint(20) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_role_auth
-- ----------------------------
INSERT INTO `t_role_auth` VALUES (1, 1, 3, 1, '2021-11-26 17:11:31', '2021-11-26 17:11:29');
INSERT INTO `t_role_auth` VALUES (2, 1, 4, 1, '2021-11-26 17:11:31', '2021-11-26 17:11:29');
INSERT INTO `t_role_auth` VALUES (3, 4, 5, 1, '2021-11-26 17:14:45', '2021-11-26 17:14:35');
INSERT INTO `t_role_auth` VALUES (4, 4, 6, 1, '2021-11-26 17:14:47', '2021-11-26 17:14:41');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(11) NOT NULL,
  `user_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一的userId',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '120', 'zhangsan', '123456', '张三', 1, '2021-11-26 17:07:03', '2021-11-26 17:06:53');
INSERT INTO `t_user` VALUES (2, '110', 'lisi', '123456', '李四', 1, '2021-11-26 17:07:36', '2021-11-26 17:07:12');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户唯一userId',
  `role_id` bigint(20) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, '120', 4, 1, '2021-11-26 17:10:10', '2021-11-26 17:10:11');
INSERT INTO `t_user_role` VALUES (2, '110', 2, 1, '2021-11-26 17:11:16', '2021-11-26 17:11:13');

SET FOREIGN_KEY_CHECKS = 1;
