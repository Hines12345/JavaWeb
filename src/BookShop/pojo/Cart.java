package BookShop.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    //Map数组中存放整个购物车的商品
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

        /*
    添加到购物车
     */
    public void addItems(CartItem cartItem)
    {
        CartItem cartItem1 = items.get(cartItem.getId());
        if(cartItem1 == null)
        {
            items.put(cartItem.getId(),cartItem);
        }
        //这里就是说已经添加过carItem，我们无需再添加一次，
        // 我们只需要取出Map中已添加的CartItem的数量+1 和重新计算它的总金额即可
        else{
            cartItem1.setCount(cartItem1.getCount() + 1);
            cartItem1.setTotalPrice(cartItem1.getTotalPrice().multiply(new BigDecimal(cartItem1.getCount())));

        }
    }

    /*
    清空购物车操作
     */
    public void clearCart()
    {
        items.clear();
    }

    /*
    删除商品
     */
    public void deleteItem(Integer id)
    {
        items.remove(id);
    }

    /*
    修改商品数量
     */
    public void updateCount(Integer id,Integer count)
    {
        //获取你要删除哪一行
        CartItem item = items.get(id);
        if(item != null)
        {
            //修改商品数量
            item.setCount(count);
            //修改总金额
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));

        }
    }

    //这里获取每件商品的个数（通过遍历整个商品栏Map，获取每一件商品的个数并相加）
    public Integer getTotalItem() {
        Integer totalItem = 0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet())
        {
            totalItem += entry.getValue().getCount();
        }

        return totalItem;
    }


    public BigDecimal getTotalPrice() {
       BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet())
        {
            //当前的总金额+总商品金额
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalItem=" + getTotalItem() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
