package dao;

import model.Bid;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface BidDAO extends DAO<Bid> {
    @Override
    @Select("select * from bid where id=#{id}")
    Optional<Bid> get(int id);


    @Select("select * from bid where buyer=#{buyer}")
    List<Bid> getByBuyer(int buyer);

    @Select("select * from bid where auction=#{auction}")
    List<Bid> getByAuction(int auction);

    @Override
    @Select("select * from bid")
    List<Bid> getAll();

    @Override
    @Insert("insert into bid values (#{id}, #{auction}, #{buyer}, #{price}, #{time})")
    int add(Bid bid);

    @Override
    @Update("update bid set auction=#{auction}, buyer=#{buyer}, price=#{price}, time=#{time} where id=#{id}")
    int update(Bid bid);

    @Override
    @Delete("delete from bid where id=#{id}")
    int delete(int id);
}
