package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Bill;
import song.sj.repository.query.BillQueryRepository;

public interface BillRepository extends JpaRepository<Bill, Long>, BillQueryRepository {
}
