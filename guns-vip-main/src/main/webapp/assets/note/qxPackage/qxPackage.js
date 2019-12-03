layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 充值套餐管理
     */
    var QxPackage = {
        tableId: "qxPackageTable"
    };

    /**
     * 初始化表格的列
     */
    QxPackage.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'amount', sort: true, title: '实际金额'},
            {field: 'coins', sort: true, title: '金币个数'},
            {field: 'orderNo', sort: true, title: '排序'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxPackage.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxPackage.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxPackage.openAddDlg = function () {
        func.open({
            title: '添加充值套餐',
            content: Feng.ctxPath + '/qxPackage/add',
            tableId: QxPackage.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxPackage.openEditDlg = function (data) {
        func.open({
            title: '修改充值套餐',
            content: Feng.ctxPath + '/qxPackage/edit?id=' + data.id,
            tableId: QxPackage.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxPackage.exportExcel = function () {
        var checkRows = table.checkStatus(QxPackage.tableId);
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
    QxPackage.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxPackage/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxPackage.tableId);
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
        elem: '#' + QxPackage.tableId,
        url: Feng.ctxPath + '/qxPackage/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxPackage.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxPackage.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxPackage.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxPackage.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxPackage.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxPackage.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxPackage.onDeleteItem(data);
        }
    });
});
