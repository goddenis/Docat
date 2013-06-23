package docat

import org.springframework.dao.DataIntegrityViolationException

class DocCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [docCategoryInstanceList: DocCategory.list(params), docCategoryInstanceTotal: DocCategory.count()]
    }

    def create() {
        [docCategoryInstance: new DocCategory(params)]
    }

    def save() {
        def docCategoryInstance = new DocCategory(params)
        if (!docCategoryInstance.save(flush: true)) {
            render(view: "create", model: [docCategoryInstance: docCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), docCategoryInstance.id])
        redirect(action: "show", id: docCategoryInstance.id)
    }

    def show(Long id) {
        def docCategoryInstance = DocCategory.get(id)
        if (!docCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "list")
            return
        }

        [docCategoryInstance: docCategoryInstance]
    }

    def edit(Long id) {
        def docCategoryInstance = DocCategory.get(id)
        if (!docCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "list")
            return
        }

        [docCategoryInstance: docCategoryInstance]
    }

    def update(Long id, Long version) {
        def docCategoryInstance = DocCategory.get(id)
        if (!docCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (docCategoryInstance.version > version) {
                docCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'docCategory.label', default: 'DocCategory')] as Object[],
                          "Another user has updated this DocCategory while you were editing")
                render(view: "edit", model: [docCategoryInstance: docCategoryInstance])
                return
            }
        }

        docCategoryInstance.properties = params

        if (!docCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [docCategoryInstance: docCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), docCategoryInstance.id])
        redirect(action: "show", id: docCategoryInstance.id)
    }

    def delete(Long id) {
        def docCategoryInstance = DocCategory.get(id)
        if (!docCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            docCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'docCategory.label', default: 'DocCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
