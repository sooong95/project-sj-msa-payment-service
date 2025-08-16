package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.order.GetOrderBillDto;
import song.sj.dto.order.GetOrderItemBillDto;
import song.sj.dto.order.OrderBillDto;
import song.sj.entity.Bill;
import song.sj.entity.ItemBill;
import song.sj.repository.*;
import song.sj.service.toEntity.ToBill;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BillService {

    private final BillRepository billRepository;
    private final ItemBillRepository itemBillRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public void saveBill(OrderBillDto orderBillDto) throws AccessDeniedException {

        Shop shop = shopRepository.findById(orderBillDto.getShopId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 shop 입니다."));

        if (!memberService.getMemberFromJwt().getId().equals(shop.getMember().getId())) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        Bill billEntity = ToBill.toBillEntity(orderBillDto);

        List<ItemBill> itemBillList = orderBillDto.getItemBillDtoList().stream().map(list ->
                itemBillRepository.save(new ItemBill(list.getCost(), list.getQuantity(), list.getRepairDescription()))
        ).toList();

        billRepository.save(billEntity);

        for (int i = 0; i < itemBillList.size(); i++) {

            ItemBill itemBill = itemBillList.get(i);

            itemBill.injectBill(billEntity);

            itemBill.injectItem(itemRepository.findById(orderBillDto.getItemBillDtoList().get(i).getItemId())
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 아이템 입니다.")));
            itemBillRepository.save(itemBill);
        }


        billEntity.injectMember(memberRepository.findByEmail(orderBillDto.getClientEmail()));
        billEntity.injectShop(shop);
        billEntity.injectOrder(orderRepository.findById(orderBillDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 주문입니다.")));
    }

    public GetOrderBillDto getBill() {

        Long memberId = memberService.getMemberFromJwt().getId();

        Bill bill = billRepository.getBill(memberId);

        return new GetOrderBillDto(bill.getShop().getShopName(), bill.getShop().getMember().getEmail(),
                bill.getMember().getEmail(), bill.getCreatedDate(),
                bill.getItemBillList().stream().map(itemBill ->
                        new GetOrderItemBillDto(itemBill.getCost(), itemBill.getItem().getItemName(),
                                itemBill.getQuantity(), itemBill.getRepairDescription())).toList(),
                bill.getTotalCost());
    }
}
