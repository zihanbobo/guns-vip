layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 私密日记评论表管理
     */
    var QxNoteComment = {
        tableId: "qxNoteCommentTable"
    };

    /**
     * 初始化表格的列
     */
    QxNoteComment.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'noteId', sort: true, title: '推文ID'},
            {field: 'content', sort: true, title: '评论内容'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxNoteComment.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxNoteComment.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxNoteComment.openAddDlg = function () {
        func.open({
            title: '添加私密日记评论表',
            content: Feng.ctxPath + '/qxNoteComment/add',
            tableId: QxNoteComment.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxNoteComment.openEditDlg = function (data) {
        func.open({
            title: '修改私密日记评论表',
            content: Feng.ctxPath + '/qxNoteComment/edit?id=' + data.id,
            tableId: QxNoteComment.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxNoteComment.exportExcel = function () {
        var checkRows = table.checkStatus(QxNoteComment.tableId);
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
    QxNoteComment.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxNoteComment/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxNoteComment.tableId);
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
        elem: '#' + QxNoteComment.tableId,
        url: Feng.ctxPath + '/qxNoteComment/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxNoteComment.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxNoteComment.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxNoteComment.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxNoteComment.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxNoteComment.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxNoteComment.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxNoteComment.onDeleteItem(data);
        }
    });
});
