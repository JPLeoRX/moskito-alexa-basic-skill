package moskito.services.rest.abstractions;

import java.util.LinkedList;
import java.util.List;

/**
 * Basic structure of REST objects that are read like a collection of objects
 *
 * @author Leo Ertuna
 */
public abstract class CollectionRest<E> extends ObjectRest {
    protected List<E> list;

    protected CollectionRest(String appUrl) {
        this.list = new LinkedList<>();
        this.appUrl = appUrl;
        this.requestUrl = appUrl + "/" + RESTPATH + "/" + this.getCaseUrl();
        this.read();
    }

    public List<E> getList() {
        return list;
    }
}