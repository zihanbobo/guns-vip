layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 约单表管理
     */
    var QxInvite = {
        tableId: "qxInviteTable"
    };

    /**
     * 初始化表格的列
     */
    QxInvite.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'sn', sort: true, title: '约单单号', width: 320},
            {field: 'inviteTime', sort: true, width: 160, title: '邀请时间'},
            {field: 'location', sort: false, title: '地点', templet: function(d) {
            	return d.province + d.city + d.district + d.street + d.streetNumber;
            }},
            {field: 'content', sort: true, title: '约单描述'},
            {field: 'status', sort: true, title: '状态', width: 80, templet: function(d) {
            	// 0-待配对;1-已配对;2-进行中;3-已完成;4-已取消;5-已投诉
            	if (d.status == '0') {
            		return '待配对';
            	} else if (d.status == '1') {
            		return '已配对';
            	} else if (d.status == '2') {
            		return '进行中';
            	} else if (d.status == '3') {
            		return '已完成';
            	} else if (d.status == '4') {
            		return '已取消';
            	} else if (d.status == '5') {
            		return '已投诉';
            	} else {
            		return '';
            	}
            }},
            {align: 'center', toolbar: '#tableBar', width: 160, title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxInvite.search = function () {
        var queryData = {};
        queryData['sn'] = $("#sn").val();
        table.reload(QxInvite.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxInvite.openAddDlg = function () {
        func.open({
            title: '添加约单表',
            content: Feng.ctxPath + '/qxInvite/add',
            tableId: QxInvite.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxInvite.openEditDlg = function (data) {
        func.open({
            title: '修改约单表',
            content: Feng.ctxPath + '/qxInvite/edit?id=' + data.id,
            tableId: QxInvite.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxInvite.exportExcel = function () {
        var checkRows = table.checkStatus(QxInvite.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    QxInvite.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxInvite/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxInvite.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + QxInvite.tableId,
        url: Feng.ctxPath + '/qxInvite/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxInvite.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxInvite.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxInvite.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxInvite.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxInvite.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxInvite.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxInvite.onDeleteItem(data);
        }
    });
});
