/**
 * 调用后台批量删除方法
 * @basePath 工程路径
 */
function toList(basePath) {
	$("#mainForm").attr("action", basePath + "list.do");
	$("#mainForm").submit();
}

