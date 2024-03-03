<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>[(${uriName})]</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
    <link rel="stylesheet" th:href="@{/css/jquery-ui-timepicker-addon.min.css}">
    <link rel="stylesheet" th:href="@{/jqueryui/jquery-ui.min.css}">
    <link rel="stylesheet" th:href="@{/css/crud.css}">

    <script th:src="@{/js/jquery-3.5.1.min.js}" ></script>
    <script th:src="@{/jqueryui/jquery-ui.min.js}"></script>
    <script th:src="@{/js/jquery-ui-timepicker-addon.min.js}"></script>
    <script th:src="@{/js/jquery-ui-timepicker-addon-i18n.min.js}"></script>
    <script th:src="@{/js/jquery.validate.min.js}"></script>
    <!--<script th:src="@{/js/localization/messages_zh.js}" type="text/javascript"></script>-->
</head>
<body>
<div th:replace="~{fragments/header::header}"></div>

<div class="content-root">

    <form id="queryform" th:action="@{/api/[(${moduleName})]/[(${uriName})]/list}" method="POST" th:object="${[(${entityKey})]Model.[(${reqKey})]}">
        <div class="query-container">
            <span>Keyword:</span>
            <span>
                <input type="text" name="key" th:value="*{key}"><br/>
            </span>
            <span>
                <input type="submit" value="Query"/>
            </span>
            <span>
                <input type="hidden" name="createaction" th:value="@{/api/[(${moduleName})]/[(${uriName})]/form}">
                <input type="button" name="createbutton" value="Create" />
            </span>
        </div>
    </form>

    <div class="list-container">
        <form id="listform">
            <div class="list">
                <table class="list-table" border="1" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <td width="120px">Operations</td>
                        <td>ID</td>
                    [# th:each="attr,attrStat:${entityAttrs}" ]
                        <td>[(${attr.get('name')})]</td>
                    [/]
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:if="${[(${entityKey})]Model.page.list.size()} eq 0">
                        <td th:colspan="[(${entityAttrs.size} + 2)]">No data!</td>
                    </tr>
                    <tr th:each="[(${dtoKey})]:${[(${entityKey})]Model.page.list}">
                        <td style="white-space: nowrap;">
                            <a th:href="@{'/api/[(${moduleName})]/[(${uriName})]/'+${[(${dtoKey})].id}}" title="View Detail">[V]</a>&nbsp;
                            <a th:href="@{'/api/[(${moduleName})]/[(${uriName})]/remove/'+${[(${dtoKey})].id}}" title="Delete">[D]</a>&nbsp;
                            <a th:href="@{'/api/[(${moduleName})]/[(${uriName})]/modify/'+${[(${dtoKey})].id}}" title="Modify">[M]</a>
                        </td>
                        <td th:text="${[(${dtoKey})].id}"></td>
                        [# th:each="attr,attrStat:${entityAttrs}" ]
                            <td th:text="${[(${dtoKey})].[(${attr.get('name')})]}"></td>
                        [/]

                    </tr>
                    </tbody>
                </table>
            </div>
        </form>

        <form id="navigatorform" th:action="@{/api/[(${moduleName})]/[(${uriName})]/list}" method="POST" th:object="${[(${entityKey})]Model.[(${reqKey})]}">
            <div class="list-navigator">
                <input id="navigatorKey" type="hidden" name="key" value="" />
                <span th:text="'Total Pages (' + ${[(${entityKey})]Model.page.pages} + ')'"></span>&nbsp;&nbsp;
                <input type="hidden" name="pages" th:value="${[(${entityKey})]Model.page.pages}" />&nbsp;
                <span id="navigateToFirstPage" th:class="${[(${entityKey})]Model.page.isFirst}? 'disabled':'enabled'">&lt;&lt;</span>&nbsp;
                <span id="navigateToPreviousPage" th:class="${[(${entityKey})]Model.page.isFirst}? 'disabled':'enabled'">&lt;</span>&nbsp;
                <input type="number" name="current" style="width: 50px;" th:value="*{current}" min="1" th:max="${[(${entityKey})]Model.page.pages}"/>&nbsp;
                <span id="navigateGoto">Go</span>&nbsp;
                <span id="navigateToNextPage" th:class="${[(${entityKey})]Model.page.isLast}? 'disabled':'enabled'">&gt;</span>&nbsp;
                <span id="navigateToLastPage" th:class="${[(${entityKey})]Model.page.isLast}? 'disabled':'enabled'">&gt;&gt;</span>
            </div>
        </form>
        <!--list-container div end -->
    </div>
    <!--content-root div end -->
</div>

<script th:inline="javascript">
/*<![CDATA[*/
$(function() {
    $("form").submit(function(e) {
        var docHeight = $(document).height();
        $('body').append('<div id="selft-widow-shadow"></div>');
        $('#selft-widow-shadow')
        .height(docHeight)
        .css({
        'opacity': .4,
        'position': 'absolute',
        'top': 0,
        'left': 0,
        'background-color': 'black',
        'width': '100%',
        'z-index': 9003
        });
        $('#selft-widow-shadow').focus();
        setTimeout(function(){$("#selft-widow-shadow").remove();}, 1000);
    });

    $("#queryform input[name='key']").keyup(function () {
        if (event.keyCode == 13) {
            this.blur();
        }
    });

    $("#queryform input[name='key']").blur(function () {
        $("#queryform").submit();
    });

    $("input[name='createbutton']").click(function () {
        window.location.href = encodeURI($("input[name='createaction']").val());
    });


    /* ------------Page Navigation Begin -------------*/
    $("#navigateToFirstPage").click(function () {
        var cur = $("#navigatorform input[name='current']");
        if (parseInt(cur.val()) <= 1) {
            return;
        }
        cur.val(1);
        $("#navigatorKey").val($("#queryform input[name='key']").val());
        $("#navigatorform").submit();
    });
    $("#navigateToPreviousPage").click(function () {
        var cur = $("#navigatorform input[name='current']");
        if (parseInt(cur.val()) <= 1) {
            return;
        }
        cur.val(parseInt(cur.val())-1);
        $("#navigatorKey").val($("#queryform input[name='key']").val());
        $("#navigatorform").submit();
    });
    $("#navigateGoto").click(function () {
        var pages = $("#navigatorform input[name='pages']");
        var cur = $("#navigatorform input[name='current']");
        if (parseInt(cur.val()) > parseInt(pages.val())) {
            cur.val(pages.val());
        }
        if (parseInt(cur.val()) < 1) {
            cur.val(1);
        }
        $("#navigatorKey").val($("#queryform input[name='key']").val());
        $("#navigatorform").submit();
    });
    $("#navigateToNextPage").click(function () {
        var pages = $("#navigatorform input[name='pages']");
        var cur = $("#navigatorform input[name='current']");
        if (parseInt(cur.val()) >= parseInt(pages.val())) {
            cur.val(pages.val());
            return;
        } else {
            cur.val(parseInt(cur.val())+1);
        }
        $("#navigatorKey").val($("#queryform input[name='key']").val());
        $("#navigatorform").submit();
    });
    $("#navigateToLastPage").click(function () {
        var pages = $("#navigatorform input[name='pages']");
        var cur = $("#navigatorform input[name='current']");
        if (parseInt(cur.val()) >= parseInt(pages.val())) {
            cur.val(pages.val());
            return;
        }
        $("#navigatorform input[name='current']").val(pages.val());
        $("#navigatorKey").val($("#queryform input[name='key']").val());
        $("#navigatorform").submit();
    });

    /* ------------Page Navigation End -------------*/

});
/*]]>*/
</script>

<div th:replace="~{fragments/footer::footer}"></div>
</body>
</html>