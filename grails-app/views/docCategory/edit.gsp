<%@ page import="docat.DocCategory"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Редактирование</title>
</head>
<body>

	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"> Home</a></li>
					<li><g:link class="list" action="list">Список категорий</g:link></li>
					<li><g:link class="create" action="create">Создать новую категорию</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">
			<h1>Редактирование</h1>


			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${docCategoryInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${docCategoryInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form method="post">
				<g:hiddenField name="id" value="${docCategoryInstance?.id}" />
				<g:hiddenField name="version"
					value="${docCategoryInstance?.version}" />
				<fieldset class="form">
					<g:render template="form" />
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-primary" action="update"
						value="Сохранить" />
					<g:actionSubmit class="btn" action="delete"
						value="Удалить"
						formnovalidate=""
						onclick="return confirm('Вы уверены?');" />
				</fieldset>
			</g:form>
		</div>
</body>
</html>
