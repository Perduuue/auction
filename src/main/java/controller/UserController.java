package controller;

import model.Bid;
import model.Item;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BidService;
import service.ItemService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private BidService bidService;

    @RequestMapping(value = "/auction", method = RequestMethod.POST)
    public Boolean addAuction(@RequestParam String description, @RequestParam String type, @RequestParam double price, @RequestParam String time, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            var item = new Item();
            item.setSeller(user.getId());
            item.setDescription(description);
            item.setType(type);
            item.setMinPrice(price);
            item.setLastBidPrice(0);
            item.setClosingTime(Date.valueOf(time));
            return itemService.add(item);
        }
        return false;
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public Boolean addBid(@RequestParam int auction, @RequestParam double price, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            var oItem = itemService.get(auction);
            if (oItem.isEmpty()) {
                return false;
            }
            var item = oItem.get();
            if (item.getLastBidPrice() > price) {
                return false;
            }
            var now = new Date(System.currentTimeMillis());
            item.setLastBidPrice(price);
            item.setLastBidTime(now);
            item.setBuyer(user.getId());
            var bid = new Bid();
            bid.setAuction(auction);
            bid.setBuyer(user.getId());
            bid.setPrice(price);
            bid.setTime(now);
            return itemService.update(item) && bidService.add(bid);
        }
        return false;
    }

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public List<Item> getAllAuctions(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return itemService.getAll();
        }
        return null;
    }

    @RequestMapping(value = "/auctionsByType", method = RequestMethod.GET)
    public List<Item> getAllAuctionsByType(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Item> items = itemService.getAll();
            items.sort(Comparator.comparing(Item::getType));
            return items;
        }
        return null;
    }

    @RequestMapping(value = "/auctionsByPrice", method = RequestMethod.GET)
    public List<Item> getAllAuctionsByPrice(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Item> items = itemService.getAll();
            items.sort(Comparator.comparing(Item::getLastBidPrice));
            return items;
        }
        return null;
    }

    @RequestMapping(value = "/auction/my", method = RequestMethod.GET)
    public List<Item> getMyAuctions(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return itemService.getBySeller(user.getId());
        }
        return null;
    }

    @RequestMapping(value = "/auction/myBuy", method = RequestMethod.GET)
    public List<Item> getBuyAuctions(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Item> items = new ArrayList<>();
            Date now = new Date(System.currentTimeMillis());
            for (Item item : itemService.getAll()) {
                if (item.getBuyer() == user.getId() &&now.after(item.getClosingTime()) && item.getMinPrice() < item.getLastBidPrice()) {
                    items.add(item);
                }
            }
            return items;
        }
        return null;
    }

    @RequestMapping(value = "/bid/my", method = RequestMethod.GET)
    public List<Bid> getBids(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return bidService.getByBuyer(user.getId());
        }
        return null;
    }

    @RequestMapping(value = "/bid/{id}", method = RequestMethod.GET)
    public List<Bid> getBidsByAuction(@PathVariable int id) {
        return bidService.getByAuction(id);
    }

    @RequestMapping(value = "/auction/{id}", method = RequestMethod.DELETE)
    public Boolean deleteAuction(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return itemService.delete(id);
        }
        return false;
    }

    @RequestMapping(value = "/bid/{id}", method = RequestMethod.DELETE)
    public Boolean deleteBid(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return bidService.delete(id);
        }
        return false;
    }
}
