package docat

import docat.anotations.SolrExtractable

class Document {
	static solrable = true
	String name
	DocCategory category
    @SolrExtractable()
	String attachedFileName

	static constraints = {
        attachedFileName nullable: true
	}
}
