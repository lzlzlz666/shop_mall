/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : shop_mall

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 03/03/2025 19:12:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `banner_id` int NOT NULL,
  `banner_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`banner_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `banner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/9c3f93e5-f5aa-4c8f-b022-9e8d830a075c.png\r', 1, '2025-01-27 00:45:50', '2025-01-27 00:45:53');
INSERT INTO `banner` VALUES (2, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/ed4327e0-7c9b-4eb7-82e9-d7091b7e8426.jpg', 1, '2025-01-27 00:46:12', '2025-01-27 00:46:17');
INSERT INTO `banner` VALUES (3, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/ba9c0654-521e-4631-88ad-c6d5b23a3b1e.jpg', 1, '2025-01-27 00:46:14', '2025-01-27 00:46:19');
INSERT INTO `banner` VALUES (4, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/c191c6bb-2e5d-400b-91b4-588be256daf7.jpg', 1, '2025-01-27 01:10:05', '2025-01-27 01:10:08');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  `category_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对应的图片',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '电子商品', 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/ac55c935-fa8c-45c6-8b40-7a682c8b988a.png', '2025-01-10 14:01:35', '2025-01-10 14:01:39');
INSERT INTO `category` VALUES (2, '体育', 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/7434486a-7609-4bba-b137-a76cd63dbef7.png', '2025-01-10 14:01:55', '2025-01-10 14:01:59');
INSERT INTO `category` VALUES (3, '食品', 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/d920f19f-c1d2-4be9-9f8a-a5fc1b194465.png', '2025-01-10 14:02:27', '2025-01-10 14:02:31');
INSERT INTO `category` VALUES (4, '学习用品', 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/41cdc45b-a813-49c7-83f9-eaf303e6f73a.png', '2025-01-10 14:02:48', '2025-01-10 14:02:51');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL COMMENT '业务模块id，关联产品id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `like` int NULL DEFAULT NULL COMMENT '点赞数',
  `pid` int NULL DEFAULT NULL COMMENT '父级评论id',
  `target_user_id` int NULL DEFAULT NULL COMMENT '回复用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `target_user_id`(`target_user_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`target_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (10, 20, 'AD钙奶是杭州娃哈哈集团有限公司旗下的饮品，有原味等多种口味。\nAD钙奶饮料，面世于1996年，是娃哈哈最经典的产品之一。它含有牛奶、含有维生素A、维生素D、钙，酸酸甜甜有营养，推出后畅销不衰，是许多人从童年时期2024年都依然爱喝的乳饮料，已成为一个特殊的文化符号。', 1, 2, NULL, NULL, '2025-02-01 20:43:54');
INSERT INTO `comment` VALUES (25, 20, 'AD钙奶诶，小时候喝过！', 2, 0, NULL, NULL, '2025-02-01 22:38:54');
INSERT INTO `comment` VALUES (26, 20, '666，大哥，你要笑死我是吧', 2, 1, 10, 1, '2025-02-01 22:51:17');
INSERT INTO `comment` VALUES (27, 20, '我回复我自己，哈哈哈哈哈😝😝😝', 2, 1, 10, 2, '2025-02-01 22:51:56');
INSERT INTO `comment` VALUES (28, 20, '是的，味道还是杠杠滴🥰😈', 1, 2, 25, 2, '2025-02-01 23:05:36');
INSERT INTO `comment` VALUES (30, 20, '你说的都对，哥', 2, 0, 25, 1, '2025-02-02 11:53:32');
INSERT INTO `comment` VALUES (31, 20, '你说的都对！乐子', 1, 0, 10, 2, '2025-02-02 12:12:26');
INSERT INTO `comment` VALUES (32, 20, '这是什么东东呀', 1, 0, NULL, NULL, '2025-02-02 12:28:51');
INSERT INTO `comment` VALUES (33, 20, '你是linzhou吗', 1, 1, 32, 1, '2025-02-02 12:29:00');
INSERT INTO `comment` VALUES (34, 16, '这瓶可乐我要了', 1, 0, NULL, NULL, '2025-02-02 14:42:24');
INSERT INTO `comment` VALUES (35, 16, '哈哈哈哈，好', 1, 1, 34, 1, '2025-02-02 14:42:33');
INSERT INTO `comment` VALUES (36, 20, '黑马表示认可', 4, 1, 10, 1, '2025-02-02 15:55:26');
INSERT INTO `comment` VALUES (37, 20, '这是娃哈哈AD钙奶呀', 4, 0, 32, 1, '2025-02-02 15:55:42');
INSERT INTO `comment` VALUES (38, 20, '我是黑马程序员，我喜欢喝AD钙奶，爽！', 4, 0, NULL, NULL, '2025-02-02 15:56:11');
INSERT INTO `comment` VALUES (42, 20, '哈哈哈', 1, 0, NULL, NULL, '2025-02-02 20:53:44');
INSERT INTO `comment` VALUES (43, 20, '666😎', 1, 1, 10, 2, '2025-02-02 20:54:09');
INSERT INTO `comment` VALUES (44, 2, '有这么贵？😅', 1, 1, NULL, NULL, '2025-02-11 17:39:24');

-- ----------------------------
-- Table structure for like_comment
-- ----------------------------
DROP TABLE IF EXISTS `like_comment`;
CREATE TABLE `like_comment`  (
  `like_comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '关联用户外键',
  `comment_id` int NULL DEFAULT NULL COMMENT '关联评论外键',
  `like_state` int NULL DEFAULT NULL COMMENT '点赞状态( 0是取消点赞，1是已经点赞)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`like_comment_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  CONSTRAINT `like_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `like_comment_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like_comment
-- ----------------------------
INSERT INTO `like_comment` VALUES (4, 1, 10, 1, '2025-02-02 00:29:54', '2025-02-02 11:43:26');
INSERT INTO `like_comment` VALUES (5, 1, 26, 1, '2025-02-02 01:19:08', '2025-02-02 01:19:08');
INSERT INTO `like_comment` VALUES (6, 1, 27, 1, '2025-02-02 01:19:22', '2025-02-02 11:49:07');
INSERT INTO `like_comment` VALUES (7, 1, 28, 1, '2025-02-02 11:49:12', '2025-02-02 11:49:12');
INSERT INTO `like_comment` VALUES (8, 2, 10, 1, '2025-02-02 11:53:18', '2025-02-02 11:53:18');
INSERT INTO `like_comment` VALUES (9, 2, 28, 1, '2025-02-02 11:53:22', '2025-02-02 11:53:22');
INSERT INTO `like_comment` VALUES (10, 1, 31, 0, '2025-02-02 12:12:30', '2025-02-02 12:12:31');
INSERT INTO `like_comment` VALUES (11, 1, 33, 1, '2025-02-02 12:32:59', '2025-02-02 12:32:59');
INSERT INTO `like_comment` VALUES (12, 1, 35, 1, '2025-02-02 14:42:35', '2025-02-02 14:42:35');
INSERT INTO `like_comment` VALUES (13, 1, 36, 1, '2025-02-02 15:57:46', '2025-02-02 15:57:46');
INSERT INTO `like_comment` VALUES (14, 1, 37, 0, '2025-02-02 15:58:03', '2025-02-02 15:58:06');
INSERT INTO `like_comment` VALUES (15, 1, 25, 0, '2025-02-02 15:58:09', '2025-02-02 15:58:10');
INSERT INTO `like_comment` VALUES (16, 1, 38, 0, '2025-02-02 15:58:13', '2025-02-02 15:58:14');
INSERT INTO `like_comment` VALUES (17, 1, 43, 1, '2025-02-02 20:54:17', '2025-02-02 20:54:17');
INSERT INTO `like_comment` VALUES (18, 1, 44, 1, '2025-02-11 17:39:26', '2025-02-11 17:39:26');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int NULL DEFAULT NULL COMMENT '外键，关联购买的用户',
  `address_id` int NULL DEFAULT NULL COMMENT '外键，关联购买用户的地址',
  `product_id` int NULL DEFAULT NULL COMMENT '外键，关联购买的商品',
  `product_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品规格',
  `product_count` int NULL DEFAULT NULL COMMENT '产品数量',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '产品总价格',
  `is_pay` tinyint NULL DEFAULT NULL COMMENT '是否支付，0表示未支付，1表示已支付',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `address_id`(`address_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `user_address` (`user_address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (21, 1, 1, 15, '规格1', 2, 2.50, 1, '2025-01-23 23:07:27', '2025-01-23 23:07:27');
INSERT INTO `order` VALUES (23, 1, 1, 17, '规格3', 1, 20.00, 1, '2025-01-23 23:10:29', '2025-01-23 23:10:29');
INSERT INTO `order` VALUES (25, 1, 2, 20, '规格2', 3, 4.00, 1, '2025-01-23 23:13:06', '2025-01-23 23:13:06');
INSERT INTO `order` VALUES (26, 2, 9, 10, '规格1', 1, 1199.00, 1, '2025-01-23 23:17:34', '2025-01-23 23:17:34');
INSERT INTO `order` VALUES (27, 2, 9, 15, '规格3', 2, 2.50, 1, '2025-01-23 23:19:28', '2025-01-23 23:19:28');
INSERT INTO `order` VALUES (28, 2, 9, 13, '规格1', 1, 799.00, 1, '2025-01-23 23:26:20', '2025-01-23 23:26:20');
INSERT INTO `order` VALUES (29, 2, 10, 22, '规格3', 1, 120.00, 1, '2025-01-23 23:30:16', '2025-01-23 23:30:16');
INSERT INTO `order` VALUES (30, 2, 10, 15, '规格1', 3, 2.50, 1, '2025-01-23 23:33:59', '2025-01-23 23:33:59');
INSERT INTO `order` VALUES (31, 2, 10, 16, '规格1', 1, 2.50, 1, '2025-01-23 23:36:35', '2025-01-23 23:36:35');
INSERT INTO `order` VALUES (35, 1, 1, 22, '规格1', 1, 120.00, 1, '2025-01-26 17:28:15', '2025-01-26 17:28:15');
INSERT INTO `order` VALUES (40, 1, 1, 13, '规格1', 1, 799.00, 1, '2025-02-12 01:51:57', '2025-02-12 01:51:57');
INSERT INTO `order` VALUES (41, 1, 1, 1, '规格3', 1, 3999.00, 1, '2025-02-12 01:58:51', '2025-02-12 01:58:51');
INSERT INTO `order` VALUES (67, 1, 1, 17, '规格1', 1, 20.00, 1, '2025-02-12 12:36:22', '2025-02-12 12:36:22');
INSERT INTO `order` VALUES (68, 1, 1, 16, '百事可乐1瓶', 1, 2.50, 1, '2025-02-14 23:41:50', '2025-02-14 23:41:50');
INSERT INTO `order` VALUES (69, 1, 1, 15, '黑暗雪碧1瓶', 2, 2.50, 1, '2025-02-14 23:44:09', '2025-02-14 23:44:09');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NULL DEFAULT NULL COMMENT '商品分类',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述信息',
  `product_original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品原价',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品现价',
  `product_stock` int NULL DEFAULT NULL COMMENT '商品库存',
  `product_sales` int NULL DEFAULT NULL COMMENT '商品销量',
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime NULL DEFAULT NULL COMMENT '商品创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '商品更新时间',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, '华为MateBook', '华为MateBook D 14 SE 2024笔记本电脑 13代酷睿/14英寸护眼全面屏/轻薄办公本 i5 16G 512G 皓月银', 4999.00, 3999.00, 120, 30, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/ee44167d-0a43-4bd2-b722-5af3cd75a7de.png', '2025-01-10 17:17:10', '2025-01-10 17:17:13');
INSERT INTO `product` VALUES (2, 1, '华为MateBook X Pro', '华为MateBook X Pro 13.9英寸全面屏笔记本电脑，i7 16GB 512GB SSD，超薄设计，超长续航', 9999.00, 8499.00, 50, 10, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/cb529d34-5a12-47a3-a35d-eac6f6452f73.png', '2025-01-10 17:18:10', '2025-01-10 17:18:13');
INSERT INTO `product` VALUES (3, 1, '苹果MacBook Pro 14', 'MacBook Pro 14英寸，Apple M1芯片，16GB内存，512GB SSD，支持4K显示', 14999.00, 13999.00, 30, 8, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/925ce9ba-3b35-4371-9b6a-b53b34eace7c.png', '2025-01-10 17:18:20', '2025-01-10 17:18:23');
INSERT INTO `product` VALUES (4, 1, '戴尔XPS 13', '戴尔XPS 13英寸超轻薄笔记本，i5 8GB内存，256GB SSD，适合商务办公', 7999.00, 6999.00, 80, 25, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/4ba4ecfa-b8db-464b-9255-c51d2c4d1c42.png', '2025-01-10 17:18:30', '2025-01-10 17:18:33');
INSERT INTO `product` VALUES (5, 1, '小米笔记本Pro 15', '小米笔记本Pro 15.6英寸，i7 16GB内存，512GB SSD，超高性价比笔记本', 8999.00, 7999.00, 60, 15, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/c53eb3b7-cbc5-4c2d-b0c5-1172640bc80c.png', '2025-01-10 17:18:40', '2025-01-10 17:18:43');
INSERT INTO `product` VALUES (9, 2, '耐克Air Zoom Pegasus 39', '耐克Air Zoom Pegasus 39跑步鞋，透气舒适，适合长时间运动使用', 799.00, 699.00, 150, 50, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/d050d2ba-84b3-46e5-bc86-9327876b9f54.png', '2025-01-10 17:19:20', '2025-01-10 17:19:23');
INSERT INTO `product` VALUES (10, 2, '阿迪达斯Ultraboost 23', '阿迪达斯Ultraboost 23跑步鞋，搭载BOOST科技，极致舒适感', 1299.00, 1199.00, 120, 35, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/52b3e1cf-5663-49ef-9c40-978edecd2928.png', '2025-01-10 17:19:30', '2025-01-10 17:19:33');
INSERT INTO `product` VALUES (11, 2, '安踏跑步鞋', '安踏跑步鞋，适合各种运动，舒适耐用，时尚运动款', 599.00, 499.00, 200, 100, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/6227c0b2-1318-45c4-a829-b0062346d2b9.png', '2025-01-10 17:19:40', '2025-01-10 17:19:43');
INSERT INTO `product` VALUES (12, 2, '李宁羽毛球鞋', '李宁羽毛球鞋，采用防滑设计，增强脚底摩擦力，适合羽毛球运动', 699.00, 599.00, 180, 70, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/5aec7901-e399-4384-9033-84b67d024048.png', '2025-01-10 17:19:50', '2025-01-10 17:19:53');
INSERT INTO `product` VALUES (13, 2, '彪马运动鞋', '彪马运动鞋，舒适合脚，适合日常穿着和运动', 899.00, 799.00, 140, 40, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/e03f19c6-50c1-406b-82e8-2e5e7dbe9b77.png', '2025-01-10 17:20:00', '2025-01-10 17:20:03');
INSERT INTO `product` VALUES (15, 3, '雪碧饮料', '雪碧500ml清爽饮料，解渴必备', 3.00, 2.50, 500, 200, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/3947af93-d2fa-4305-8e6e-0a6fd831f5b7.png', '2025-01-10 17:20:20', '2025-01-10 17:20:23');
INSERT INTO `product` VALUES (16, 3, '可口可乐饮料', '可口可乐330ml瓶装饮料，畅爽口感', 3.00, 2.50, 550, 250, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/93650672-5399-4b91-8407-86abad4e91ee.png', '2025-01-10 17:20:30', '2025-01-10 17:20:33');
INSERT INTO `product` VALUES (17, 3, '吉野家便当', '吉野家经典便当，米饭+牛肉，简餐美味', 25.00, 20.00, 300, 100, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/505f5fdc-eed2-4ef3-a8cf-9ca52bdf9f9e.png', '2025-01-10 17:20:40', '2025-01-10 17:20:43');
INSERT INTO `product` VALUES (19, 3, '辛拉面', '辛拉面热销韩国方便面，麻辣口味，快餐首选', 10.00, 8.00, 400, 150, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/ceafad08-ec10-4028-8b59-479b10d1cbd2.png', '2025-01-10 17:21:00', '2025-01-10 17:21:03');
INSERT INTO `product` VALUES (20, 3, '娃哈哈AD钙奶', '娃哈哈AD钙奶250ml，补钙必备，适合儿童和青少年', 5.00, 4.00, 600, 300, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/3f6e05cf-1d2b-46a4-8eaa-dda10407b688.png', '2025-01-10 17:21:10', '2025-01-10 17:21:13');
INSERT INTO `product` VALUES (21, 4, '精致文具套装', '精致文具套装，包括钢笔、圆珠笔、尺子等，适合学生使用', 99.00, 79.00, 300, 80, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/3032a133-f8cc-4dd7-aa64-5f2c258fb25f.png', '2025-01-10 17:21:20', '2025-01-10 17:21:23');
INSERT INTO `product` VALUES (22, 4, '书法工具套装', '书法工具套装，包含毛笔、墨汁、宣纸等', 150.00, 120.00, 150, 50, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/f074c2e0-bb9b-4c87-b7d8-686b28393d31.png', '2025-01-10 17:21:30', '2025-01-10 17:21:33');

-- ----------------------------
-- Table structure for product_detail_img
-- ----------------------------
DROP TABLE IF EXISTS `product_detail_img`;
CREATE TABLE `product_detail_img`  (
  `product_detail_img_id` int NOT NULL AUTO_INCREMENT,
  `product_detail_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`product_detail_img_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_detail_img_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_detail_img
-- ----------------------------
INSERT INTO `product_detail_img` VALUES (1, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/5eb2020c-3c4e-4c85-a151-387eaf129f2f.webp', 16, '2025-02-11 17:04:21', '2025-02-11 17:04:23');
INSERT INTO `product_detail_img` VALUES (2, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/73248f52-54ba-441d-992d-b7d31a1915bd.jpg', 16, '2025-02-11 17:05:04', '2025-02-11 17:05:06');
INSERT INTO `product_detail_img` VALUES (3, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/766758e3-2f7f-461a-89d0-140b7a16ffa3.jpeg', 16, '2025-02-11 17:05:21', '2025-02-11 17:05:23');
INSERT INTO `product_detail_img` VALUES (4, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/c5beb4fa-0e07-4ed7-a4c4-a8335c5810ed.png', 20, '2025-02-11 17:35:26', '2025-02-11 17:35:28');
INSERT INTO `product_detail_img` VALUES (5, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/5b758cd7-d21e-4ef6-a365-466d848847cd.png', 20, '2025-02-11 17:36:10', '2025-02-11 17:36:12');

-- ----------------------------
-- Table structure for product_format
-- ----------------------------
DROP TABLE IF EXISTS `product_format`;
CREATE TABLE `product_format`  (
  `product_format_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `product_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`product_format_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_format_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_format
-- ----------------------------
INSERT INTO `product_format` VALUES (1, 16, '百事可乐1瓶', '2025-02-14 23:12:01', '2025-02-14 23:12:03');
INSERT INTO `product_format` VALUES (2, 16, '可口可乐1瓶', '2025-02-14 23:12:18', '2025-02-14 23:12:20');
INSERT INTO `product_format` VALUES (3, 15, '无糖雪碧1瓶', '2025-02-14 23:43:06', '2025-02-14 23:43:08');
INSERT INTO `product_format` VALUES (4, 15, '柠檬雪碧1瓶', '2025-02-14 23:43:23', '2025-02-14 23:43:25');
INSERT INTO `product_format` VALUES (5, 15, '黑暗雪碧1瓶', '2025-02-14 23:43:40', '2025-02-14 23:43:43');
INSERT INTO `product_format` VALUES (6, 20, 'AD钙奶100ml*2', '2025-02-15 21:22:09', '2025-02-15 21:22:11');
INSERT INTO `product_format` VALUES (7, 20, 'AD钙奶200ml*1', '2025-02-15 21:22:30', '2025-02-15 21:22:32');

-- ----------------------------
-- Table structure for product_imgs
-- ----------------------------
DROP TABLE IF EXISTS `product_imgs`;
CREATE TABLE `product_imgs`  (
  `product_img_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `defalut_img` tinyint NULL DEFAULT NULL COMMENT '1表示第一张图片，0表示不是第一张',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`product_img_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_imgs_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_imgs
-- ----------------------------
INSERT INTO `product_imgs` VALUES (1, 20, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/3f6e05cf-1d2b-46a4-8eaa-dda10407b688.png', 1, '2025-02-07 17:39:34', '2025-02-07 17:39:37');
INSERT INTO `product_imgs` VALUES (2, 20, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/22c88ebf-6c16-4f5e-bd20-3cffac008b5c.avif', 0, '2025-02-07 17:41:21', '2025-02-07 17:41:26');
INSERT INTO `product_imgs` VALUES (3, 16, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/a8ac1a62-8365-451a-b7d0-5b47e7986d4d.png', 1, '2025-02-07 18:25:04', '2025-02-07 18:25:06');
INSERT INTO `product_imgs` VALUES (4, 16, 'https://big-event-lz.oss-cn-hangzhou.aliyuncs.com/93650672-5399-4b91-8407-86abad4e91ee.png', 0, '2025-02-07 18:25:30', '2025-02-07 18:25:32');
INSERT INTO `product_imgs` VALUES (5, 16, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/2e2d9d63-773c-4454-81d6-f13338669737.png', 0, '2025-02-07 18:32:46', '2025-02-07 18:32:48');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码(加密)',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `user_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex` tinyint NULL DEFAULT NULL COMMENT '用户性别，0女性，1男性',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'linzhou', 'e10adc3949ba59abbe56e057f20f883e', '我是男孩❤️🌈', 'lz18958750073@gmail.com', '2004-02-22', '17816155311', 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/063333ef-033b-4758-9ed7-987b593ff8c4.png', 1, '美国洛杉矶 Los Angeles！！', '2025-01-25 00:07:24', '2025-01-25 00:07:38');
INSERT INTO `user` VALUES (2, '人工智能', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/62be4c7b-1a6d-4023-884a-7b6bbaa37ce7.png', NULL, NULL, '2025-01-26 00:04:27', '2025-01-12 22:11:35');
INSERT INTO `user` VALUES (3, 'lzlz', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-01-12 22:30:37', '2025-01-12 22:30:37');
INSERT INTO `user` VALUES (4, '黑马程序员', 'e10adc3949ba59abbe56e057f20f883e', 'It黑马', NULL, '2025-02-23', NULL, 'https://shop-mall-lz.oss-cn-hangzhou.aliyuncs.com/1b87cca2-f3f7-4e17-84be-303d6a9fa95c.png', 1, NULL, '2025-02-02 15:54:56', '2025-02-02 15:55:09');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `user_address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户的外键',
  `receiver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接受者姓名',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接受者联系方式',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接受者地址',
  `default_address` tinyint NULL DEFAULT NULL COMMENT '0为默认地址，1为非默认地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_address_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (1, 1, 'lz', '18958750073', '新田园三组团9幢304室', 0, '2025-01-19 17:26:56', '2025-01-19 17:26:59');
INSERT INTO `user_address` VALUES (2, 1, '王瑞轩', '18334489688', '文成县珊溪镇滨江西路19号', 1, '2025-01-19 17:27:30', '2025-01-19 17:27:33');
INSERT INTO `user_address` VALUES (3, 1, '姚浩然', '13968851682', '瞿溪镇会龙小区5幢5号', 1, '2025-01-19 19:30:29', '2025-01-19 19:30:32');
INSERT INTO `user_address` VALUES (9, 2, '孤独的狼', '11011011011', '浙江奶龙大学', 1, '2025-01-23 23:17:29', '2025-01-23 23:17:29');
INSERT INTO `user_address` VALUES (10, 2, '我是奶龙', '1200000003', '小猪佩奇的家', 0, '2025-01-23 23:30:13', '2025-01-23 23:30:13');
INSERT INTO `user_address` VALUES (12, 1, '王肥波', '12345678901', '嘉鸿花园某卫生间', 1, '2025-01-26 20:22:49', '2025-01-26 20:22:49');

SET FOREIGN_KEY_CHECKS = 1;
