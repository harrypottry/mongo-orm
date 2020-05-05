package javax.core.common.mongo;

/**
 * @author harrypotter
 */
public class EntityOperation<T> {
    public Class<T> entityClass ;

    public EntityOperation(Class<T> entityClass){
        this.entityClass = entityClass;
    }
}
