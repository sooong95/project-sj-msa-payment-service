package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bill extends TimeStamp {

    public Bill(int totalCost) {
        this.totalCost = totalCost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    private int totalCost;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "bill")
    private List<ItemBill> itemBillList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    public void injectMember(Member member) {
        this.member = member;
        member.getBillList().add(this);
    }

    public void injectShop(Shop shop) {
        this.shop = shop;
        shop.getBillList().add(this);
    }

    public void injectOrder(Order order) {
        this.order = order;
        order.getBillList().add(this);
    }
}
