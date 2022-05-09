package dao;

import model.Admin;
import model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface AdminDAO extends DAO<Admin> {
    @Override
    @Select("select * from admin where id=#{id}")
    Optional<Admin> get(int id);

    @Select("select * from admin where name=#{name} and password=#{password}")
    Optional<Admin> check(@Param("name") String name, @Param("password") String password);

    @Override
    @Select("select * from admin")
    List<Admin> getAll();

    @Override
    @Insert("insert into admin values (#{id}, #{name}, #{password})")
    int add(Admin user);

    @Override
    @Update("update admin set name=#{name}, password=#{password} where id=#{id}")
    int update(Admin user);

    @Override
    @Delete("delete from admin where id=#{id}")
    int delete(int id);
}
