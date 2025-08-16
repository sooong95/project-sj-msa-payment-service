package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import song.sj.dto.order.GetOrderBillDto;
import song.sj.dto.order.OrderBillDto;
import song.sj.service.BillService;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/shop/bill")
    public ResponseEntity<String> saveBill(@RequestBody OrderBillDto orderBillDto) throws AccessDeniedException {

        billService.saveBill(orderBillDto);
        return new ResponseEntity<>("성공적으로 청구서가 생성 되었습니다.", HttpStatus.CREATED);
    }

    @GetMapping("/bill")
    public ResponseEntity<GetOrderBillDto> getBill() {

        return new ResponseEntity<>(billService.getBill(), HttpStatus.OK);
    }
}
