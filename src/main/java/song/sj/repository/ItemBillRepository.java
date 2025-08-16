package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.ItemBill;

public interface ItemBillRepository extends JpaRepository<ItemBill, Long> {
}
