/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.21-log : Database - bluetooth_price_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bluetooth_price_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bluetooth_price_test`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` varchar(50) NOT NULL,
  `goods_name` varchar(150) NOT NULL COMMENT '商品名',
  `goods_specs` varchar(50) NOT NULL COMMENT '商品规格',
  `goods_unit` varchar(25) DEFAULT NULL COMMENT '商品单位',
  `goods_place` varchar(25) DEFAULT NULL COMMENT '商品生产地',
  `goods_code` varchar(25) DEFAULT NULL COMMENT '商品编码',
  `goods_price` varchar(25) DEFAULT NULL COMMENT '商品价格',
  `goods_barcode` varchar(25) DEFAULT NULL COMMENT '商品条形码编号',
  `goods_tell` varchar(25) DEFAULT NULL COMMENT '生产厂家电话',
  PRIMARY KEY (`goods_id`,`goods_name`,`goods_specs`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_name`,`goods_specs`,`goods_unit`,`goods_place`,`goods_code`,`goods_price`,`goods_barcode`,`goods_tell`) values ('6000000115518826','ThinkPad无线网卡','150','g','重庆','068078','199.50','12304567891','4000230033'),('6000001873558921','刺力王刺梨果汁饮料','245','ml','六盘水','00035s','6.00','6971401660015','4000046666'),('6000001873558922','美汁源果粒果汁','375','ml','重庆','068078','3.50','1234567980123','4000230033'),('6000001873558923','云烟紫云','20','支','玉溪','012345','10.00','09010280446886','4000050606'),('6000001873558929','东鹏维生素功能饮料','250','ml','重庆','068078','6.00','6934502300396','4000230033');

/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `img_id` int(25) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(250) DEFAULT NULL COMMENT '图片名',
  `img_path` varchar(250) DEFAULT NULL COMMENT '图片路径',
  `goods_id` varchar(50) DEFAULT NULL COMMENT '关联的商品信息',
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `images` */

insert  into `images`(`img_id`,`img_name`,`img_path`,`goods_id`) values (1,'0e073cd6fc8c4b92b24a53023515b459.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.7500472652524563049.8080\\0e073cd6fc8c4b92b24a53023515b459.bmp','6000001873558921'),(2,'b4bc146b206643a9bdf127f30ae90b0b.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.7500472652524563049.8080\\b4bc146b206643a9bdf127f30ae90b0b.bmp','6000001873558922'),(3,'1bde4b93f2ae412b820b45097ee2f28a.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.7500472652524563049.8080\\1bde4b93f2ae412b820b45097ee2f28a.bmp','6000001873558923'),(4,'ab0dde8763aa408583e3099ffb11e5c2.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.7500472652524563049.8080\\ab0dde8763aa408583e3099ffb11e5c2.bmp','6000001873558929'),(5,'668c7d2c52ce4a7795241c6fc1f5f183.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.6904398416060508338.8080\\668c7d2c52ce4a7795241c6fc1f5f183.bmp','6000001873558922'),(6,'02c64912b803456883972d922756a36a.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.6904398416060508338.8080\\02c64912b803456883972d922756a36a.bmp','6000001873558921'),(7,'cc1f9425506945f584bca4895112372b.bmp','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.523782462902647005.8080\\cc1f9425506945f584bca4895112372b.bmp','6000000115518826');

/*Table structure for table `images_info` */

DROP TABLE IF EXISTS `images_info`;

CREATE TABLE `images_info` (
  `img_id` int(15) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(150) NOT NULL,
  `img_title` varchar(150) NOT NULL,
  `img_unit` varchar(150) NOT NULL,
  `img_specs` varchar(150) NOT NULL,
  `img_place` varchar(150) NOT NULL,
  `img_code` varchar(150) NOT NULL,
  `img_price` varchar(150) NOT NULL,
  `img_barcode` varchar(150) NOT NULL,
  `img_tell` varchar(150) NOT NULL,
  `img_path` varchar(150) NOT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `images_info` */

insert  into `images_info`(`img_id`,`img_name`,`img_title`,`img_unit`,`img_specs`,`img_place`,`img_code`,`img_price`,`img_barcode`,`img_tell`,`img_path`) values (1,'dc35adc4e6da44b8a1dff012fe50631c.bmp','美汁源果粒果汁','GE','420ML','武汉','068078','333.40','1234567890123','4000230033','F:/LymTools/bluetoothprice/src/main/resources/static/images/dc35adc4e6da44b8a1dff012fe50631c.bmp'),(2,'f5220182766d4db7a0c5f2365e803325.bmp','美汁源果粒果汁','GE','420ML','武汉','068078','33.40','1234567890123','4000230033','F:/LymTools/bluetoothprice/src/main/resources/static/images/f5220182766d4db7a0c5f2365e803325.bmp'),(3,'c7a6c690445c441995aca7189bc7ce72.bmp','美汁源果粒橙','GE','420ML','武汉','068078','333.40','1234567890123','4000230033','F:/LymTools/bluetoothprice/src/main/resources/static/images/c7a6c690445c441995aca7189bc7ce72.bmp');

/*Table structure for table `stores` */

DROP TABLE IF EXISTS `stores`;

CREATE TABLE `stores` (
  `store_id` int(25) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(25) DEFAULT NULL,
  `store_province` varchar(25) DEFAULT NULL COMMENT '所属省份',
  `store_urban` varchar(25) DEFAULT NULL COMMENT '所属城市',
  `store_address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `stores` */

insert  into `stores`(`store_id`,`store_name`,`store_province`,`store_urban`,`store_address`) values (1,'易捷便利店花果园店','贵州省','贵阳市','云岩区花果园西路1号'),(2,'易捷便利店西南大学店','重庆市','重庆市','北碚区天生路18号'),(3,'易捷便利店腾冲店','云南省','腾冲市','鬼知道什么路'),(4,'你猜','不晓得','别问了','日你爹'),(5,'垃圾开发','垃圾省','垃圾市','垃圾路');

/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `tag_id` int(25) NOT NULL AUTO_INCREMENT,
  `wifi_id` int(25) DEFAULT NULL,
  `goods_id` varchar(50) DEFAULT NULL,
  `mac_address` varchar(150) NOT NULL,
  `picpx` varchar(25) DEFAULT NULL,
  `type` int(25) DEFAULT NULL,
  PRIMARY KEY (`tag_id`,`mac_address`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tags` */

insert  into `tags`(`tag_id`,`wifi_id`,`goods_id`,`mac_address`,`picpx`,`type`) values (2,3,'6000000115518826','6A9208A6C13B','250*122\r\n',1),(3,4,'6000001873558922','CEF6EDA1AAAA','0*0',1),(5,5,'6000001873558921','361BAE63AAAA','212*104',1),(6,5,'6000001873558923','ACFBBF63AAAA','212*104',1),(7,5,'6000001873558929','ADADBF63AAAA','212*104',1),(8,5,NULL,'ECECBF73AAAA','212*104',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(25) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) DEFAULT NULL,
  `user_password` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`user_id`,`user_name`,`user_password`) values (1,'admin','123456');

/*Table structure for table `wifis` */

DROP TABLE IF EXISTS `wifis`;

CREATE TABLE `wifis` (
  `wifi_id` int(25) NOT NULL AUTO_INCREMENT,
  `wifi_ip` varchar(25) DEFAULT NULL COMMENT '当前wifi的IP地址',
  `store_id` int(25) DEFAULT NULL COMMENT '当前WiFi所属门店id',
  `type` int(25) DEFAULT NULL COMMENT '表示当前WiFi的使用状态。0表示未使用，1表示使用中',
  PRIMARY KEY (`wifi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `wifis` */

insert  into `wifis`(`wifi_id`,`wifi_ip`,`store_id`,`type`) values (1,'192.168.0.122',1,1),(2,'192.168.0.123',2,1),(3,'192.168.0.109',3,1),(4,'192.168.0.152',1,1),(5,'192.168.0.150',2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
