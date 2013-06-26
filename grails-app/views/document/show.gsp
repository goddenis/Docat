
<%@ page import="docat.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Подробности</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message
								code="default.home.label" /></a></li>
					<li><g:link class="list" action="list">
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li><g:link class="create" action="create">
							<g:message code="default.new.label" args="[entityName]" />
						</g:link></li>
				</ul>
			</div>
		</div>

		<div class="span9">
			<h1>Документ</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<ul class="property-list document">

				<g:if test="${documentInstance?.category}">
					<li><span>Категория :</span> <span><g:link
								controller="docCategory" action="show"
								id="${documentInstance?.category?.id}">
								${documentInstance?.category?.encodeAsHTML()}
							</g:link></span></li>
				</g:if>

				<g:if test="${documentInstance?.name}">
					<li><span>Имя :</span> <span> <g:fieldValue
								bean="${documentInstance}" field="name" /></span></li>
				</g:if>
			</ul>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${documentInstance?.id}" />
					<g:link class="btn btn-primary" action="
						edit" id="${documentInstance?.id}">
						Редактировать
						</g:link>
					<g:actionSubmit class="btn" action="delete"
						value="Удалить"
						onclick="return confirm('Вы уверены?');" />
				</fieldset>
			</g:form>
		</div>
</body>
</html>
