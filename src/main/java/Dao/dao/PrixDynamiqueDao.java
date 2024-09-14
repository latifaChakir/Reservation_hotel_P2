package Dao.dao;
import bean.PrixDynamique;

import java.util.List;

public abstract class PrixDynamiqueDao {
    public abstract List<PrixDynamique> findAll() ;
    public abstract PrixDynamique findById(int pricedynamicId);
    public abstract PrixDynamique save(PrixDynamique pricedynamic);
    public abstract void update(PrixDynamique pricedynamic);
    public abstract void delete(int pricedynamicId) ;
}
