package docat

class DocCategoryController {

	static scaffold = true
	
    def index() { 
		redirect(action: "list" )
	}
}
