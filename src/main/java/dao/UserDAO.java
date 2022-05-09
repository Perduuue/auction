package dao;

import model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends DAO<User> {
    @Override
    @Select("select * from user where id=#{id}")
    Optional<User> get(int id);

    @Select("select * from user where name=#{name} and password=#{password}")
    Optional<User> check(@Param("name") String name, @Param("password") String password);

    @Override
    @Select("select * from user")
    List<User> getAll();

    @Override
    @Insert("insert into user values (#{id}, #{name}, #{password})")
    int add(User user);

    @Override
    @Update("update user set name=#{name}, password=#{password} where id=#{id}")
    int update(User user);

    @Override
    @Delete("delete from user where id=#{id}")
    int delete(int id);
}
