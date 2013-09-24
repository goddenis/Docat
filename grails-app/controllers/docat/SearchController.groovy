package docat

import docat.SearchUtil

class SearchController {

    def index() {

    }

    def test (){
        def rec = grailsApplication.config.devPropereties
        render("Config {\n${rec}\n ${grailsApplication.config.devPropereties.foo} \n ${grailsApplication.config.devPropereties.bar}" )
    }
}
