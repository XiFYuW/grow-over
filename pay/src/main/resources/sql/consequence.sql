/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 08/11/2020 09:32:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consequence
-- ----------------------------
DROP TABLE IF EXISTS `consequence`;
CREATE TABLE `consequence`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商户订单号',
  `is_consequence` tinyint(1) NOT NULL COMMENT '1.是 0.否',
  `pay_way` tinyint(1) NOT NULL DEFAULT 0 COMMENT '支付方式1：支付宝 2：微信',
  `is_perform` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0.没有执行 1.已执行',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `ali_order_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0.WAIT_BUYER_PAY.交易创建，等待买家付款\r\n1.TRADE_CLOSED.未付款交易超时关闭，或支付完成后全额退款\r\n2.TRADE_SUCCESS.交易支付成功\r\n3.TRADE_FINISHED.交易结束，不可退款',
  `wx_order_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '2.SUCCESS.支付成功\r\n3.REFUND.转入退款\r\n0.NOTPAY.未支付\r\n4.REVOKED.已撤销（付款码支付）\r\n5.USERPAYING.用户支付中（付款码支付）\r\n1.PAYERROR.支付失败(其他原因，如银行返回失败\r\n6.CLOSED.已关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
