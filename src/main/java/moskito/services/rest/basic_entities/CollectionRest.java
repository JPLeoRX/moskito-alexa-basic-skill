package moskito.services.rest.basic_entities;

import java.util.LinkedList;
import java.util.List;

public abstract class CollectionRest<E> extends ObjectRest {
    protected List<E> list;

    CollectionRest() {

    }

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
