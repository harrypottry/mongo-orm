package javax.core.common.mongo;

/**
 * @author harrypotter
 */
public class EntiyOperation<T> {
    public Class<T> entiyClass = null;

    public EntiyOperation(Class<T> entiyClass){
        this.entiyClass = entiyClass;
    }
}
