package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;
import song.sj.entity.item.Item;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemBill extends TimeStamp {

    public ItemBill(int cost, int quantity, String repairDescription) {
        this.cost = cost;
        this.quantity = quantity;
        this.repairDescription = repairDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_bill_id")
    private Long id;

    private int cost;
    private int quantity;
    private String repairDescription;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @JoinColumn(name = "bill_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bill bill;

    public void injectItem(Item item) {
        this.item = item;
        item.getItemBillList().add(this);
    }

    public void injectBill(Bill bill) {
        this.bill = bill;
        bill.getItemBillList().add(this);
    }
}
