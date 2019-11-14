layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 系统通知表管理
     */
    var QxNotification = {
        tableId: "qxNotificationTable"
    };

    /**
     * 初始化表格的列
     */
    QxNotification.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'userId', sort: true, title: '用户ID'},
            {field: 'content', sort: true, title: '通知内容'},
            {field: 'readed', sort: true, title: '已读'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxNotification.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxNotification.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxNotification.openAddDlg = function () {
        func.open({
            title: '添加系统通知表',
            content: Feng.ctxPath + '/qxNotification/add',
            tableId: QxNotification.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxNotification.openEditDlg = function (data) {
        func.open({
            title: '修改系统通知表',
            content: Feng.ctxPath + '/qxNotification/edit?id=' + data.id,
            tableId: QxNotification.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxNotification.exportExcel = function () {
        var checkRows = table.checkStatus(QxNotification.tableId);
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
    QxNotification.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxNotification/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxNotification.tableId);
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
        elem: '#' + QxNotification.tableId,
        url: Feng.ctxPath + '/qxNotification/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxNotification.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxNotification.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxNotification.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxNotification.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxNotification.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxNotification.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxNotification.onDeleteItem(data);
        }
    });
});
