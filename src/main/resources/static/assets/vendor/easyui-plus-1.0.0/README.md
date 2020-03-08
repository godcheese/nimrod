# easyui plus
> 基于 EasyUI 的 Javascript 框架，可快速简单的搭建企业级应用的 UI，已兼容更新 EasyUI 至最新版（v1.6.3）

## 更新日志
[CHANGELOG.md](/CHANGELOG.md)

## 引用示例

```
<link rel="stylesheet" type="text/css" th:href="/vendor/font-awesome-4.7.0/css/font-awesome.css">
<link rel="stylesheet" type="text/css" th:href="/vendor/jquery-easyui-1.6.3/themes/icon.css">
<link rel="stylesheet" type="text/css" th:href="/vendor/jquery-easyui-1.6.3/themes/color.css">
<link rel="stylesheet" type="text/css" th:href="@{/vendor/jquery-easyui-1.6.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" th:href="/vendor/expressui-1.0.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" th:href="/vendor/expressui-1.0.0/css/expressui.css">

<script type="text/javascript" th:src="/vendor/jquery/jquery-1.11.3.js" charset="UTF-8"></script>
<script type="text/javascript" th:src="/vendor/jquery-easyui-1.6.3/jquery.easyui.min.js" charset="UTF-8"></script>
<script type="text/javascript" th:src="/vendor/jquery-easyui-1.6.3/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" th:src="@{/vendor/expressui-1.0.0/js/jquery.expressui.js}" charset="UTF-8"></script>

```

## 使用示例
### dialog

- 新增
```
            $('#dialogSelector').dialog(expressui.dialog.create, {
                title: 'dialog demo',
                grid: {type: 'treegrid', selector: '#treegridSelector'},
                href: '/dialog_demo/add_dialog',
                save: {
                    url: '/dialog_demo/add_one',
                    data: something ? something : {}, // 非必传参数
                    method: 'post'
                },
                buttons: [{
                    text: '保存',
                    iconCls: 'iconfont icon-save',
                    handler: expressui.dialog.add,
                    reload: [{type: 'treegrid', selector: '#treegridSelector'}, {type: 'datagrid', selector: '#datagridSelector', data: []}],
                    success: '新增成功'
                }, {
                    text: '关闭',
                    iconCls: 'iconfont icon-close',
                    handler: expressui.dialog.close
                }]
            });

```