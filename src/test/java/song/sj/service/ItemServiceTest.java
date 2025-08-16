package song.sj.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.item.Bottom;
import song.sj.entity.item.Item;
import song.sj.entity.item.Shoes;
import song.sj.entity.item.Top;

import static org.assertj.core.api.Assertions.assertThat;
import static song.sj.enums.ItemValue.*;

@SpringBootTest
@Transactional
@Slf4j
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    ItemSaveDto itemSaveDto;

    @BeforeEach
    void init() {

        itemSaveDto = ItemSaveDto.builder()
                .itemName("jeans")
                .design("design")
                .size(110)
                .description("description")
                .material("material")
                .build();

        Bottom bottom = new Bottom("jeans");
        bottom.changeItemName(itemSaveDto.getItemName());
        bottom.changeDesign(itemSaveDto.getDesign());
        bottom.changeDescription(itemSaveDto.getDescription());
        bottom.changeSize(itemSaveDto.getSize());
        bottom.changeMaterial(itemSaveDto.getMaterial());

        itemRepository.save(bottom);
    }

    @Test
    void save() {

        Top top;
        Bottom bottom;
        Shoes shoes;

        ItemSaveDto dto = ItemSaveDto.builder().itemName("jeans")
                .design("design")
                .size(110)
                .description("description")
                .material("material")
                .value(TOP)
                .build();


        Item save = itemRepository.save(item(dto));

        assertThat(save.getItemName()).isEqualTo("jeans");
        assertThat(save.getClass()).isEqualTo(Top.class);
    }

    private static Item item(ItemSaveDto dto) {
        Top top;
        Shoes shoes;
        Bottom bottom;
        if (dto.getValue().equals(TOP)) {
            top = new Top("productClassification");
            top.changeItemName(dto.getItemName());
            top.changeMaterial(dto.getMaterial());
            top.changeSize(dto.getSize());
            top.changeDesign(dto.getDesign());
            top.changeDescription(dto.getDescription());
            return top;
        }

        if (dto.getValue().equals(BOTTOM)) {
            bottom = new Bottom("productClassification");
            bottom.changeItemName(dto.getItemName());
            bottom.changeMaterial(dto.getMaterial());
            bottom.changeSize(dto.getSize());
            bottom.changeDesign(dto.getDesign());
            bottom.changeDescription(dto.getDescription());
            return bottom;
        }

        if (dto.getValue().equals(SHOES)) {
            shoes = new Shoes("productClassification");
            shoes.changeItemName(dto.getItemName());
            shoes.changeMaterial(dto.getMaterial());
            shoes.changeSize(dto.getSize());
            shoes.changeDesign(dto.getDesign());
            shoes.changeDescription(dto.getDescription());
            return shoes;
        }
        return null;
    }

    @Test
    void findItem() {

    }

    @Test
    void changeSize() {
        Item item = itemRepository.findById(1L).get();
        item.changeSize(100);

        assertThat(item.getSize()).isEqualTo(100);
    }

}
