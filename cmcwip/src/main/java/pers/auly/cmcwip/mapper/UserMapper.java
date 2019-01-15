package pers.auly.cmcwip.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import pers.auly.cmcwip.entity.User;

@Mapper
@Component
public interface UserMapper extends AbstractMapper<User> {
    
    String table = "user ";
    
    @Override
    @Select(SELECT + all + FROM + table)
    List<User> selectAll();
    
    @Override
    @Insert(INSERT_INTO + table + "(name, freq)" + VALUES + "(#{name}, #{freq})")
    @SelectKey(statement = LAST_ID, before = false, keyProperty = "id", resultType = Integer.class)
    void insertEntity(User user);
    
    @Select(SELECT + all + FROM + table + WHERE + "(name=#{name})")
    User selectName(String name);
    
    @Update(UPDATE+table+SET+"freq=#{freq} "+WHERE_ID)
    void updateFreq(User user);
}
