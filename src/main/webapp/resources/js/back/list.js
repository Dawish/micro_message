/**
 * 调用后台批量删除方法
 * @basePath 工程路径
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action", basePath + "deleteBatch.do");
	$("#mainForm").submit();
}

