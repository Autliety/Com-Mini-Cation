package pers.auly.cmcwip.mapper;

import java.util.List;
import pers.auly.cmcwip.entity.AbstractEntity;

public interface AbstractMapper<E extends AbstractEntity> {
    
    // PLEASE add a space " " after any properties
    // including Mapper specific TABLE path!!!
    
    String table = "test ";
    
    String INSERT_INTO = "INSERT INTO ";
    String DELETE_FROM = "DELETE FROM ";
    String SELECT = "SELECT ";
    String UPDATE = "UPDATE ";
    
    String all = "* ";
    String FROM = "FROM ";
    String VALUES = "VALUES ";
    String WHERE = "WHERE ";
    String SET = "SET ";
    
    String WHERE_ID = "WHERE id=#{id} ";
    
    String LAST_ID = "SELECT last_insert_rowid() AS id ";
    
    // SELECT * FROM table
    List<E> selectAll();
    
    // INSERT INTO table VALUES(except_id)
//  @SelectKey(statement = LAST_ID, before = false, keyProperty = "id", resultType = Integer.class)
    void insertEntity(E entity);
    
}
