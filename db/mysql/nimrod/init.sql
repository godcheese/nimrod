/*
 Navicat Premium Data Transfer

 Source Server         : 10.79.187.156
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : 10.79.187.156:3306
 Source Schema         : nimrod

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 11/01/2019 17:20:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT 'API 名称',
  `url` text COMMENT '请求地址（url）',
  `authority` varchar(191) NOT NULL COMMENT '权限（authority）',
  `api_category_id` bigint(20) unsigned NOT NULL COMMENT 'API 分类 id',
  `sort` bigint(20) unsigned DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_authority` (`authority`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COMMENT='API 表';

-- ----------------------------
-- Records of api
-- ----------------------------
BEGIN;
INSERT INTO `api` VALUES (1, '分页获取所有父级 API 分类', '/api/system/api_category/page_all_parent', '/API/SYSTEM/API_CATEGORY/PAGE_ALL_PARENT', 4, 0, '', '2018-06-20 12:56:39', '2018-06-20 12:56:39');
INSERT INTO `api` VALUES (2, '指定父级 API 分类 id ，获取所有 API 分类分类', '/api/system/api_category/list_all_by_parent_id', '/API/SYSTEM/API_CATEGORY/LIST_ALL_BY_PARENT_ID', 4, 0, '', '2018-11-19 09:00:05', '2018-06-20 12:57:36');
INSERT INTO `api` VALUES (3, '新增 API 分类', '/api/system/api_category/add_one', '/API/SYSTEM/API_CATEGORY/ADD_ONE', 4, 0, '', '2018-06-20 12:58:15', '2018-06-20 12:58:15');
INSERT INTO `api` VALUES (4, '保存 API 分类', '/api/system/api_category/save_one', '/API/SYSTEM/API_CATEGORY/SAVE_ONE', 4, 0, '', '2018-06-20 12:58:43', '2018-06-20 12:58:43');
INSERT INTO `api` VALUES (5, '指定 API 分类 id ，批量删除 API 分类', '/api/system/api_category/delete_all', '/API/SYSTEM/API_CATEGORY/DELETE_ALL', 4, 0, '', '2018-06-20 12:59:32', '2018-06-20 12:59:18');
INSERT INTO `api` VALUES (6, '指定 API 分类 id ，获取 API 分类信息', '/api/system/api_category/one', '/API/SYSTEM/API_CATEGORY/ONE', 4, 0, '', '2018-06-20 13:00:35', '2018-06-20 13:00:35');
INSERT INTO `api` VALUES (7, '指定 API 分类 id ，分页获取所有 API', '/api/system/api/page_all_by_api_category_id', '/API/SYSTEM/API/PAGE_ALL_BY_API_CATEGORY_ID', 5, 0, '', '2018-06-20 13:01:16', '2018-06-20 13:01:16');
INSERT INTO `api` VALUES (8, '新增 API', '/api/system/api/add_one', '/API/SYSTEM/API/ADD_ONE', 5, 0, '', '2018-06-20 13:01:45', '2018-06-20 13:01:45');
INSERT INTO `api` VALUES (9, '保存 API', '/api/system/api/save_one', '/API/SYSTEM/API/SAVE_ONE', 5, 0, '', '2018-06-20 13:02:03', '2018-06-20 13:02:03');
INSERT INTO `api` VALUES (10, '指定 API id ，批量删除 API', '/api/system/api/delete_all', '/API/SYSTEM/API/DELETE_ALL', 5, 0, '', '2018-06-20 13:02:22', '2018-06-20 13:02:22');
INSERT INTO `api` VALUES (11, '指定 API id ， 获取 API 信息', '/api/system/api/one', '/API/SYSTEM/API/ONE', 5, 0, '', '2018-06-20 13:02:40', '2018-06-20 13:02:40');
INSERT INTO `api` VALUES (12, '分页获取所有父级数据字典分类', '/api/system/dictionary_category/page_all_parent', '/API/SYSTEM/DICTIONARY_CATEGORY/PAGE_ALL_PARENT', 6, 0, '', '2018-06-20 13:04:25', '2018-06-20 13:04:25');
INSERT INTO `api` VALUES (13, '指定父级数据字典分类 id ，获取所有数据字典分类', '/api/system/dictionary_category/list_all_by_parent_id', '/API/SYSTEM/DICTIONARY_CATEGORY/LIST_ALL_BY_PARENT_ID', 6, 0, '', '2018-06-20 13:04:43', '2018-06-20 13:04:43');
INSERT INTO `api` VALUES (14, '新增数据字典分类', '/api/system/dictionary_category/add_one', '/API/SYSTEM/DICTIONARY_CATEGORY/ADD_ONE', 6, 0, '', '2018-06-20 13:05:26', '2018-06-20 13:05:02');
INSERT INTO `api` VALUES (15, '保存数据字典分类', '/api/system/dictionary_category/save_one', '/API/SYSTEM/DICTIONARY_CATEGORY/SAVE_ONE', 6, 0, '', '2018-06-20 13:05:19', '2018-06-20 13:05:19');
INSERT INTO `api` VALUES (16, '指定数据字典分类 id ，批量删除数据字典分类', '/api/system/dictionary_category/delete_all', '/API/SYSTEM/DICTIONARY_CATEGORY/DELETE_ALL', 6, 0, '', '2018-06-20 13:05:44', '2018-06-20 13:05:44');
INSERT INTO `api` VALUES (17, '指定数据字典分类 id ，获取数据字典分类信息', '/api/system/dictionary_category/one', '/API/SYSTEM/DICTIONARY_CATEGORY/ONE', 6, 0, '', '2018-06-20 13:06:00', '2018-06-20 13:06:00');
INSERT INTO `api` VALUES (18, '指定父级数据字典分类 id ，获取所有数据字典', '/api/system/dictionary/page_all_by_dictionary_category_id', '/API/SYSTEM/DICTIONARY/PAGE_ALL_BY_DICTIONARY_CATEGORY_ID', 7, 0, '', '2018-06-20 13:06:49', '2018-06-20 13:06:49');
INSERT INTO `api` VALUES (19, '新增数据字典', '/api/system/dictionary/add_one', '/API/SYSTEM/DICTIONARY/ADD_ONE', 7, 0, '', '2018-06-20 13:07:27', '2018-06-20 13:07:27');
INSERT INTO `api` VALUES (20, '保存数据字典', '/api/system/dictionary/save_one', '/API/SYSTEM/DICTIONARY/SAVE_ONE', 7, 0, '', '2018-06-20 13:07:45', '2018-06-20 13:07:45');
INSERT INTO `api` VALUES (21, '指定数据字典 id ， 批量删除数据字典', '/api/system/dictionary/delete_all', '/API/SYSTEM/DICTIONARY/DELETE_ALL', 7, 0, '', '2018-06-20 13:08:06', '2018-06-20 13:08:06');
INSERT INTO `api` VALUES (22, '指定数据字典 id ，获取数据字典信息', '/api/system/dictionary/one', '/API/SYSTEM/DICTIONARY/ONE', 7, 0, '', '2018-06-20 13:10:51', '2018-06-20 13:08:54');
INSERT INTO `api` VALUES (23, '获取所有数据字典', '/api/system/dictionary/list_all', '/API/SYSTEM/DICTIONARY/LIST_ALL', 7, 0, '', '2018-06-20 13:11:16', '2018-06-20 13:11:16');
INSERT INTO `api` VALUES (24, '指定数据字典键 ，获取所有数据字典', '/api/system/dictionary/list_all_by_key', '/API/SYSTEM/DICTIONARY/LIST_ALL_BY_KEY', 7, 0, '', '2018-06-20 13:11:55', '2018-06-20 13:11:55');
INSERT INTO `api` VALUES (25, '是否关联 API', '/api/system/view_page_api/is_associated_by_page_id_and_api_id', '/API/SYSTEM/VIEW_PAGE_API/IS_ASSOCIATED_BY_PAGE_ID_AND_API_ID', 8, 0, '', '2018-06-20 14:25:36', '2018-06-20 14:25:36');
INSERT INTO `api` VALUES (26, '批量关联 API', '/api/system/view_page_api/associate_all_by_page_id_and_api_id_list', '/API/SYSTEM/VIEW_PAGE_API/ASSOCIATE_ALL_BY_PAGE_ID_AND_API_ID_LIST', 8, 0, '', '2018-06-20 14:26:00', '2018-06-20 14:26:00');
INSERT INTO `api` VALUES (27, '指定视图页面 id、API id ，批量撤销关联', '/api/system/view_page_api/revoke_associate_all_by_page_id_and_api_id_list', '/API/SYSTEM/VIEW_PAGE_API/REVOKE_ASSOCIATE_ALL_BY_PAGE_ID_AND_API_ID_LIST', 8, 0, '', '2018-06-20 14:26:26', '2018-06-20 14:26:26');
INSERT INTO `api` VALUES (28, '分页获取所有父级视图页面分类', '/api/system/view_page_category/page_all_parent', '/API/SYSTEM/VIEW_PAGE_CATEGORY/PAGE_ALL_PARENT', 9, 0, '', '2018-06-20 14:27:26', '2018-06-20 14:27:26');
INSERT INTO `api` VALUES (29, '指定父级视图页面分类 id ，获取所有视图页面分类', '/api/system/view_page_category/list_all_by_parent_id', '/API/SYSTEM/VIEW_PAGE_CATEGORY/LIST_ALL_BY_PARENT_ID', 9, 0, '', '2018-06-20 14:27:45', '2018-06-20 14:27:45');
INSERT INTO `api` VALUES (30, '新增视图页面分类', '/api/system/view_page_category/add_one', '/API/SYSTEM/VIEW_PAGE_CATEGORY/ADD_ONE', 9, 0, '', '2018-06-20 14:28:02', '2018-06-20 14:28:02');
INSERT INTO `api` VALUES (31, '保存视图页面分类', '/api/system/view_page_category/save_one', '/API/SYSTEM/VIEW_PAGE_CATEGORY/SAVE_ONE', 9, 0, '', '2018-06-20 14:28:17', '2018-06-20 14:28:17');
INSERT INTO `api` VALUES (32, '指定视图页面分类 id ，批量删除视图页面分类', '/api/system/view_page_category/delete_all', '/API/SYSTEM/VIEW_PAGE_CATEGORY/DELETE_ALL', 9, 0, '', '2018-06-20 14:28:36', '2018-06-20 14:28:36');
INSERT INTO `api` VALUES (33, '指定视图页面分类 id ，获取视图页面分类信息', '/api/system/view_page_category/one', '/API/SYSTEM/VIEW_PAGE_CATEGORY/ONE', 9, 0, '', '2018-06-20 14:29:04', '2018-06-20 14:29:04');
INSERT INTO `api` VALUES (34, '指定父级视图页面分类 id ，获取所有视图页面', '/api/system/view_page/page_all_by_page_category_id', '/API/SYSTEM/VIEW_PAGE/PAGE_ALL_BY_PAGE_CATEGORY_ID', 10, 0, '', '2018-06-20 14:38:43', '2018-06-20 14:38:43');
INSERT INTO `api` VALUES (35, '新增视图页面', '/api/system/view_page/add_one', '/API/SYSTEM/VIEW_PAGE/ADD_ONE', 10, 0, '', '2018-06-20 14:39:26', '2018-06-20 14:39:26');
INSERT INTO `api` VALUES (36, '保存视图页面', '/api/system/view_page/save_one', '/API/SYSTEM/VIEW_PAGE/SAVE_ONE', 10, 0, '', '2018-06-20 14:39:53', '2018-06-20 14:39:53');
INSERT INTO `api` VALUES (37, '指定视图页面 id ，批量删除视图页面', '/api/system/view_page/delete_all', '/API/SYSTEM/VIEW_PAGE/DELETE_ALL', 10, 0, '', '2018-06-20 14:40:17', '2018-06-20 14:40:17');
INSERT INTO `api` VALUES (38, '指定视图页面 id ，获取视图页面信息', '/api/system/view_page/one', '/API/SYSTEM/VIEW_PAGE/ONE', 10, 0, '', '2018-06-20 14:40:34', '2018-06-20 14:40:34');
INSERT INTO `api` VALUES (39, '分页获取所有视图页面组件', '/api/system/view_page_component/page_all_by_page_id', '/API/SYSTEM/VIEW_PAGE_COMPONENT/PAGE_ALL_BY_PAGE_ID', 11, 0, '', '2018-06-20 14:45:30', '2018-06-20 14:45:30');
INSERT INTO `api` VALUES (40, '新增视图页面组件', '/api/system/view_page_component/add_one', '/API/SYSTEM/VIEW_PAGE_COMPONENT/ADD_ONE', 11, 0, '', '2018-06-20 14:45:45', '2018-06-20 14:45:45');
INSERT INTO `api` VALUES (41, '保存视图页面组件', '/api/system/view_page_component/save_one', '/API/SYSTEM/VIEW_PAGE_COMPONENT/SAVE_ONE', 11, 0, '', '2018-06-20 14:46:03', '2018-06-20 14:46:03');
INSERT INTO `api` VALUES (42, '指定视图页面组件 id ，批量删除视图页面组件', '/api/system/view_page_component/delete_all', '/API/SYSTEM/VIEW_PAGE_COMPONENT/DELETE_ALL', 11, 0, '', '2018-06-20 14:46:22', '2018-06-20 14:46:22');
INSERT INTO `api` VALUES (43, '指定视图组件 id ，获取视图组件信息', '/api/system/view_page_component/one', '/API/SYSTEM/VIEW_PAGE_COMPONENT/ONE', 11, 0, '', '2018-06-20 14:46:40', '2018-06-20 14:46:40');
INSERT INTO `api` VALUES (44, '是否关联 API', '/api/system/view_page_component_api/is_associated_by_page_component_id_and_api_id', '/API/SYSTEM/VIEW_PAGE_COMPONENT_API/IS_ASSOCIATED_BY_PAGE_COMPONENT_ID_AND_API_ID', 12, 0, '', '2018-06-20 14:48:47', '2018-06-20 14:48:47');
INSERT INTO `api` VALUES (45, '批量关联 API', '/api/system/view_page_component_api/associate_all_by_page_component_id_and_api_id_list', '/API/SYSTEM/VIEW_PAGE_COMPONENT_API/ASSOCIATE_ALL_BY_PAGE_COMPONENT_ID_AND_API_ID_LIST', 12, 0, '', '2018-06-20 14:49:42', '2018-06-20 14:49:42');
INSERT INTO `api` VALUES (46, '指定视图页面组件 id、API id ，批量撤销关联', '/api/system/view_page_component_api/revoke_associate_all_by_page_component_id_and_api_id_list', '/API/SYSTEM/VIEW_PAGE_COMPONENT_API/REVOKE_ASSOCIATE_ALL_BY_PAGE_COMPONENT_ID_AND_API_ID_LIST', 12, 0, '', '2018-06-20 14:50:43', '2018-06-20 14:50:43');
INSERT INTO `api` VALUES (47, '指定角色 id、视图页面权限（authority），批量授权', '/api/user/role_authority/grant_all_by_role_id_and_page_authority_list', '/API/USER/ROLE_AUTHORITY/GRANT_ALL_BY_ROLE_ID_AND_PAGE_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:24:13', '2018-06-20 15:07:15');
INSERT INTO `api` VALUES (48, '指定角色 id、视图页面权限（authority），批量撤销授权', '/api/user/role_authority/revoke_all_by_role_id_and_page_authority_list', '/API/USER/ROLE_AUTHORITY/REVOKE_ALL_BY_ROLE_ID_AND_PAGE_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:24:17', '2018-06-20 15:07:39');
INSERT INTO `api` VALUES (49, '指定角色 id、视图页面组件权限（authority），批量授权', '/api/user/role_authority/grant_all_by_role_id_and_page_component_authority_list', '/API/USER/ROLE_AUTHORITY/GRANT_ALL_BY_ROLE_ID_AND_PAGE_COMPONENT_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:24:20', '2018-06-20 15:08:11');
INSERT INTO `api` VALUES (50, '指定角色 id、视图页面组件权限（authority），批量撤销授权', '/api/user/role_authority/revoke_all_by_role_id_and_page_component_authority_list', '/API/USER/ROLE_AUTHORITY/REVOKE_ALL_BY_ROLE_ID_AND_PAGE_COMPONENT_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:24:24', '2018-06-20 15:08:27');
INSERT INTO `api` VALUES (51, '指定角色权限 id ，获取角色权限信息', '/api/user/role_authority/one', '/API/USER/ROLE_AUTHORITY/ONE', 13, 0, '', '2018-06-20 15:08:44', '2018-06-20 15:08:44');
INSERT INTO `api` VALUES (52, '指定角色 id、权限（authority）判断是否已授权', '/api/user/role_authority/is_granted_by_role_id_and_authority', '/API/USER/ROLE_AUTHORITY/IS_GRANTED_BY_ROLE_ID_AND_AUTHORITY', 13, 0, '', '2018-06-20 15:10:50', '2018-06-20 15:10:50');
INSERT INTO `api` VALUES (53, '分页获取所有角色', '/api/user/role/page_all', '/API/USER/ROLE/PAGE_ALL', 14, 0, '', '2018-06-20 15:12:12', '2018-06-20 15:12:12');
INSERT INTO `api` VALUES (54, '获取所有角色', '/api/user/role/list_all', '/API/USER/ROLE/LIST_ALL', 14, 0, '', '2018-06-20 15:12:38', '2018-06-20 15:12:38');
INSERT INTO `api` VALUES (55, '新增角色', '/api/user/role/add_one', '/API/USER/ROLE/ADD_ONE', 14, 0, '', '2018-06-20 15:12:53', '2018-06-20 15:12:53');
INSERT INTO `api` VALUES (56, '保存角色', '/api/user/role/save_one', '/API/USER/ROLE/SAVE_ONE', 14, 0, '', '2018-06-20 15:13:14', '2018-06-20 15:13:14');
INSERT INTO `api` VALUES (57, '指定角色 id ，批量删除角色', '/api/user/role/delete_all', '/API/USER/ROLE/DELETE_ALL', 14, 0, '', '2018-06-20 15:13:40', '2018-06-20 15:13:40');
INSERT INTO `api` VALUES (58, '指定角色 id ，获取角色信息', '/api/user/role/one', '/API/USER/ROLE/ONE', 14, 0, '', '2018-06-20 15:13:57', '2018-06-20 15:13:57');
INSERT INTO `api` VALUES (59, '指定用户 id ，获取用户角色', '/api/user/role/list_all_by_user_id', '/API/USER/ROLE/LIST_ALL_BY_USER_ID', 14, 0, '', '2018-06-20 15:14:27', '2018-06-20 15:14:27');
INSERT INTO `api` VALUES (60, '分页获取用户', '/api/user/page_all', '/API/USER/PAGE_ALL', 15, 0, '', '2018-06-20 15:16:24', '2018-06-20 15:16:24');
INSERT INTO `api` VALUES (61, '新增用户', '/api/user/add_one', '/API/USER/ADD_ONE', 15, 0, '', '2018-06-20 15:16:40', '2018-06-20 15:16:40');
INSERT INTO `api` VALUES (62, '保存用户', '/api/user/save_one', '/API/USER/SAVE_ONE', 15, 0, '', '2018-06-20 15:16:55', '2018-06-20 15:16:55');
INSERT INTO `api` VALUES (63, '伪删除用户', '/api/user/sign_all_gmt_deleted', '/API/USER/SIGN_ALL_GMT_DELETED', 15, 0, '', '2018-06-24 21:23:49', '2018-06-24 21:23:49');
INSERT INTO `api` VALUES (64, '撤销伪删除用户', '/api/user/unsign_all_gmt_deleted', '/API/USER/UNSIGN_ALL_GMT_DELETED', 15, 0, '', '2018-06-24 21:24:07', '2018-06-24 21:24:07');
INSERT INTO `api` VALUES (65, '指定用户 id ，批量删除用户', '/api/user/delete_all', '/API/USER/DELETE_ALL', 15, 0, '', '2018-06-24 21:24:54', '2018-06-24 21:24:54');
INSERT INTO `api` VALUES (66, '指定用户 id ，获取用户信息（除密码和角色）', '/api/user/one', '/API/USER/ONE', 15, 0, '', '2018-06-24 21:25:22', '2018-06-24 21:25:22');
INSERT INTO `api` VALUES (67, '分页获取所有用户角色', '/api/user/user_role/page_all', '/API/USER/USER_ROLE/PAGE_ALL', 16, 0, '', '2018-06-24 21:26:34', '2018-06-24 21:26:34');
INSERT INTO `api` VALUES (68, '新增用户角色', '/api/user/user_role/add_one', '/API/USER/USER_ROLE/ADD_ONE', 16, 0, '', '2018-06-24 21:26:53', '2018-06-24 21:26:53');
INSERT INTO `api` VALUES (69, '指定用户角色 id ，批量删除用户角色', '/api/user/user_role/delete_all_by_user_id_and_role_id_list', '/API/USER/USER_ROLE/DELETE_ALL_BY_USER_ID_AND_ROLE_ID_LIST', 16, 0, '', '2018-06-24 21:27:42', '2018-06-24 21:27:42');
INSERT INTO `api` VALUES (70, '指定角色 id ，分页获取所有父级视图菜单分类', '/api/user/view_menu_category/page_all_parent_by_role_id', '/API/USER/VIEW_MENU_CATEGORY/PAGE_ALL_PARENT_BY_ROLE_ID', 17, 0, '', '2018-06-24 21:30:19', '2018-06-24 21:28:38');
INSERT INTO `api` VALUES (71, '指定父级视图菜单分类 id 、角色 id ，获取所有视图菜单分类', '/api/user/view_menu_category/list_all_by_parent_id_and_role_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_BY_PARENT_ID_AND_ROLE_ID', 17, 0, '', '2018-06-24 21:30:36', '2018-06-24 21:29:06');
INSERT INTO `api` VALUES (72, '新增视图菜单分类', '/api/user/view_menu_category/add_one', '/API/USER/VIEW_MENU_CATEGORY/ADD_ONE', 17, 0, '', '2018-06-24 21:30:55', '2018-06-24 21:30:55');
INSERT INTO `api` VALUES (73, '保存视图菜单分类', '/api/user/view_menu_category/save_one', '/API/USER/VIEW_MENU_CATEGORY/SAVE_ONE', 17, 0, '', '2018-06-24 21:31:10', '2018-06-24 21:31:10');
INSERT INTO `api` VALUES (74, '指定视图菜单分类 id ，批量删除视图菜单分类', '/api/user/view_menu_category/delete_all', '/API/USER/VIEW_MENU_CATEGORY/DELETE_ALL', 17, 0, '', '2018-06-24 21:31:30', '2018-06-24 21:31:30');
INSERT INTO `api` VALUES (75, '指定视图菜单分类 id ，获取视图菜单分类信息', '/api/user/view_menu_category/one', '/API/USER/VIEW_MENU_CATEGORY/ONE', 17, 0, '', '2018-06-24 21:31:52', '2018-06-24 21:31:52');
INSERT INTO `api` VALUES (76, '指定角色 id ，获取所有父级视图菜单分类', '/api/user/view_menu_category/list_all_parent_by_role_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_PARENT_BY_ROLE_ID', 17, 0, '', '2018-06-24 21:32:16', '2018-06-24 21:32:16');
INSERT INTO `api` VALUES (77, '指定用户 id ，获取所有父级视图菜单分类', '/api/user/view_menu_category/list_all_parent_by_user_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_PARENT_BY_USER_ID', 17, 0, '首页显示顶部菜单分类', '2018-06-29 12:09:25', '2018-06-24 21:32:36');
INSERT INTO `api` VALUES (78, '指定用户 id 、父级视图菜单分类 id ，获取所有子级视图菜单分类', '/api/user/view_menu_category/list_all_child_by_parent_id_and_user_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_CHILD_BY_PARENT_ID_AND_USER_ID', 17, 0, '首页显示左侧子级菜单分类', '2018-06-29 12:09:11', '2018-06-24 21:33:19');
INSERT INTO `api` VALUES (79, '指定用户 id 、父级视图菜单分类 id ，获取所有子级视图菜单分类和视图菜单', '/api/user/view_menu_category/list_all_child_view_menu_category_and_view_menu_by_parent_id_and_user_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_CHILD_VIEW_MENU_CATEGORY_AND_VIEW_MENU_BY_PARENT_ID_AND_USER_ID', 17, 0, '首页显示子级菜单分类及子级菜单', '2018-06-29 12:10:22', '2018-06-24 21:34:15');
INSERT INTO `api` VALUES (80, '获取所有菜单分类', '/api/user/view_menu_category/list_all', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL', 17, 0, '', '2018-06-24 21:35:24', '2018-06-24 21:35:24');
INSERT INTO `api` VALUES (81, '指定菜单分类名，模糊搜索获取所有菜单分类', '/api/user/view_menu_category/search_all_by_name', '/API/USER/VIEW_MENU_CATEGORY/SEARCH_ALL_BY_NAME', 17, 0, '', '2018-06-24 21:35:49', '2018-06-24 21:35:49');
INSERT INTO `api` VALUES (82, '指定视图菜单分类 id 、角色 id ，分页获取所有视图菜单', '/api/user/view_menu/page_all_by_menu_category_id_and_role_id', '/API/USER/VIEW_MENU/PAGE_ALL_BY_MENU_CATEGORY_ID_AND_ROLE_ID', 18, 0, '', '2018-06-24 21:36:50', '2018-06-24 21:36:50');
INSERT INTO `api` VALUES (83, '新增视图菜单', '/api/user/view_menu/add_one', '/API/USER/VIEW_MENU/ADD_ONE', 18, 0, '', '2018-06-24 21:37:11', '2018-06-24 21:37:11');
INSERT INTO `api` VALUES (84, '保存视图菜单', '/api/user/view_menu/save_one', '/API/USER/VIEW_MENU/SAVE_ONE', 18, 0, '', '2018-06-24 21:37:29', '2018-06-24 21:37:29');
INSERT INTO `api` VALUES (85, '指定视图菜单 id ，批量删除视图菜单', '/api/user/view_menu/delete_all', '/API/USER/VIEW_MENU/DELETE_ALL', 18, 0, '', '2018-06-24 21:37:51', '2018-06-24 21:37:51');
INSERT INTO `api` VALUES (86, '指定视图菜单 id ，获取视图菜单信息', '/api/user/view_menu/one', '/API/USER/VIEW_MENU/ONE', 18, 0, '', '2018-06-24 21:38:11', '2018-06-24 21:38:11');
INSERT INTO `api` VALUES (87, '指定菜单名，模糊搜索获取所有菜单', '/api/user/view_menu/search_all_by_name', '/API/USER/VIEW_MENU/SEARCH_ALL_BY_NAME', 18, 0, '', '2018-06-24 21:38:53', '2018-06-24 21:38:53');
INSERT INTO `api` VALUES (88, '指定角色 id、API 权限（authority），批量授权', '/api/user/role_authority/grant_all_by_role_id_and_api_authority_list', '/API/USER/ROLE_AUTHORITY/GRANT_ALL_BY_ROLE_ID_AND_API_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:24:04', '2018-07-03 21:24:04');
INSERT INTO `api` VALUES (89, '指定角色 id、API 权限（authority），批量撤销授权', '/api/user/role_authority/revoke_all_by_role_id_and_api_authority_list', '/API/USER/ROLE_AUTHORITY/REVOKE_ALL_BY_ROLE_ID_AND_API_AUTHORITY_LIST', 13, 0, '', '2018-07-03 21:25:13', '2018-07-03 21:25:13');
COMMIT;

-- ----------------------------
-- Table structure for api_category
-- ----------------------------
DROP TABLE IF EXISTS `api_category`;
CREATE TABLE `api_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级分类 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='API 分类表';

-- ----------------------------
-- Records of api_category
-- ----------------------------
BEGIN;
INSERT INTO `api_category` VALUES (1, '系统管理', NULL, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (2, '系统配置', 1, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (3, '用户配置', 1, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (4, 'API 分类', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (5, 'API', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (6, '数据字典分类', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (7, '数据字典', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (8, '视图页面关联 API', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (9, '视图页面分类', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (10, '视图页面', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (11, '视图页面组件', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (12, '视图页面组件关联 API', 2, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (13, '角色关联权限', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (14, '角色', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (15, '用户', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (16, '用户关联角色', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (17, '视图菜单分类', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (18, '视图菜单', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
COMMIT;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `guid` varchar(255) NOT NULL COMMENT '唯一标识符',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `pretty_size` varchar(255) NOT NULL COMMENT '文件美化大小',
  `mime_type` varchar(255) DEFAULT '' COMMENT 'MIME 类型',
  `path` text COMMENT '文件路径',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级部门 id',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES (1, '测试部门', NULL, '', '2018-12-20 06:08:12', '2018-12-20 03:43:04');
COMMIT;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `key` varchar(255) NOT NULL COMMENT '字典键',
  `key_name` varchar(255) NOT NULL COMMENT '字典键名',
  `value_name` varchar(255) NOT NULL COMMENT '字典值名',
  `value_slug` varchar(255) NOT NULL COMMENT '字典值别名',
  `value` text COMMENT '字典值',
  `enable` tinyint(1) unsigned DEFAULT NULL COMMENT '是否有效（0=否，1=是，默认=0）',
  `dictionary_category_id` bigint(20) unsigned NOT NULL COMMENT '字典分类 id',
  `sort` bigint(20) unsigned DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典表';

-- ----------------------------
-- Records of dictionary
-- ----------------------------
BEGIN;
INSERT INTO `dictionary` VALUES (1, 'WEB', '网站字段', '网站名', 'NAME', 'Nimrod', 1, 3, 0, '', '2019-01-10 02:25:43', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (2, 'WEB', '网站字段', '页脚版权', 'FOOTER', 'Copyright &copy; 2019 Nimrod.All rights reserved.', 1, 3, 0, '', '2019-01-04 08:34:31', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (3, 'MAIL', '电子邮箱发信配置', '主机', 'HOST', 'smtp.exmail.qq.com', 1, 4, 0, '腾讯企业邮箱：https://exmail.qq.com/', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (4, 'MAIL', '电子邮箱发信配置', '协议', 'PROTOCOL', 'smtp', 1, 4, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (5, 'MAIL', '电子邮箱发信配置', '端口号', 'PORT', '25', 1, 4, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (6, 'MAIL', '电子邮箱发信配置', '用户名', 'USERNAME', 'no-reply@example.com', 1, 4, 0, '', '2018-12-24 01:27:17', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (7, 'MAIL', '电子邮箱发信配置', '密码', 'PASSWORD', '', 1, 4, 0, '', '2018-12-24 01:28:13', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (8, 'MAIL', '电子邮箱发信配置', '显示邮箱', 'FROM', 'no-reply@example.com', 1, 4, 0, '', '2018-12-24 01:27:25', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (9, 'MAIL', '电子邮箱发信配置', '默认编码', 'DEFAULT_ENCODING', 'UTF-8', 1, 4, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (10, 'MAIL', '电子邮箱发信配置', '测试连接', 'TEST_CONNECTION', 'false', 1, 4, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (11, 'VIEW_PAGE_COMPONENT_TYPE', '视图页面组件类型', '按钮', 'BUTTON', '1', 1, 5, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (12, 'VIEW_PAGE_COMPONENT_TYPE', '视图页面组件类型', '搜索框', 'SEARCH', '2', 1, 5, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (16, 'SMS_STATUS', '信息状态', '等待', 'WAIT', '0', 1, 7, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (17, 'SMS_STATUS', '信息状态', '失败', 'FAIL', '1', 1, 7, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (18, 'SMS_STATUS', '信息状态', '成功', 'SUCCESS', '2', 1, 7, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (19, 'IS_OR_NOT', '是或否', '否', 'NOT', '0', 1, 8, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (20, 'IS_OR_NOT', '是或否', '是', 'IS', '1', 1, 8, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (21, 'GENDER', '性别', '未知', 'UNKNOWN', '0', 1, 9, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (22, 'GENDER', '性别', '男', 'MALE', '1', 1, 9, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (23, 'GENDER', '性别', '女', 'FEMALE', '2', 1, 9, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (24, 'ATTACHMENT', '附件配置', '上传路径', 'UPLOAD_PATH', '/upload', 0, 11, 0, '', '2019-01-09 06:53:06', '2018-11-19 07:41:07');
INSERT INTO `dictionary` VALUES (25, 'OPERATION_TYPE', '操作类型', '访问页面', 'PAGE', '0', 1, 12, 0, '', '2018-12-27 03:18:00', '2018-12-17 12:13:32');
INSERT INTO `dictionary` VALUES (26, 'OPERATION_TYPE', '操作类型', '调用 API', 'API', '1', 1, 12, 0, '', '2018-12-27 03:18:08', '2018-12-17 12:14:42');
INSERT INTO `dictionary` VALUES (27, 'ATTACHMENT', '附件配置', '附件类型', 'TYPE', '.jpg,.png,.zip', 0, 11, 0, '允许上传的文件类型', '2019-01-09 06:53:11', '2018-12-17 13:08:13');
INSERT INTO `dictionary` VALUES (28, 'ATTACHMENT', '附件配置', '最大文件上传大小', 'MAX_FILE_SIZE', '1KB', 0, 11, 0, '允许上传的最大文件大小', '2019-01-09 06:53:16', '2018-12-17 13:10:23');
INSERT INTO `dictionary` VALUES (29, 'SYSTEM', '系统配置', '语言', 'LANGUAGE', 'zh', 0, 13, 0, '', '2019-01-09 06:32:49', '2018-12-19 08:25:09');
INSERT INTO `dictionary` VALUES (30, 'SYSTEM', '系统配置', '国家/区域', 'COUNTY', 'CN', 0, 13, 0, '', '2019-01-09 06:32:55', '2018-12-19 08:27:08');
INSERT INTO `dictionary` VALUES (31, 'SYSTEM', '系统配置', '时区标识符', 'TIME_ZONE_ID', 'GMT+8', 0, 13, 0, '', '2019-01-09 06:32:59', '2018-12-19 08:30:44');
INSERT INTO `dictionary` VALUES (32, 'SYSTEM', '系统配置', '日期格式', 'DATE_FORMAT_PATTERN', 'yyyy-MM-dd HH:mm:ss', 0, 13, 0, '', '2019-01-09 06:33:04', '2018-12-19 08:32:46');
INSERT INTO `dictionary` VALUES (33, 'ATTACHMENT', '附件配置', '最大请求上传大小', 'MAX_REQUEST_SIZE', '10KB', 0, 11, 0, '', '2019-01-09 06:53:20', '2018-12-21 01:05:54');
INSERT INTO `dictionary` VALUES (34, 'VERIFY_CODE', '验证码', '过期时间（秒）', 'EXPIRE_IN', '20', 1, 14, 0, '', '2019-01-09 06:56:53', '2019-01-09 06:56:53');
INSERT INTO `dictionary` VALUES (35, 'VERIFY_CODE', '验证码', '噪点', 'YAWP', 'false', 1, 14, 0, '', '2019-01-09 06:57:56', '2019-01-09 06:57:43');
INSERT INTO `dictionary` VALUES (36, 'VERIFY_CODE', '验证码', '字符长度', 'STRING_LENGTH', '4', 1, 14, 0, '建议字典值设置为4', '2019-01-10 01:02:27', '2019-01-09 06:58:19');
INSERT INTO `dictionary` VALUES (37, 'VERIFY_CODE', '验证码', '干扰线条数', 'INTER_LINE', '0', 1, 14, 0, '建议字典值设置为4', '2019-01-10 01:02:56', '2019-01-09 06:59:09');
COMMIT;

-- ----------------------------
-- Table structure for dictionary_category
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_category`;
CREATE TABLE `dictionary_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级分类 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典分类表';

-- ----------------------------
-- Records of dictionary_category
-- ----------------------------
BEGIN;
INSERT INTO `dictionary_category` VALUES (1, '系统缺省字段', NULL, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (2, '通用缺省字段', NULL, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (3, '网站配置', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (4, '电子邮箱配置', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (5, '视图页面组件类型', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (7, '信息状态', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (8, '是否', 2, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (9, '性别', 2, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (11, '附件配置', 1, 0, '', '2018-11-19 07:39:44', '2018-11-19 07:39:44');
INSERT INTO `dictionary_category` VALUES (12, '操作类型', 1, 0, '', '2018-12-17 12:02:51', '2018-12-17 12:02:51');
INSERT INTO `dictionary_category` VALUES (13, '系统配置', 1, 0, '', '2018-12-19 08:23:57', '2018-12-19 08:23:57');
INSERT INTO `dictionary_category` VALUES (14, '验证码', 1, 0, '', '2019-01-04 08:23:15', '2019-01-04 08:23:15');
COMMIT;

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `status` tinyint(1) NOT NULL COMMENT '发信状态',
  `from` varchar(255) DEFAULT '' COMMENT '发件人',
  `to` varchar(255) NOT NULL COMMENT '收件人',
  `subject` varchar(255) DEFAULT '' COMMENT '主题',
  `text` text COMMENT '内容',
  `html` tinyint(1) DEFAULT '0' COMMENT '是否为 html，0=否，1=是',
  `error` text COMMENT '发信报错信息',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子邮件表';

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '访问用户 id',
  `ip_address` varchar(255) DEFAULT '' COMMENT '用户 IP',
  `operation_type` tinyint(1) DEFAULT NULL COMMENT '操作类型',
  `operation` text COMMENT '操作说明',
  `request_time` bigint(255) DEFAULT '0' COMMENT '请求耗时（毫秒）',
  `request_url` text COMMENT '请求地址',
  `request_method` varchar(50) DEFAULT '' COMMENT '请求方法',
  `request_parameter` text COMMENT '请求参数',
  `accept_language` varchar(255) DEFAULT '' COMMENT '请求语言',
  `referer` text COMMENT '请求来源',
  `user_agent` varchar(255) DEFAULT '' COMMENT '用户代理',
  `handler` text COMMENT 'Handler',
  `session_id` varchar(255) DEFAULT '' COMMENT 'Session ID',
  `cookie` text COMMENT 'Cookie',
  `content_type` varchar(255) DEFAULT '' COMMENT '响应文本类型',
  `status` varchar(255) DEFAULT '' COMMENT '响应状态码',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统操作日志表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `value` varchar(191) NOT NULL COMMENT '角色值',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_value` (`value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '普通用户', 'NORMAL_USER', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
INSERT INTO `role` VALUES (2, '管理员', 'ADMIN', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
INSERT INTO `role` VALUES (3, '系统管理员', 'SYSTEM_ADMIN', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
COMMIT;

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  `authority` varchar(255) NOT NULL COMMENT '权限（authority）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='角色关联权限表';

-- ----------------------------
-- Records of role_authority
-- ----------------------------
BEGIN;
INSERT INTO `role_authority` VALUES (1, 2, '/');
INSERT INTO `role_authority` VALUES (2, 2, '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_CHILD_VIEW_MENU_CATEGORY_AND_VIEW_MENU_BY_PARENT_ID_AND_USER_ID');
INSERT INTO `role_authority` VALUES (3, 2, '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_CHILD_BY_PARENT_ID_AND_USER_ID');
INSERT INTO `role_authority` VALUES (4, 2, '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_PARENT_BY_USER_ID');
INSERT INTO `role_authority` VALUES (5, 2, '/SYSTEM/WORKBENCH');
INSERT INTO `role_authority` VALUES (6, 2, '/SYSTEM/VIEW_PAGE/PAGE_ALL');
INSERT INTO `role_authority` VALUES (7, 2, '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/ADD');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(191) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `email_is_verified` tinyint(1) unsigned DEFAULT NULL COMMENT '电子邮箱是否验证通过（0=未验证，1=已验证，默认=0）',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门 id',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_deleted` datetime DEFAULT NULL COMMENT '删除时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '$2a$10$2ASJsYX1q5AMjHCji6DkrOt/mmS4D1nCXwnUW4kt.n/FvONR6Kjiq', 'normal_user', 'normal_user@outlook.com', 1, 1, '测试备注', NULL, '2018-11-13 04:26:59', '2018-06-27 21:22:40');
INSERT INTO `user` VALUES (2, '$2a$10$2ASJsYX1q5AMjHCji6DkrOt/mmS4D1nCXwnUW4kt.n/FvONR6Kjiq', 'admin', 'admin@outlook.com', 1, 1, '测试备注', NULL, '2018-11-13 04:27:08', '2018-06-27 21:22:40');
INSERT INTO `user` VALUES (3, '$2a$10$2ASJsYX1q5AMjHCji6DkrOt/mmS4D1nCXwnUW4kt.n/FvONR6Kjiq', 'system_admin', 'godcheese@outlook.com', 1, 1, '测试备注', NULL, '2018-11-13 04:26:44', '2018-06-27 21:22:40');
COMMIT;

-- ----------------------------
-- Table structure for user_password_reset
-- ----------------------------
DROP TABLE IF EXISTS `user_password_reset`;
CREATE TABLE `user_password_reset` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户 id',
  `verify_from` varchar(191) NOT NULL COMMENT '用户绑定的电子邮箱或手机号码',
  `verify_code` varchar(191) NOT NULL COMMENT '电子邮箱或手机号码验证码',
  `gmt_expires` datetime NOT NULL COMMENT '过期时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `uk_verify_from` (`verify_from`) USING BTREE,
  UNIQUE KEY `uk_verify_code` (`verify_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户密码重置表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户 id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户关联角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 3);
COMMIT;

-- ----------------------------
-- Table structure for view_menu
-- ----------------------------
DROP TABLE IF EXISTS `view_menu`;
CREATE TABLE `view_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图标（icon）',
  `url` text COMMENT '请求地址（url）',
  `menu_category_id` bigint(20) unsigned NOT NULL COMMENT '菜单分类 id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='视图菜单表';

-- ----------------------------
-- Records of view_menu
-- ----------------------------
BEGIN;
INSERT INTO `view_menu` VALUES (1, 'API 管理', '', '/system/api/page_all', 2, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (2, '数据字典', '', '/system/dictionary/page_all', 2, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (3, '视图页面管理', '', '/system/view_page/page_all', 2, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (4, 'Druid Monitor', '', '/druid', 2, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (5, '用户管理', '', '/user/page_all', 3, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (6, '角色管理', '', '/user/role/page_all', 3, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (7, '发送邮件', '', '/system/mail/send', 4, 3, 0, '', '2018-12-18 13:03:54', '2018-07-08 13:20:28');
INSERT INTO `view_menu` VALUES (8, '邮件队列', '', '/system/mail/page_all', 4, 3, 0, '', '2018-12-18 13:04:01', '2018-07-08 13:22:30');
INSERT INTO `view_menu` VALUES (9, '操作日志', '', '/system/operation_log/page_all', 2, 3, 0, '', '2018-12-11 03:13:08', '2018-08-06 16:47:15');
INSERT INTO `view_menu` VALUES (10, '附件管理', 'fa fa-archive', '/system/attachment/page_all', 2, 3, 0, '', '2018-10-20 19:07:33', '2018-10-20 19:01:23');
INSERT INTO `view_menu` VALUES (11, '部门管理', '', '/user/department/page_all', 3, 3, 0, '', '2018-12-20 02:43:28', '2018-12-20 02:43:28');
COMMIT;

-- ----------------------------
-- Table structure for view_menu_category
-- ----------------------------
DROP TABLE IF EXISTS `view_menu_category`;
CREATE TABLE `view_menu_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图标（icon）',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级分类 id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='视图菜单分类表';

-- ----------------------------
-- Records of view_menu_category
-- ----------------------------
BEGIN;
INSERT INTO `view_menu_category` VALUES (1, '系统管理', 'fa fa-cog', NULL, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (2, '系统配置', 'fa fa-cog', 1, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (3, '用户配置', 'fa fa-user', 1, 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (4, '电子邮箱', '', 2, 3, 0, '', '2018-07-08 13:19:57', '2018-07-08 13:19:57');
COMMIT;

-- ----------------------------
-- Table structure for view_page
-- ----------------------------
DROP TABLE IF EXISTS `view_page`;
CREATE TABLE `view_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '页面名称',
  `url` text COMMENT '请求地址（url）',
  `authority` varchar(191) NOT NULL COMMENT '权限（authority）',
  `page_category_id` bigint(20) unsigned NOT NULL COMMENT '页面分类 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_authority` (`authority`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='视图页面表';

-- ----------------------------
-- Records of view_page
-- ----------------------------
BEGIN;
INSERT INTO `view_page` VALUES (1, '系统首页1', '/', '/', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (2, '系统首页2', '/index', '/INDEX', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (3, '系统首页2', '/system', '/SYSTEM', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (4, '系统首页3', '/system/index', '/SYSTEM/INDEX', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (5, '工作台', '/system/workbench', '/SYSTEM/WORKBENCH', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (6, '视图页面管理', '/system/view_page/page_all', '/SYSTEM/VIEW_PAGE/PAGE_ALL', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (7, '视图页面关联 API', '/system/view_page_api/page_all', '/SYSTEM/VIEW_PAGE_API/PAGE_ALL', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (8, '视图页面组件关联 API', '/system/view_page_component_api/page_all', '/SYSTEM/VIEW_PAGE_COMPONENT_API/PAGE_ALL', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (9, 'API 管理', '/system/api/page_all', '/SYSTEM/API/PAGE_ALL', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (10, '数据字典', '/system/dictionary/page_all', '/SYSTEM/DICTIONARY/PAGE_ALL', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (11, 'Druid Monitor', '/druid', '/DRUID', 2, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (12, '用户管理', '/user/page_all', '/USER/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (13, '角色管理', '/user/role/page_all', '/USER/ROLE/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (14, '角色 API 权限管理', '/user/role_authority/api/page_all', '/USER/ROLE_AUTHORITY/API/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (15, '角色视图页面管理', '/user/role_authority/view_page/page_all', '/USER/ROLE_AUTHORITY/VIEW_PAGE/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (16, '用户角色管理', '/user/user_role/page_all', '/USER/USER_ROLE/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (17, '视图菜单管理', '/user/view_menu/page_all', '/USER/VIEW_MENU/PAGE_ALL', 3, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page` VALUES (18, '发送邮件', '/system/email/send', '/SYSTEM/EMAIL/SEND', 4, 0, '', '2018-07-08 13:16:32', '2018-07-08 13:16:32');
INSERT INTO `view_page` VALUES (19, '邮件队列', '/system/email/page_all', '/SYSTEM/EMAIL/PAGE_ALL', 4, 0, '', '2018-07-08 14:59:14', '2018-07-08 13:17:38');
COMMIT;

-- ----------------------------
-- Table structure for view_page_api
-- ----------------------------
DROP TABLE IF EXISTS `view_page_api`;
CREATE TABLE `view_page_api` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `page_id` bigint(20) unsigned NOT NULL COMMENT '视图页面 id',
  `api_id` bigint(20) unsigned NOT NULL COMMENT 'API id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='视图页面关联 API 表';

-- ----------------------------
-- Records of view_page_api
-- ----------------------------
BEGIN;
INSERT INTO `view_page_api` VALUES (1, 1, 77);
INSERT INTO `view_page_api` VALUES (2, 1, 78);
INSERT INTO `view_page_api` VALUES (3, 1, 79);
INSERT INTO `view_page_api` VALUES (4, 2, 77);
INSERT INTO `view_page_api` VALUES (5, 2, 78);
INSERT INTO `view_page_api` VALUES (6, 2, 79);
INSERT INTO `view_page_api` VALUES (7, 3, 77);
INSERT INTO `view_page_api` VALUES (8, 3, 78);
INSERT INTO `view_page_api` VALUES (9, 3, 79);
INSERT INTO `view_page_api` VALUES (10, 4, 77);
INSERT INTO `view_page_api` VALUES (11, 4, 78);
INSERT INTO `view_page_api` VALUES (12, 4, 79);
INSERT INTO `view_page_api` VALUES (13, 6, 28);
INSERT INTO `view_page_api` VALUES (14, 6, 29);
INSERT INTO `view_page_api` VALUES (15, 6, 34);
INSERT INTO `view_page_api` VALUES (16, 6, 39);
INSERT INTO `view_page_api` VALUES (17, 6, 24);
INSERT INTO `view_page_api` VALUES (18, 7, 1);
INSERT INTO `view_page_api` VALUES (19, 7, 2);
INSERT INTO `view_page_api` VALUES (20, 7, 25);
INSERT INTO `view_page_api` VALUES (21, 8, 1);
INSERT INTO `view_page_api` VALUES (22, 8, 2);
INSERT INTO `view_page_api` VALUES (23, 8, 7);
INSERT INTO `view_page_api` VALUES (24, 8, 44);
INSERT INTO `view_page_api` VALUES (25, 9, 1);
INSERT INTO `view_page_api` VALUES (26, 9, 2);
INSERT INTO `view_page_api` VALUES (27, 9, 7);
INSERT INTO `view_page_api` VALUES (28, 10, 12);
INSERT INTO `view_page_api` VALUES (29, 10, 13);
INSERT INTO `view_page_api` VALUES (30, 10, 18);
INSERT INTO `view_page_api` VALUES (31, 10, 24);
INSERT INTO `view_page_api` VALUES (32, 12, 24);
INSERT INTO `view_page_api` VALUES (33, 12, 60);
INSERT INTO `view_page_api` VALUES (34, 16, 59);
INSERT INTO `view_page_api` VALUES (35, 14, 1);
INSERT INTO `view_page_api` VALUES (36, 14, 2);
INSERT INTO `view_page_api` VALUES (37, 14, 52);
INSERT INTO `view_page_api` VALUES (38, 15, 28);
INSERT INTO `view_page_api` VALUES (39, 15, 29);
INSERT INTO `view_page_api` VALUES (40, 15, 52);
INSERT INTO `view_page_api` VALUES (41, 15, 39);
INSERT INTO `view_page_api` VALUES (42, 17, 70);
INSERT INTO `view_page_api` VALUES (43, 17, 71);
INSERT INTO `view_page_api` VALUES (44, 17, 82);
INSERT INTO `view_page_api` VALUES (45, 17, 81);
INSERT INTO `view_page_api` VALUES (46, 17, 87);
INSERT INTO `view_page_api` VALUES (48, 15, 34);
COMMIT;

-- ----------------------------
-- Table structure for view_page_category
-- ----------------------------
DROP TABLE IF EXISTS `view_page_category`;
CREATE TABLE `view_page_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级分类 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='视图页面分类表';

-- ----------------------------
-- Records of view_page_category
-- ----------------------------
BEGIN;
INSERT INTO `view_page_category` VALUES (1, '系统管理', NULL, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page_category` VALUES (2, '系统配置', 1, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page_category` VALUES (3, '用户配置', 1, 0, '', '2018-07-01 18:52:40', '2018-07-01 18:52:40');
INSERT INTO `view_page_category` VALUES (4, '电子邮箱', 2, 0, '', '2018-07-08 13:12:23', '2018-07-08 13:12:23');
COMMIT;

-- ----------------------------
-- Table structure for view_page_component
-- ----------------------------
DROP TABLE IF EXISTS `view_page_component`;
CREATE TABLE `view_page_component` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `page_component_type` tinyint(2) NOT NULL COMMENT '组件类型 id',
  `name` varchar(255) NOT NULL COMMENT '组件名称',
  `authority` varchar(191) NOT NULL COMMENT '权限（authority）',
  `page_id` bigint(20) unsigned NOT NULL COMMENT '视图页面 id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_authority` (`authority`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='视图页面组件表';

-- ----------------------------
-- Records of view_page_component
-- ----------------------------
BEGIN;
INSERT INTO `view_page_component` VALUES (1, 1, '新增', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_CATEGORY_ADD_DIALOG', 6, 0, '新增视图页面分类', '2018-07-01 19:14:28', '2018-07-01 19:14:28');
INSERT INTO `view_page_component` VALUES (2, 1, '编辑', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_CATEGORY_EDIT_DIALOG', 6, 0, '编辑视图页面分类', '2018-07-01 19:17:39', '2018-07-01 19:17:39');
INSERT INTO `view_page_component` VALUES (3, 1, '删除', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_CATEGORY_DELETE_ALL', 6, 0, '删除视图页面分类', '2018-07-01 19:18:11', '2018-07-01 19:18:11');
INSERT INTO `view_page_component` VALUES (4, 1, '新增', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_ADD_DIALOG', 6, 0, '新增视图页面', '2018-07-01 19:19:40', '2018-07-01 19:19:40');
INSERT INTO `view_page_component` VALUES (5, 1, '复制新增', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COPY_ADD_DIALOG', 6, 0, '复制新增视图页面', '2018-07-01 19:20:44', '2018-07-01 19:20:44');
INSERT INTO `view_page_component` VALUES (6, 1, '编辑', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_EDIT_DIALOG', 6, 0, '编辑视图页面', '2018-07-01 19:30:34', '2018-07-01 19:30:34');
INSERT INTO `view_page_component` VALUES (7, 1, '删除', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_DELETE_ALL', 6, 0, '删除视图页面', '2018-07-01 19:30:56', '2018-07-01 19:30:56');
INSERT INTO `view_page_component` VALUES (8, 1, '关联 API', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_API_DIALOG', 6, 0, '视图页面关联 API', '2018-07-01 19:32:27', '2018-07-01 19:32:27');
INSERT INTO `view_page_component` VALUES (9, 1, '新增', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COMPONENT_ADD_DIALOG', 6, 0, '新增视图页面组件', '2018-07-01 19:33:06', '2018-07-01 19:33:06');
INSERT INTO `view_page_component` VALUES (10, 1, '复制新增', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COMPONENT_COPY_ADD_DIALOG', 6, 0, '复制新增视图页面组件', '2018-07-01 19:33:42', '2018-07-01 19:33:42');
INSERT INTO `view_page_component` VALUES (11, 1, '编辑', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COMPONENT_EDIT_DIALOG', 6, 0, '编辑视图页面组件', '2018-07-01 19:34:07', '2018-07-01 19:34:07');
INSERT INTO `view_page_component` VALUES (12, 1, '删除', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COMPONENT_DELETE_ALL', 6, 0, '删除视图页面组件', '2018-07-01 19:35:50', '2018-07-01 19:35:50');
INSERT INTO `view_page_component` VALUES (13, 1, '关联 API', '/COMPONENT/SYSTEM/VIEW_PAGE/PAGE_ALL/VIEW_PAGE_COMPONENT_API_DIALOG', 6, 0, '视图页面组件关联 API', '2018-07-01 19:36:27', '2018-07-01 19:36:27');
INSERT INTO `view_page_component` VALUES (14, 1, '关联', '/COMPONENT/SYSTEM/VIEW_PAGE_API/PAGE_ALL/ASSOCIATE_ALL_VIEW_PAGE_API', 7, 0, '', '2018-07-03 12:59:22', '2018-07-03 12:57:37');
INSERT INTO `view_page_component` VALUES (15, 1, '撤销关联', '/COMPONENT/SYSTEM/VIEW_PAGE_API/PAGE_ALL/REVOKE_ASSOCIATE_ALL_VIEW_PAGE_API', 7, 0, '', '2018-07-03 12:59:16', '2018-07-03 12:59:16');
INSERT INTO `view_page_component` VALUES (16, 1, '关联', '/COMPONENT/SYSTEM/VIEW_PAGE_COMPONENT_API/PAGE_ALL/ASSOCIATE_ALL_VIEW_PAGE_COMPONENT_API', 8, 0, '', '2018-07-03 13:00:28', '2018-07-03 13:00:28');
INSERT INTO `view_page_component` VALUES (17, 1, '撤销关联', '/COMPONENT/SYSTEM/VIEW_PAGE_COMPONENT_API/PAGE_ALL/REVOKE_ASSOCIATE_ALL_VIEW_PAGE_COMPONENT_API', 8, 0, '', '2018-07-03 13:00:42', '2018-07-03 13:00:42');
INSERT INTO `view_page_component` VALUES (18, 1, '新增', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_CATEGORY_ADD_DIALOG', 9, 0, '新增 API 分类', '2018-07-03 13:07:51', '2018-07-03 13:07:51');
INSERT INTO `view_page_component` VALUES (19, 1, '编辑', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_CATEGORY_EDIT_DIALOG', 9, 0, '编辑 API 分类', '2018-07-03 13:08:17', '2018-07-03 13:08:17');
INSERT INTO `view_page_component` VALUES (20, 1, '删除', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_CATEGORY_DELETE_ALL', 9, 0, '删除 API 分类', '2018-07-03 13:08:36', '2018-07-03 13:08:36');
INSERT INTO `view_page_component` VALUES (21, 1, '新增', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_ADD_DIALOG', 9, 0, '新增 API', '2018-07-03 13:09:21', '2018-07-03 13:09:21');
INSERT INTO `view_page_component` VALUES (22, 1, '复制新增', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_COPY_ADD_DIALOG', 9, 0, '复制新增 API', '2018-07-03 13:09:36', '2018-07-03 13:09:36');
INSERT INTO `view_page_component` VALUES (23, 1, '编辑', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_EDIT_DIALOG', 9, 0, '编辑 API', '2018-07-03 13:09:55', '2018-07-03 13:09:55');
INSERT INTO `view_page_component` VALUES (24, 1, '删除', '/COMPONENT/SYSTEM/API/PAGE_ALL/API_DELETE_ALL', 9, 0, '删除 API', '2018-07-03 13:10:08', '2018-07-03 13:10:08');
INSERT INTO `view_page_component` VALUES (25, 1, '新增', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_CATEGORY_ADD_DIALOG', 10, 0, '新增字典分类', '2018-07-03 15:25:33', '2018-07-03 15:25:33');
INSERT INTO `view_page_component` VALUES (26, 1, '编辑', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_CATEGORY_EDIT_DIALOG', 10, 0, '编辑字典分类', '2018-07-03 15:25:58', '2018-07-03 15:25:58');
INSERT INTO `view_page_component` VALUES (27, 1, '删除', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_CATEGORY_DELETE_ALL', 10, 0, '删除字典分类', '2018-07-03 15:26:18', '2018-07-03 15:26:18');
INSERT INTO `view_page_component` VALUES (28, 1, '新增', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_ADD_DIALOG', 10, 0, '新增字典', '2018-07-03 15:26:37', '2018-07-03 15:26:37');
INSERT INTO `view_page_component` VALUES (29, 1, '复制新增', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_COPY_ADD_DIALOG', 10, 0, '复制新增字典', '2018-07-03 15:26:56', '2018-07-03 15:26:56');
INSERT INTO `view_page_component` VALUES (30, 1, '编辑', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_EDIT_DIALOG', 10, 0, '编辑字典', '2018-07-03 15:27:18', '2018-07-03 15:27:18');
INSERT INTO `view_page_component` VALUES (31, 1, '删除', '/COMPONENT/SYSTEM/DICTIONARY/PAGE_ALL/DICTIONARY_DELETE_ALL', 10, 0, '删除字典', '2018-07-03 15:27:32', '2018-07-03 15:27:32');
INSERT INTO `view_page_component` VALUES (32, 1, '新增', '/COMPONENT/USER/PAGE_ALL/USER_ADD_DIALOG', 12, 0, '', '2018-07-03 20:29:52', '2018-07-03 20:29:52');
INSERT INTO `view_page_component` VALUES (33, 1, '编辑', '/COMPONENT/USER/PAGE_ALL/USER_EDIT_DIALOG', 12, 0, '', '2018-07-03 20:30:18', '2018-07-03 20:30:18');
INSERT INTO `view_page_component` VALUES (34, 1, '删除', '/COMPONENT/USER/PAGE_ALL/USER_SIGN_ALL_GMT_DELETED', 12, 0, '标记 gmt_deleted 不为空，可撤销删除', '2018-07-03 20:32:47', '2018-07-03 20:31:36');
INSERT INTO `view_page_component` VALUES (35, 1, '撤销删除', '/COMPONENT/USER/PAGE_ALL/USER_UNSIGN_ALL_GMT_DELETED', 12, 0, '标记 gmt_deleted 为空，撤销删除', '2018-07-03 20:32:32', '2018-07-03 20:32:32');
INSERT INTO `view_page_component` VALUES (36, 1, '永久删除', '/COMPONENT/USER/PAGE_ALL/USER_DELETE_ALL', 12, 0, '永久删除，不可撤销删除', '2018-07-03 20:33:45', '2018-07-03 20:33:45');
INSERT INTO `view_page_component` VALUES (37, 1, '角色管理', '/COMPONENT/USER/PAGE_ALL/USER_ROLE_DIALOG', 12, 0, '角色管理，可为指定用户新增或删除角色', '2018-07-03 20:35:12', '2018-07-03 20:35:12');
INSERT INTO `view_page_component` VALUES (38, 1, '授权用户角色', '/COMPONENT/USER/USER_ROLE/PAGE_ALL/USER_ROLE_ADD_DIALOG', 16, 0, '', '2018-07-03 20:41:51', '2018-07-03 20:41:51');
INSERT INTO `view_page_component` VALUES (39, 1, '撤销授权用户角色', '/COMPONENT/USER/USER_ROLE/PAGE_ALL/USER_ROLE_DELETE_ALL', 16, 0, '', '2018-07-03 20:42:22', '2018-07-03 20:42:22');
INSERT INTO `view_page_component` VALUES (40, 1, '新增', '/COMPONENT/USER/ROLE/PAGE_ALL/ROLE_ADD_DIALOG', 13, 0, '', '2018-07-03 20:45:22', '2018-07-03 20:45:22');
INSERT INTO `view_page_component` VALUES (41, 1, '编辑', '/COMPONENT/USER/ROLE/PAGE_ALL/ROLE_EDIT_DIALOG', 13, 0, '', '2018-07-03 20:45:53', '2018-07-03 20:45:53');
INSERT INTO `view_page_component` VALUES (42, 1, '删除', '/COMPONENT/USER/ROLE/PAGE_ALL/ROLE_DELETE_ALL', 13, 0, '', '2018-07-03 20:46:24', '2018-07-03 20:46:24');
INSERT INTO `view_page_component` VALUES (43, 1, '视图菜单管理', '/COMPONENT/USER/ROLE/PAGE_ALL/VIEW_MENU_DIALOG', 13, 0, '', '2018-07-03 20:46:58', '2018-07-03 20:46:58');
INSERT INTO `view_page_component` VALUES (44, 1, '视图页面权限管理', '/COMPONENT/USER/ROLE/PAGE_ALL/ROLE_AUTHORITY_VIEW_PAGE_DIALOG', 13, 0, '', '2018-07-03 20:47:35', '2018-07-03 20:47:35');
INSERT INTO `view_page_component` VALUES (45, 1, 'API 权限管理', '/COMPONENT/USER/ROLE/PAGE_ALL/ROLE_AUTHORITY_API_DIALOG', 13, 0, '', '2018-07-03 20:48:00', '2018-07-03 20:48:00');
INSERT INTO `view_page_component` VALUES (46, 1, '授权', '/COMPONENT/USER/ROLE_AUTHORITY/API/PAGE_ALL/GRANT_ALL_ROLE_AUTHORITY_API', 14, 0, '', '2018-07-03 20:51:20', '2018-07-03 20:51:20');
INSERT INTO `view_page_component` VALUES (47, 1, '撤销授权', '/COMPONENT/USER/ROLE_AUTHORITY/API/PAGE_ALL/REVOKE_ALL_ROLE_AUTHORITY_API', 14, 0, '', '2018-07-03 20:51:48', '2018-07-03 20:51:48');
INSERT INTO `view_page_component` VALUES (48, 1, '授权', '/COMPONENT/USER/ROLE_AUTHORITY/VIEW_PAGE/PAGE_ALL/GRANT_ALL_ROLE_AUTHORITY_VIEW_PAGE', 15, 0, '授权视图页面权限（authority）', '2018-07-03 21:31:49', '2018-07-03 20:52:32');
INSERT INTO `view_page_component` VALUES (49, 1, '撤销授权', '/COMPONENT/USER/ROLE_AUTHORITY/VIEW_PAGE/PAGE_ALL/REVOKE_ALL_ROLE_AUTHORITY_VIEW_PAGE', 15, 0, '撤销授权视图页面权限（authority）', '2018-07-03 21:31:57', '2018-07-03 20:52:54');
INSERT INTO `view_page_component` VALUES (50, 1, '新增', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_CATEGORY_ADD_DIALOG', 17, 0, '新增视图菜单分类', '2018-07-03 20:54:19', '2018-07-03 20:54:19');
INSERT INTO `view_page_component` VALUES (51, 1, '编辑', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_CATEGORY_EDIT_DIALOG', 17, 0, '编辑视图菜单分类', '2018-07-03 20:54:44', '2018-07-03 20:54:44');
INSERT INTO `view_page_component` VALUES (52, 1, '删除', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_CATEGORY_DELETE_ALL', 17, 0, '删除视图菜单分类', '2018-07-03 20:55:05', '2018-07-03 20:55:05');
INSERT INTO `view_page_component` VALUES (53, 1, '新增', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_ADD_DIALOG', 17, 0, '新增视图菜单', '2018-07-03 20:55:37', '2018-07-03 20:55:37');
INSERT INTO `view_page_component` VALUES (54, 1, '编辑', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_EDIT_DIALOG', 17, 0, '编辑视图菜单', '2018-07-03 20:55:52', '2018-07-03 20:55:52');
INSERT INTO `view_page_component` VALUES (55, 1, '删除', '/COMPONENT/USER/VIEW_MENU/PAGE_ALL/VIEW_MENU_DELETE_ALL', 17, 0, '删除视图菜单', '2018-07-03 20:56:08', '2018-07-03 20:56:08');
INSERT INTO `view_page_component` VALUES (56, 1, '授权', '/COMPONENT/USER/ROLE_AUTHORITY/VIEW_PAGE/PAGE_ALL/GRANT_ALL_ROLE_AUTHORITY_VIEW_PAGE_COMPONENT', 15, 0, '授权视图页面组件权限（authority）', '2018-07-03 21:32:25', '2018-07-03 21:32:25');
INSERT INTO `view_page_component` VALUES (57, 1, '撤销授权', '/COMPONENT/USER/ROLE_AUTHORITY/VIEW_PAGE/PAGE_ALL/REVOKE_ALL_ROLE_AUTHORITY_VIEW_PAGE_COMPONENT', 15, 0, '撤销授权视图页面组件权限（authority）', '2018-07-03 21:32:48', '2018-07-03 21:32:48');
COMMIT;

-- ----------------------------
-- Table structure for view_page_component_api
-- ----------------------------
DROP TABLE IF EXISTS `view_page_component_api`;
CREATE TABLE `view_page_component_api` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `page_component_id` bigint(20) unsigned NOT NULL COMMENT '视图页面组件 id',
  `api_id` bigint(20) unsigned NOT NULL COMMENT 'API id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COMMENT='视图页面组件关联 API 表';

-- ----------------------------
-- Records of view_page_component_api
-- ----------------------------
BEGIN;
INSERT INTO `view_page_component_api` VALUES (1, 1, 30);
INSERT INTO `view_page_component_api` VALUES (2, 2, 33);
INSERT INTO `view_page_component_api` VALUES (3, 2, 31);
INSERT INTO `view_page_component_api` VALUES (4, 3, 32);
INSERT INTO `view_page_component_api` VALUES (5, 4, 35);
INSERT INTO `view_page_component_api` VALUES (6, 5, 38);
INSERT INTO `view_page_component_api` VALUES (7, 5, 35);
INSERT INTO `view_page_component_api` VALUES (8, 6, 36);
INSERT INTO `view_page_component_api` VALUES (9, 6, 38);
INSERT INTO `view_page_component_api` VALUES (10, 7, 37);
INSERT INTO `view_page_component_api` VALUES (11, 9, 40);
INSERT INTO `view_page_component_api` VALUES (12, 10, 40);
INSERT INTO `view_page_component_api` VALUES (13, 10, 43);
INSERT INTO `view_page_component_api` VALUES (14, 11, 41);
INSERT INTO `view_page_component_api` VALUES (15, 11, 43);
INSERT INTO `view_page_component_api` VALUES (16, 12, 42);
INSERT INTO `view_page_component_api` VALUES (17, 14, 26);
INSERT INTO `view_page_component_api` VALUES (18, 15, 27);
INSERT INTO `view_page_component_api` VALUES (19, 16, 45);
INSERT INTO `view_page_component_api` VALUES (20, 17, 46);
INSERT INTO `view_page_component_api` VALUES (21, 18, 3);
INSERT INTO `view_page_component_api` VALUES (22, 19, 4);
INSERT INTO `view_page_component_api` VALUES (23, 19, 6);
INSERT INTO `view_page_component_api` VALUES (24, 20, 5);
INSERT INTO `view_page_component_api` VALUES (25, 21, 8);
INSERT INTO `view_page_component_api` VALUES (26, 22, 8);
INSERT INTO `view_page_component_api` VALUES (27, 22, 11);
INSERT INTO `view_page_component_api` VALUES (28, 23, 9);
INSERT INTO `view_page_component_api` VALUES (29, 23, 11);
INSERT INTO `view_page_component_api` VALUES (30, 24, 10);
INSERT INTO `view_page_component_api` VALUES (31, 32, 61);
INSERT INTO `view_page_component_api` VALUES (32, 33, 62);
INSERT INTO `view_page_component_api` VALUES (33, 33, 66);
INSERT INTO `view_page_component_api` VALUES (34, 34, 63);
INSERT INTO `view_page_component_api` VALUES (35, 35, 64);
INSERT INTO `view_page_component_api` VALUES (36, 36, 65);
INSERT INTO `view_page_component_api` VALUES (37, 38, 68);
INSERT INTO `view_page_component_api` VALUES (38, 39, 69);
INSERT INTO `view_page_component_api` VALUES (39, 40, 55);
INSERT INTO `view_page_component_api` VALUES (40, 41, 56);
INSERT INTO `view_page_component_api` VALUES (41, 41, 58);
INSERT INTO `view_page_component_api` VALUES (42, 42, 57);
INSERT INTO `view_page_component_api` VALUES (43, 46, 88);
INSERT INTO `view_page_component_api` VALUES (44, 47, 89);
INSERT INTO `view_page_component_api` VALUES (45, 48, 47);
INSERT INTO `view_page_component_api` VALUES (46, 49, 48);
INSERT INTO `view_page_component_api` VALUES (47, 56, 49);
INSERT INTO `view_page_component_api` VALUES (48, 57, 50);
INSERT INTO `view_page_component_api` VALUES (49, 50, 72);
INSERT INTO `view_page_component_api` VALUES (50, 51, 73);
INSERT INTO `view_page_component_api` VALUES (51, 51, 75);
INSERT INTO `view_page_component_api` VALUES (52, 52, 74);
INSERT INTO `view_page_component_api` VALUES (53, 53, 83);
INSERT INTO `view_page_component_api` VALUES (54, 54, 84);
INSERT INTO `view_page_component_api` VALUES (55, 54, 86);
INSERT INTO `view_page_component_api` VALUES (56, 55, 85);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
