SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for d_doctor 医生登陆表
-- ----------------------------
DROP TABLE IF EXISTS `d_doctor`;
CREATE TABLE `d_doctor`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
                            `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                            `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
                            `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
                            `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
                            `userFace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                            `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for d_doctor_role 医生角色表
-- ----------------------------
DROP TABLE IF EXISTS `d_doctor_role`;
CREATE TABLE `d_doctor_role`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `doctorId` int(11) NULL DEFAULT NULL COMMENT '用户id',
                                 `rid` int(11) NULL DEFAULT NULL COMMENT '权限id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `rid`(`rid`) USING BTREE,
                                 INDEX `adminId`(`adminId`) USING BTREE,
                                 CONSTRAINT `t_admin_role_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `t_admin` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                 CONSTRAINT `t_admin_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;