<%@ page import="docat.DocCategory"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Создать новую категорию</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
					<li><g:link class="list" action="list">Список категорий</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">
			<h1>Создать новую категорию</h1>
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
			<g:form action="save">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="btn btn-primary"
						value="Сохранить" />
				</fieldset>
			</g:form>

		</div>
	</div>

</body>
</html>
