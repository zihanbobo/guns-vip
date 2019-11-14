layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 黑名单表管理
     */
    var QxBlacklist = {
        tableId: "qxBlacklistTable"
    };

    /**
     * 初始化表格的列
     */
    QxBlacklist.initColumn = function () {
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
            {field: 'blackUserId', sort: true, title: '黑名单用户ID'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxBlacklist.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxBlacklist.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxBlacklist.openAddDlg = function () {
        func.open({
            title: '添加黑名单表',
            content: Feng.ctxPath + '/qxBlacklist/add',
            tableId: QxBlacklist.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxBlacklist.openEditDlg = function (data) {
        func.open({
            title: '修改黑名单表',
            content: Feng.ctxPath + '/qxBlacklist/edit?id=' + data.id,
            tableId: QxBlacklist.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxBlacklist.exportExcel = function () {
        var checkRows = table.checkStatus(QxBlacklist.tableId);
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
    QxBlacklist.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxBlacklist/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxBlacklist.tableId);
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
        elem: '#' + QxBlacklist.tableId,
        url: Feng.ctxPath + '/qxBlacklist/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxBlacklist.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxBlacklist.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxBlacklist.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxBlacklist.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxBlacklist.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxBlacklist.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxBlacklist.onDeleteItem(data);
        }
    });
});
