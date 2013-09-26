package docat

import grails.plugins.springsecurity.Secured;
import groovyx.net.http.HTTPBuilder
import org.springframework.dao.DataIntegrityViolationException

class DocumentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def processingService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [documentInstanceList: Document.list(params), documentInstanceTotal: Document.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [documentInstance: new Document(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def documentInstance = new Document()
        bindData(documentInstance, params, ['file'])
        def uploadedFile = request.getFile('file')

        if (!uploadedFile.empty) {
            def newFilename = processingService.process(uploadedFile)
            documentInstance.attachedFileName = newFilename
        }

        if (!documentInstance.save(flush: true)) {
            render(view: "create", model: [documentInstance: documentInstance])
            return
        }



        flash.message = "Документ ${documentInstance.id}."
        redirect(action: "show", id: documentInstance.id)
    }

    def show(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message = "Документ ${id} не найден"
            redirect(action: "list")
            return
        }

        [documentInstance: documentInstance]
    }

    def download(Long id) {
        def docInstance = Document.get(id)

        if (docInstance?.attachedFileName) {
            def fileBytes = processingService.readFile(docInstance.attachedFileName)


            if (fileBytes?.length > 0) {
                forceDownload(filename:"${docInstance.name.replace(' ','_')}.pdf", contentType:"application/octet-stream", contentLength: fileBytes.length, fileBytes)
                //render(contentType: "application/octet-stream",header("Content-disposition", "filename=${docInstance.attachedFileName}"), outputStrem:fileBytes )

            }
        } else {
            render(action: "show", id: docInstance.id)
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message ="Документ ${id} не найден"
            redirect(action: "list")
            return
        }

        [documentInstance: documentInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def update(Long id, Long version) {
        def documentInstance = Document.get(id)

        if (!documentInstance) {
            flash.message = "Документ ${id} не найден"
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (documentInstance.version > version) {
                documentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        "Another user has updated this Document while you were editing")
                render(view: "edit", model: [documentInstance: documentInstance])
                return
            }
        }

        documentInstance.properties = params

        def uploadedFile = request.getFile('file')

        if (!uploadedFile.empty) {
            def newFilename = processingService.process(uploadedFile)
            documentInstance.attachedFileName = newFilename
        }

        if (!documentInstance.save(flush: true)) {
            render(view: "edit", model: [documentInstance: documentInstance])
            return
        }

        flash.message ="Документ ${documentInstance.id} Обновлен"
        redirect(action: "show", id: documentInstance.id)
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message ="Документ ${id} не найден"
            redirect(action: "list")
            return
        }

        try {
            documentInstance.delete(flush: true)
            flash.message ="Документ ${id} удален"
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Ошибка документ ${id} не удален. \n${e.message}"
            redirect(action: "show", id: id)
        }
    }
}
