layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 系统通知表管理
     */
    var QxNotice = {
        tableId: "qxNoticeTable"
    };

    /**
     * 初始化表格的列
     */
    QxNotice.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'account', sort: true, title: '用户账号'},
            {field: 'content', sort: true, title: '通知内容'},
            {field: 'tag', sort: true, title: '消息分类 消息分类'},
            {field: 'type', sort: true, title: '通知类型 通知类型：0-短信，1-邮箱， 2-推送'},
            {field: 'statusSend', sort: true, title: '发送状态 是否发送：0-未发送，1-已发送'},
            {field: 'statusRead', sort: true, title: '已读状态 读取状态：1-是.0-未读'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxNotice.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxNotice.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxNotice.openAddDlg = function () {
        func.open({
            title: '添加系统通知表',
            content: Feng.ctxPath + '/qxNotice/add',
            tableId: QxNotice.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxNotice.openEditDlg = function (data) {
        func.open({
            title: '修改系统通知表',
            content: Feng.ctxPath + '/qxNotice/edit?id=' + data.id,
            tableId: QxNotice.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxNotice.exportExcel = function () {
        var checkRows = table.checkStatus(QxNotice.tableId);
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
    QxNotice.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxNotice/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxNotice.tableId);
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
        elem: '#' + QxNotice.tableId,
        url: Feng.ctxPath + '/qxNotice/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxNotice.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxNotice.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxNotice.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxNotice.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxNotice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxNotice.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxNotice.onDeleteItem(data);
        }
    });
});
