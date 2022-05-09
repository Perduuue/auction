package service;

import dao.BidDAO;
import model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService implements service.Service<Bid> {
    @Autowired
    BidDAO bidDAO;

    @Override
    public Optional<Bid> get(int id) {
        return bidDAO.get(id);
    }

    public List<Bid> getByBuyer(int buyer) {
        return bidDAO.getByBuyer(buyer);
    }

    public List<Bid> getByAuction(int auction) {
        return bidDAO.getByAuction(auction);
    }

    @Override
    public List<Bid> getAll() {
        return bidDAO.getAll();
    }

    @Override
    public boolean add(Bid bid) {
        return bidDAO.add(bid) > 0;
    }

    @Override
    public boolean update(Bid bid) {
        return bidDAO.update(bid) > 0;
    }

    @Override
    public boolean delete(int id) {
        return bidDAO.delete(id) > 0;
    }
}
