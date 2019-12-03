layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 平台流水表管理
     */
    var QxPlatformLog = {
        tableId: "qxPlatformLogTable"
    };

    /**
     * 初始化表格的列
     */
    QxPlatformLog.initColumn = function () {
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
            {field: 'amount', sort: true, title: '金额'},
            {field: 'type', sort: true, title: '流水类型：0-充值购买金币汇入；1-金币提现支出'},
            {field: 'payWay', sort: true, title: '支付方式 0-支付宝；1-微信'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxPlatformLog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxPlatformLog.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxPlatformLog.openAddDlg = function () {
        func.open({
            title: '添加平台流水表',
            content: Feng.ctxPath + '/qxPlatformLog/add',
            tableId: QxPlatformLog.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxPlatformLog.openEditDlg = function (data) {
        func.open({
            title: '修改平台流水表',
            content: Feng.ctxPath + '/qxPlatformLog/edit?id=' + data.id,
            tableId: QxPlatformLog.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxPlatformLog.exportExcel = function () {
        var checkRows = table.checkStatus(QxPlatformLog.tableId);
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
    QxPlatformLog.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxPlatformLog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxPlatformLog.tableId);
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
        elem: '#' + QxPlatformLog.tableId,
        url: Feng.ctxPath + '/qxPlatformLog/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxPlatformLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxPlatformLog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxPlatformLog.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxPlatformLog.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxPlatformLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxPlatformLog.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxPlatformLog.onDeleteItem(data);
        }
    });
});
