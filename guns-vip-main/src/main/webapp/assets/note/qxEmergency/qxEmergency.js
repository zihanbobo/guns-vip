layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 紧急联系人管理
     */
    var QxEmergency = {
        tableId: "qxEmergencyTable"
    };

    /**
     * 初始化表格的列
     */
    QxEmergency.initColumn = function () {
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
            {field: 'name', sort: true, title: '联系人名称'},
            {field: 'contact', sort: true, title: '联系方式'},
            {field: 'isDefault', sort: true, title: '是否默认'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxEmergency.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxEmergency.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxEmergency.openAddDlg = function () {
        func.open({
            title: '添加紧急联系人',
            content: Feng.ctxPath + '/qxEmergency/add',
            tableId: QxEmergency.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxEmergency.openEditDlg = function (data) {
        func.open({
            title: '修改紧急联系人',
            content: Feng.ctxPath + '/qxEmergency/edit?id=' + data.id,
            tableId: QxEmergency.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxEmergency.exportExcel = function () {
        var checkRows = table.checkStatus(QxEmergency.tableId);
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
    QxEmergency.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxEmergency/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxEmergency.tableId);
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
        elem: '#' + QxEmergency.tableId,
        url: Feng.ctxPath + '/qxEmergency/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxEmergency.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxEmergency.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxEmergency.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxEmergency.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxEmergency.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxEmergency.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxEmergency.onDeleteItem(data);
        }
    });
});
