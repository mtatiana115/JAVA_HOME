package database;

import java.util.List;

public interface CRUD {
    public Object insert(Object object);
    public boolean update(Object object);
    public boolean delete(Object obj);
    public List<Object> findAll();
    public Object findById (int id);
}
