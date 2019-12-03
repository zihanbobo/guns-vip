layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 用户表管理
     */
    var QxUser = {
        tableId: "qxUserTable"
    };

    /**
     * 初始化表格的列
     */
    QxUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'mobile', sort: true, title: '手机号'},
            {field: 'nickname', sort: true, title: '昵称'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'height', sort: true, title: '身高'},
            {field: 'sex', sort: true, title: '性别 0-男;1-女'},
            {field: 'avatar', sort: true, title: '头像'},
            {field: 'status', sort: true, title: '状态 0-正常；1-禁用'},
            {field: 'score', sort: true, title: '信用分'},
            {field: 'balance', sort: true, title: '金币余额（可提现）'},
            {field: 'freeze', sort: true, title: '冻结余额（不可提现）'},
            {field: 'inviteCode', sort: true, title: '我的邀请码'},
            {field: 'parentId', sort: true, title: '邀请人ID'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxUser.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxUser.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxUser.openAddDlg = function () {
        func.open({
            title: '添加用户表',
            content: Feng.ctxPath + '/qxUser/add',
            tableId: QxUser.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxUser.openEditDlg = function (data) {
        func.open({
            title: '修改用户表',
            content: Feng.ctxPath + '/qxUser/edit?id=' + data.id,
            tableId: QxUser.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxUser.exportExcel = function () {
        var checkRows = table.checkStatus(QxUser.tableId);
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
    QxUser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxUser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxUser.tableId);
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
        elem: '#' + QxUser.tableId,
        url: Feng.ctxPath + '/qxUser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxUser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxUser.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxUser.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxUser.onDeleteItem(data);
        }
    });
});
