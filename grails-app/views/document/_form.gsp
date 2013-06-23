<%@ page import="docat.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="document.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${docat.DocCategory.list()}" optionKey="id" required="" value="${documentInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="document.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${documentInstance?.name}"/>
</div>

