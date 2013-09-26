package docat

import docat.SearchUtil

class SearchController {
    def processingService

    def index() {
        if (!params["query"]) {
            redirect(controller: "document")
        }
        def searchResult = processingService.search(params["query"])

        def files = searchResult.collect{it["id"]}

        def docs =  Document.findAllByNameLike("%${params["query"]}%")
        def docFiles = docs.collect {it.attachedFileName}
        files.removeAll(docFiles)

        [docsList:docs, fileList: files, docsTotal:docs.size(), fileTotal: files.size()]
    }

    def test() {
        def rec = grailsApplication.config.devPropereties
        render("Config {\n${rec}\n ${grailsApplication.config.devPropereties.foo} \n ${grailsApplication.config.devPropereties.bar}")
    }
}
