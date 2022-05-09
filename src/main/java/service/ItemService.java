package service;

import dao.ItemDAO;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements service.Service<Item> {
    @Autowired
    ItemDAO itemDAO;

    @Override
    public Optional<Item> get(int id) {
        return itemDAO.get(id);
    }

    public List<Item> getBySeller(int seller) {
        return itemDAO.getBySeller(seller);
    }

    @Override
    public List<Item> getAll() {
        return itemDAO.getAll();
    }

    @Override
    public boolean add(Item item) {
        return itemDAO.add(item) > 0;
    }

    @Override
    public boolean update(Item item) {
        return itemDAO.update(item) > 0;
    }

    @Override
    public boolean delete(int id) {
        return itemDAO.delete(id) > 0;
    }
}
