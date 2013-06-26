<%@ page import="docat.DocCategory"%>



<div
	class="fieldcontain ${hasErrors(bean: docCategoryInstance, field: 'name', 'error')} ">
	<label for="name"> Имя </label>
	<g:textField name="name" value="${docCategoryInstance?.name}" />
</div>

