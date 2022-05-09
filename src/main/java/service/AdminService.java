package service;

import dao.AdminDAO;
import model.Admin;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements service.Service<Admin> {
    @Autowired
    AdminDAO adminDAO;

    @Override
    public Optional<Admin> get(int id) {
        return adminDAO.get(id);
    }

    public Optional<Admin> check(String name, String password) {
        return adminDAO.check(name, password);
    }

    @Override
    public List<Admin> getAll() {
        return adminDAO.getAll();
    }

    @Override
    public boolean add(Admin user) {
        return adminDAO.add(user) > 0;
    }

    @Override
    public boolean update(Admin user) {
        return adminDAO.update(user) > 0;
    }

    @Override
    public boolean delete(int id) {
        return adminDAO.delete(id) > 0;
    }
}
