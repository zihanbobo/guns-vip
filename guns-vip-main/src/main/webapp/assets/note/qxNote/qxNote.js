layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 私密日记管理
     */
    var QxNote = {
        tableId: "qxNoteTable"
    };

    /**
     * 初始化表格的列
     */
    QxNote.initColumn = function () {
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
            {field: 'images', sort: true, title: '图片 图片地址1;图片地址2;图片地址3'},
            {field: 'title', sort: true, title: '推文标题'},
            {field: 'content', sort: true, title: '推文内容'},
            {field: 'canComment', sort: true, title: '是否允许评价'},
            {field: 'favoriteCount', sort: true, title: '点赞数'},
            {field: 'commentCount', sort: true, title: '评论数'},
            {field: 'giftCount', sort: true, title: '已赠送礼物数'},
            {field: 'isPrivate', sort: true, title: '是否加密'},
            {field: 'giftId', sort: true, title: '打开条件 解锁需付出的礼物'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxNote.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxNote.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxNote.openAddDlg = function () {
        func.open({
            title: '添加私密日记',
            content: Feng.ctxPath + '/qxNote/add',
            tableId: QxNote.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxNote.openEditDlg = function (data) {
        func.open({
            title: '修改私密日记',
            content: Feng.ctxPath + '/qxNote/edit?id=' + data.id,
            tableId: QxNote.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxNote.exportExcel = function () {
        var checkRows = table.checkStatus(QxNote.tableId);
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
    QxNote.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxNote/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxNote.tableId);
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
        elem: '#' + QxNote.tableId,
        url: Feng.ctxPath + '/qxNote/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxNote.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxNote.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxNote.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxNote.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxNote.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxNote.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxNote.onDeleteItem(data);
        }
    });
});
