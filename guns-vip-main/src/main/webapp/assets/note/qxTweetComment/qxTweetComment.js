layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 推文评论表管理
     */
    var QxTweetComment = {
        tableId: "qxTweetCommentTable"
    };

    /**
     * 初始化表格的列
     */
    QxTweetComment.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '标识'},
            {field: 'version', sort: true, title: '乐观锁'},
            {field: 'createdBy', sort: true, title: '创建人'},
            {field: 'createdTime', sort: true, title: '创建时间'},
            {field: 'updatedBy', sort: true, title: '更新人'},
            {field: 'updatedTime', sort: true, title: '更新时间'},
            {field: 'deleted', sort: true, title: '删除标识'},
            {field: 'tweetId', sort: true, title: '推文ID'},
            {field: 'content', sort: true, title: '评论内容'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    QxTweetComment.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(QxTweetComment.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    QxTweetComment.openAddDlg = function () {
        func.open({
            title: '添加推文评论表',
            content: Feng.ctxPath + '/qxTweetComment/add',
            tableId: QxTweetComment.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    QxTweetComment.openEditDlg = function (data) {
        func.open({
            title: '修改推文评论表',
            content: Feng.ctxPath + '/qxTweetComment/edit?id=' + data.id,
            tableId: QxTweetComment.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    QxTweetComment.exportExcel = function () {
        var checkRows = table.checkStatus(QxTweetComment.tableId);
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
    QxTweetComment.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/qxTweetComment/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(QxTweetComment.tableId);
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
        elem: '#' + QxTweetComment.tableId,
        url: Feng.ctxPath + '/qxTweetComment/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: QxTweetComment.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        QxTweetComment.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        QxTweetComment.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        QxTweetComment.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + QxTweetComment.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            QxTweetComment.openEditDlg(data);
        } else if (layEvent === 'delete') {
            QxTweetComment.onDeleteItem(data);
        }
    });
});
