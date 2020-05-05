package javax.core.common.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.core.common.utils.GenericsUtils;
import java.io.Serializable;
import java.util.List;

/**
 * @author harrypotter
 */
public abstract class BaseDaoSupport<T extends Serializable,PK extends Serializable> {


    private MongoTemplate mongoTemplate;
    private EntityOperation<T> op;

    public BaseDaoSupport(){
        Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass(),0);
        op = new EntityOperation<T>(entityClass);
    }

    protected void setTempate(MongoTemplate tempate){
        this.mongoTemplate = tempate;
    }

    protected abstract String getPKColumn();


    //可控
    protected List<T> find(QueryRule queryRule){
       // "starttime " + "" + System.currentTimeMillis();
        QueryRuleBulider bulider = new QueryRuleBulider(queryRule);
        Query query = bulider.getQuery();

        return mongoTemplate.find(query,op.entityClass);
    }


    protected int saveAll(List<T> list){
        mongoTemplate.insertAll(list);
        return list.size();
    }


    protected  T get(PK id){
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.andEqual(this.getPKColumn(),id);
        QueryRuleBulider bulider = new QueryRuleBulider(queryRule);
        Query query = bulider.getQuery();

        return mongoTemplate.findOne(query,op.entityClass);
    }

    protected int delete(T entity){
        return mongoTemplate.remove(entity).getN();
    }

}
