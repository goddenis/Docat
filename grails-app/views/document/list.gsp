
<%@ page import="docat.Document"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<title>ДокАТ : Список документов</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3">
			<div class="well">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
					<li><g:link class="create" action="create">Создать документ</g:link></li>
				</ul>
			</div>
		</div>
		<div class="span9">
			<h1>Список документов</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<table class="table table-striped">
				<thead>
					<tr>

						<g:sortableColumn property="category" title="Категория" />
						<g:sortableColumn property="name" title="Имя документа" />
					</tr>
				</thead>
				<tbody>
					<g:each in="${documentInstanceList}" status="i" var="documentInstance">
						<tr>
							<td><g:link action="show" id="${documentInstance.id}">
									${fieldValue(bean: documentInstance, field: "category")}
								</g:link></td>

							<td>
								${fieldValue(bean: documentInstance, field: "name")}
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentInstanceTotal}" />
			</div>
		</div>
	</div>
</body>
</html>
