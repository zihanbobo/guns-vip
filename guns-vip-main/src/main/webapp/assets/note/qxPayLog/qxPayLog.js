layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 用户支付流水表管理
     */
    var QxPayLog = {
        tableId: "qxPayLogTable"
    };

    /**
     * 初始化表格的列
     */
    QxPayLog.initColumn = function () {
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
            {field: 'type', sort: true, title: '付费类型 0-约单支出；1-约单汇入；2-打赏支出；3-打赏汇入；4-兑换商品支出；5-购买礼物支出；6-付费日记支出；7-付费日记汇入；8-违约金支出；9-违约金汇入；'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxPayLog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxPayLog.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxPayLog.openAddDlg = function () {
        func.open({
            title: '添加用户支付流水表',
            content: Feng.ctxPath + '/qxPayLog/add',
            tableId: QxPayLog.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxPayLog.openEditDlg = function (data) {
        func.open({
            title: '修改用户支付流水表',
            content: Feng.ctxPath + '/qxPayLog/edit?id=' + data.id,
            tableId: QxPayLog.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxPayLog.exportExcel = function () {
        var checkRows = table.checkStatus(QxPayLog.tableId);
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
    QxPayLog.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxPayLog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxPayLog.tableId);
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
        elem: '#' + QxPayLog.tableId,
        url: Feng.ctxPath + '/qxPayLog/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxPayLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxPayLog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxPayLog.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxPayLog.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxPayLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxPayLog.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxPayLog.onDeleteItem(data);
        }
    });
});
