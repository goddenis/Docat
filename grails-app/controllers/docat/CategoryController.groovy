package docat

class CategoryController {

	static scaffold = true
	
    def index() { 
		redirect(action: "list" )
	}
}
