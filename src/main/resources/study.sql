/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 16/04/2022 23:51:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clock
-- ----------------------------
DROP TABLE IF EXISTS `clock`;
CREATE TABLE `clock`
(
    `clockId` bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '打卡表id',
    `bsosId`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人id',
    `link`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '分享链接',
    `isShow`  tinyint(1)                                              NOT NULL DEFAULT 0 COMMENT '打卡表是否可见',
    PRIMARY KEY (`clockId`) USING BTREE,
    INDEX `clock_user_bsosid_fk` (`bsosId`) USING BTREE,
    CONSTRAINT `clock_user_bsosid_fk` FOREIGN KEY (`bsosId`) REFERENCES `user` (`bsosId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '打卡表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for clock_record
-- ----------------------------
DROP TABLE IF EXISTS `clock_record`;
CREATE TABLE `clock_record`
(
    `clockRecordId` bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '打卡记录id',
    `clockId`       bigint(20)                                              NOT NULL COMMENT '该打卡记录所属打卡表id',
    `bsosId`        varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
    `descriptions`  text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '描述',
    `createTime`    datetime(0)                                             NOT NULL COMMENT '创建时间',
    `clockTime`     datetime(0)                                             NOT NULL COMMENT '打卡时间',
    `endTime`       datetime(0)                                             NOT NULL COMMENT '结束时间',
    `cover`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡封面',
    `deleteTime`    datetime(0)                                             NULL     DEFAULT NULL COMMENT '删除时间',
    `success`       tinyint(1)                                              NOT NULL DEFAULT 0 COMMENT '本次打卡是否成功',
    `isDelete`      tinyint(1)                                              NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`clockRecordId`) USING BTREE,
    INDEX `clock_record_clock_clockId_fk` (`clockId`) USING BTREE,
    INDEX `clock_record_user_bsosid_fk` (`bsosId`) USING BTREE,
    CONSTRAINT `clock_record_clock_clockId_fk` FOREIGN KEY (`clockId`) REFERENCES `clock` (`clockid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `clock_record_user_bsosid_fk` FOREIGN KEY (`bsosId`) REFERENCES `user` (`bsosId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '打卡记录表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for invite_code
-- ----------------------------
DROP TABLE IF EXISTS `invite_code`;
CREATE TABLE `invite_code`
(
    `codeId`     int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '邀请码Id',
    `inviteCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邀请码',
    `bsosId`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者id',
    PRIMARY KEY (`codeId`) USING BTREE,
    INDEX `invite_code_user_bsosid_fk` (`bsosId`) USING BTREE,
    CONSTRAINT `invite_code_user_bsosid_fk` FOREIGN KEY (`bsosId`) REFERENCES `user` (`bsosId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '邀请码记录表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for level
-- ----------------------------
DROP TABLE IF EXISTS `level`;
CREATE TABLE `level`
(
    `levelId`     int(11)    NOT NULL AUTO_INCREMENT COMMENT '等级id',
    `growth`      bigint(20) NOT NULL DEFAULT 0 COMMENT '该等级最底所需的成长值',
    `growthLevel` int(11)    NOT NULL DEFAULT 0 COMMENT '等级数',
    PRIMARY KEY (`levelId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`
(
    `noteId`      int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '笔记id',
    `bsosId`      varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人id',
    `noteContent` text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '笔记内容',
    `files`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件',
    `message`     text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '打卡提醒通知',
    `remindTime`  datetime(0)                                             NOT NULL COMMENT '提醒时间',
    PRIMARY KEY (`noteId`) USING BTREE,
    INDEX `note_user_bsosid_fk` (`bsosId`) USING BTREE,
    CONSTRAINT `note_user_bsosid_fk` FOREIGN KEY (`bsosId`) REFERENCES `user` (`bsosId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户笔记'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for real_message
-- ----------------------------
DROP TABLE IF EXISTS `real_message`;
CREATE TABLE `real_message`
(
    `realMessageId` int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '实名信息id',
    `bsosId`        varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
    `realName`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
    `idCode`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
    `isDelete`      tinyint(1)                                              NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (`realMessageId`) USING BTREE,
    INDEX `real_name_user_bsosid_fk` (`bsosId`) USING BTREE,
    CONSTRAINT `real_name_user_bsosid_fk` FOREIGN KEY (`bsosId`) REFERENCES `user` (`bsosId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '实名信息记录表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `bsosId`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台标识/用户id',
    `userId`          int(11)                                                 NOT NULL COMMENT '用户统计',
    `nickName`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
    `userAccount`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
    `password`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
    `email`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
    `recentLoginTime` datetime(0)                                             NOT NULL COMMENT '最近登录时间',
    `registerTime`    datetime(0)                                             NOT NULL COMMENT '用户注册时间',
    `avatar`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '头像',
    `descriptions`    text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '个人介绍',
    `phone`           varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL     DEFAULT NULL COMMENT '手机号',
    `age`             int(11)                                                 NULL     DEFAULT NULL COMMENT '年龄',
    `problem`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '安全问题',
    `ans`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '安全问题答案',
    `isRealName`      tinyint(1)                                              NOT NULL DEFAULT 0 COMMENT '是否实名注册',
    `score`           bigint(20)                                              NOT NULL DEFAULT 0 COMMENT '打卡得分',
    `isEnable`        tinyint(1)                                              NOT NULL DEFAULT 1 COMMENT '用户是否可用',
    PRIMARY KEY (`bsosId`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户基本信息表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
