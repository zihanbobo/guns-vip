layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 费用比例表管理
     */
    var QxCostRate = {
        tableId: "qxCostRateTable"
    };

    /**
     * 初始化表格的列
     */
    QxCostRate.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'rate', sort: true, title: '比例'},
            {field: 'type', sort: true, title: '类型 类型：0-金币兑换比例;1-提现手续费'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxCostRate.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxCostRate.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxCostRate.openAddDlg = function () {
        func.open({
            title: '添加费用比例表',
            content: Feng.ctxPath + '/qxCostRate/add',
            tableId: QxCostRate.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxCostRate.openEditDlg = function (data) {
        func.open({
            title: '修改费用比例表',
            content: Feng.ctxPath + '/qxCostRate/edit?id=' + data.id,
            tableId: QxCostRate.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxCostRate.exportExcel = function () {
        var checkRows = table.checkStatus(QxCostRate.tableId);
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
    QxCostRate.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxCostRate/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxCostRate.tableId);
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
        elem: '#' + QxCostRate.tableId,
        url: Feng.ctxPath + '/qxCostRate/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxCostRate.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxCostRate.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxCostRate.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxCostRate.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxCostRate.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxCostRate.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxCostRate.onDeleteItem(data);
        }
    });
});
