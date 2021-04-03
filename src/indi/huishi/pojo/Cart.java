package indi.huishi.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//购物车
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    /*
    key商品编号唯一，下面就不用再查找id了
    * */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();


    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // 当添加的商品已经存在的时候，需要合并已有的商品项，在数量上累加,不能直接items.add(cartItem);
        // 查看是否添加过此商品 如果已添加 数量增加 总金额更新
        // 如果没添加过 添加到集合
        // 通过id查找看是否已经存在此商品
        CartItem item = items.get(cartItem.getId());
        if (item==null){// 直接添加
            items.put(cartItem.getId(), cartItem);
        }else{
            // 已添加过
            item.setCount(item.getCount()+1);// 数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//总金额更新 单价乘以数量
        }
    }

    /**
     * 清空商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品项的数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count){
        //先查看是否有此商品 如果有修改数量 总金额
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(count);//修改商品数量
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));//更新总金额
        }
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        // 遍历items 将所有商品项的数目相加
        for(Map.Entry<Integer,CartItem>entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem>entry: items.entrySet()){
//            System.out.println(entry.getValue().getTotalPrice());
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
