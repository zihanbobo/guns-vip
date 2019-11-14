layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 消息免打扰表管理
     */
    var QxNotDisturb = {
        tableId: "qxNotDisturbTable"
    };

    /**
     * 初始化表格的列
     */
    QxNotDisturb.initColumn = function () {
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
            {field: 'disturbUserId', sort: true, title: '免打扰用户ID'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxNotDisturb.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxNotDisturb.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxNotDisturb.openAddDlg = function () {
        func.open({
            title: '添加消息免打扰表',
            content: Feng.ctxPath + '/qxNotDisturb/add',
            tableId: QxNotDisturb.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxNotDisturb.openEditDlg = function (data) {
        func.open({
            title: '修改消息免打扰表',
            content: Feng.ctxPath + '/qxNotDisturb/edit?id=' + data.id,
            tableId: QxNotDisturb.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxNotDisturb.exportExcel = function () {
        var checkRows = table.checkStatus(QxNotDisturb.tableId);
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
    QxNotDisturb.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxNotDisturb/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxNotDisturb.tableId);
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
        elem: '#' + QxNotDisturb.tableId,
        url: Feng.ctxPath + '/qxNotDisturb/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxNotDisturb.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxNotDisturb.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxNotDisturb.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxNotDisturb.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxNotDisturb.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxNotDisturb.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxNotDisturb.onDeleteItem(data);
        }
    });
});
