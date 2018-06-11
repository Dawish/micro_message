/**
 * 调用后台批量删除方法
 * @basePath 工程路径
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action", basePath + "deleteBatch.do");
	$("#mainForm").submit();
}

/**
 * 调转到添加页面
 * @param basePath
 * @returns
 */
function toAddPage(basePath) {
	$("#mainForm").attr("action", basePath + "addOne.do");
	$("#mainForm").submit();
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}
