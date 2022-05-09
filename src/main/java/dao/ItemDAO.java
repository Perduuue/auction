package dao;

import model.Item;
import model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends DAO<Item> {
    @Override
    @Select("select * from item where id=#{id}")
    Optional<Item> get(int id);

    @Select("select * from item where seller=#{seller}")
    List<Item> getBySeller(int seller);

    @Override
    @Select("select * from item")
    List<Item> getAll();

    @Override
    @Insert("insert into item values (#{id}, #{description}, #{type}, #{seller}, #{buyer}, #{lastBidPrice}, #{minPrice}, #{closingTime}, #{lastBidTime})")
    int add(Item item);

    @Override
    @Update("update item set description=#{description}, type=#{type}, seller=#{seller}, buyer=#{buyer}, lastBidPrice=#{lastBidPrice}, minPrice=#{minPrice}, closingTime=#{closingTime}, lastBidTime=#{lastBidTime} where id=#{id}")
    int update(Item item);

    @Override
    @Delete("delete from item where id=#{id}")
    int delete(int id);
}
