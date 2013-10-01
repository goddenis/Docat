package docat

import org.hibernate.event.PostDeleteEvent
import org.hibernate.event.PostDeleteEventListener
import org.hibernate.event.PostInsertEvent
import org.hibernate.event.PostInsertEventListener
import org.hibernate.event.PostUpdateEvent
import org.hibernate.event.PostUpdateEventListener

class SolrIndexListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener {
    def processingService

    public void onPostInsert(final PostInsertEvent event) {
        def entity = event.getEntity()
        processingService?.indexEntity(entity)
    }

    public void onPostUpdate(final PostUpdateEvent event) {
        def entity = event.getEntity()
        processingService?.reindexEntity(entity)
    }

    public void onPostDelete(final PostDeleteEvent event) {
        def entity = event.getEntity()
        processingService?.deleteEntity(entity)
    }

    private boolean ifEnabled(entity) {
        true
    }

}
