<%@ page import="docat.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Создание документа</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
					<li><g:link class="list" action="list">
						Список документов
						</g:link></li>
				</ul>
			</div>
		</div>
		<div class="span9">
			<h1>Создание документа</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${documentInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${documentInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form action="save">
				<fieldset class="form">
					<g:render template="form" />
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="btn btn-primary"
						value="Создать" />
				</fieldset>
			</g:form>
		</div>
	</div>
</body>
</html>
