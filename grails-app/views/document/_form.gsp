<%@ page import="docat.Document" %>



<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'category', 'error')} required">
	<label for="category">
		Категория
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${docat.DocCategory.list()}" optionKey="id" required="" value="${documentInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentInstance, field: 'name', 'error')} ">
	<label for="name">Имя</label>
	<g:textField name="name" value="${documentInstance?.name}"/>
</div>
