/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : itdatabase

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 21/01/2021 12:25:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message_info
-- ----------------------------
DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `message_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `message_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源url',
  `message_typeId` int(11) NOT NULL COMMENT '资源类型Id',
  `message_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源提取码',
  `message_author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传者',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '自动插入，创建时间',
  `updated` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '自动插入，修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `message_url`(`message_url`) USING BTREE,
  INDEX `message_name`(`message_name`) USING BTREE,
  INDEX `message_typeId`(`message_typeId`) USING BTREE,
  INDEX `message_author`(`message_author`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_info
-- ----------------------------
INSERT INTO `message_info` VALUES (16, 'Eclipse', 'adf', 1, '3kp2', 'jason', '2021-01-07 18:14:29', '2021-01-07 18:14:29');
INSERT INTO `message_info` VALUES (17, 'IDEA', 'asdfg', 7, '7869', 'qh', '2021-01-07 18:14:46', '2021-01-07 18:14:46');
INSERT INTO `message_info` VALUES (18, '爬虫', 'dafdfa', 9, 'asdf', 'guo', '2021-01-07 18:15:07', '2021-01-15 22:28:28');

-- ----------------------------
-- Table structure for message_type
-- ----------------------------
DROP TABLE IF EXISTS `message_type`;
CREATE TABLE `message_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `type_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源类型名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_type
-- ----------------------------
INSERT INTO `message_type` VALUES (3, 'c/c++');
INSERT INTO `message_type` VALUES (4, 'GO');
INSERT INTO `message_type` VALUES (7, 'IDE');
INSERT INTO `message_type` VALUES (1, 'Java');
INSERT INTO `message_type` VALUES (5, 'PHP');
INSERT INTO `message_type` VALUES (2, 'python');
INSERT INTO `message_type` VALUES (9, '分布式');
INSERT INTO `message_type` VALUES (8, '数据库');
INSERT INTO `message_type` VALUES (6, '框架');
INSERT INTO `message_type` VALUES (11, '算法');
INSERT INTO `message_type` VALUES (10, '自定义');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增长）',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `openId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录Id',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '自动插入，创建时间',
  `updated` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '自动插入，修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (4, 'jason', '6E97AE01F3B5ECF91E48726C5C497341', '158', 'qh404836248@163.com', 'e5fc32ac-d1b2-4fd7-a259-6b1e956d9e3a', '2021-01-06 17:52:41', '2021-01-06 17:52:41');
INSERT INTO `users` VALUES (5, '董自宁', '4713D9AC544237175383480DCA9E1177', '18162350281', '1486536739@qq.com', 'b403017a-61c8-4264-8603-0d0f698100b3', '2021-01-06 17:58:36', '2021-01-06 17:58:36');
INSERT INTO `users` VALUES (6, 'Max', '994AD2F11E6A6F7DC9177060BA3A507F', '18010668559', 'Insomniaguo@163.com', 'b29bac17-63c8-463b-a270-fcf9e898f7be', '2021-01-07 09:27:52', '2021-01-07 09:27:52');

SET FOREIGN_KEY_CHECKS = 1;
