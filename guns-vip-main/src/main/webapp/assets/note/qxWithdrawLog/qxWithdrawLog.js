layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 平台提现表管理
     */
    var QxWithdrawLog = {
        tableId: "qxWithdrawLogTable"
    };

    /**
     * 初始化表格的列
     */
    QxWithdrawLog.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'sn', sort: true, title: '订单号'},
            {field: 'userId', sort: true, title: '用户ID'},
            {field: 'amount', sort: true, title: '金额'},
            {field: 'payWay', sort: true, title: '提现方式 0-支付宝；1-微信'},
            {field: 'payeeAccount', sort: true, title: '收款账号 支付宝账号；微信uuid;'},
            {field: 'status', sort: true, title: '状态 0-已申请；1-已提现;2-已取消'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxWithdrawLog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxWithdrawLog.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxWithdrawLog.openAddDlg = function () {
        func.open({
            title: '添加平台提现表',
            content: Feng.ctxPath + '/qxWithdrawLog/add',
            tableId: QxWithdrawLog.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxWithdrawLog.openEditDlg = function (data) {
        func.open({
            title: '修改平台提现表',
            content: Feng.ctxPath + '/qxWithdrawLog/edit?id=' + data.id,
            tableId: QxWithdrawLog.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxWithdrawLog.exportExcel = function () {
        var checkRows = table.checkStatus(QxWithdrawLog.tableId);
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
    QxWithdrawLog.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxWithdrawLog/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxWithdrawLog.tableId);
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
        elem: '#' + QxWithdrawLog.tableId,
        url: Feng.ctxPath + '/qxWithdrawLog/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxWithdrawLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxWithdrawLog.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxWithdrawLog.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxWithdrawLog.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxWithdrawLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxWithdrawLog.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxWithdrawLog.onDeleteItem(data);
        }
    });
});
