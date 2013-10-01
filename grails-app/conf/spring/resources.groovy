import docat.SolrIndexListener
import org.codehaus.groovy.grails.orm.hibernate.HibernateEventListeners

// Place your Spring DSL code here
beans = {
    solrListener(SolrIndexListener) {
        processingService = ref("processingService")
    }

    hibernateEventListeners(HibernateEventListeners) {
        listenerMap = ['post-insert': solrListener,
                'post-update': solrListener,
                'post-delete': solrListener]
    }
}
