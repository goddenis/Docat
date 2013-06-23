<%@ page import="docat.DocCategory" %>



<div class="fieldcontain ${hasErrors(bean: docCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="docCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${docCategoryInstance?.name}"/>
</div>

