layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 商务合作管理
     */
    var QxCooperate = {
        tableId: "qxCooperateTable"
    };

    /**
     * 初始化表格的列
     */
    QxCooperate.initColumn = function () {
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
            {field: 'company', sort: true, title: '公司名称'},
            {field: 'name', sort: true, title: '联系人'},
            {field: 'contact', sort: true, title: '联系方式'},
            {field: 'content', sort: true, title: '合作详情'},
            {field: 'status', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxCooperate.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxCooperate.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxCooperate.openAddDlg = function () {
        func.open({
            title: '添加商务合作',
            content: Feng.ctxPath + '/qxCooperate/add',
            tableId: QxCooperate.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxCooperate.openEditDlg = function (data) {
        func.open({
            title: '修改商务合作',
            content: Feng.ctxPath + '/qxCooperate/edit?id=' + data.id,
            tableId: QxCooperate.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxCooperate.exportExcel = function () {
        var checkRows = table.checkStatus(QxCooperate.tableId);
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
    QxCooperate.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxCooperate/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxCooperate.tableId);
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
        elem: '#' + QxCooperate.tableId,
        url: Feng.ctxPath + '/qxCooperate/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxCooperate.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxCooperate.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxCooperate.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxCooperate.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxCooperate.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxCooperate.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxCooperate.onDeleteItem(data);
        }
    });
});
