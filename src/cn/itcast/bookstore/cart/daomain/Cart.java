package cn.itcast.bookstore.cart.daomain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by winsion on 2017/3/31.
 */
public class Cart {

    private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

    //增加条目
    public void addCartItem(CartItem cartItem) {

        String bid = cartItem.getBook().getBid();
        if (map.containsKey(bid)) {//根据bid判断是否存在改条目
            //获取原来条目
            CartItem _cartItem = map.get(bid);
            System.out.println(_cartItem.getCount());
            System.out.println(cartItem.getCount());
            _cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
            map.put(bid, _cartItem);
        } else {
            map.put(bid, cartItem);
        }

    }


    //清空购物车
    public void clean() {

        map.clear();
    }

    //删除指定条目

    public void delegate(String bid) {

        map.remove(bid);
    }

    //计算 总价钱
    public double getTotal() {
        BigDecimal bigDecimaltotal = new BigDecimal(0);;
        for (CartItem cartItem : map.values()) {
            BigDecimal subtotal = new BigDecimal(cartItem.getCartItemTotal());
            bigDecimaltotal = bigDecimaltotal.add(subtotal);

        }
        return bigDecimaltotal.doubleValue();
    }




    //获取所有条目
    public Collection<CartItem> getAllCartItem() {
        return map.values();
    }

}
