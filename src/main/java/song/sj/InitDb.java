package song.sj;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.item.ItemSaveDto;
import song.sj.dto.member.MemberJoinDto;
import song.sj.dto.member.ShopMemberJoinDto;
import song.sj.entity.item.Item;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitMember initMember;

    @PostConstruct
    public void init() {
        initMember.initDb();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitMember {

        private final EntityManager em;

        public void initDb() {

            /*MemberJoinDto memberDto1 = new MemberJoinDto("song1", "song1@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_MEMBER);
            MemberJoinDto memberDto2 = new MemberJoinDto("song2", "song2@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_MEMBER);
            MemberJoinDto memberDto3 = new MemberJoinDto("song3", "song3@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_MEMBER);
            MemberJoinDto memberDto4 = new MemberJoinDto("song4", "song4@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_MEMBER);
            MemberJoinDto memberDto5 = new MemberJoinDto("song5", "song5@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_MEMBER);

            Member member1 = memberDto1.toEntity();
            Member member2 = memberDto2.toEntity();
            Member member3 = memberDto3.toEntity();
            Member member4 = memberDto4.toEntity();
            Member member5 = memberDto5.toEntity();

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);

            ShopMemberJoinDto shopMemberJoinDto1 = new ShopMemberJoinDto("shop1", "1234324", "shop1@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_SHOP);
            ShopMemberJoinDto shopMemberJoinDto2 = new ShopMemberJoinDto("shop2", "9808345", "shop2@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_SHOP);
            ShopMemberJoinDto shopMemberJoinDto3 = new ShopMemberJoinDto("shop3", "878732894", "shop3@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_SHOP);
            ShopMemberJoinDto shopMemberJoinDto4 = new ShopMemberJoinDto("shop4", "18798234", "shop4@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_SHOP);
            ShopMemberJoinDto shopMemberJoinDto5 = new ShopMemberJoinDto("shop5", "32958445", "shop5@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.ROLE_SHOP);

            Member shopMember1 = shopMemberJoinDto1.toEntity();
            Member shopMember2 = shopMemberJoinDto2.toEntity();
            Member shopMember3 = shopMemberJoinDto3.toEntity();
            Member shopMember4 = shopMemberJoinDto4.toEntity();
            Member shopMember5 = shopMemberJoinDto5.toEntity();

            em.persist(shopMember1);
            em.persist(shopMember2);
            em.persist(shopMember3);
            em.persist(shopMember4);
            em.persist(shopMember5);

            List<ItemValue> mainEvent = new ArrayList<>();
            mainEvent.add(ItemValue.TOP);
            mainEvent.add(ItemValue.BOTTOM);
            Shop shop1 = new Shop("shopA", "이곳은 수선집1", mainEvent, new Address("city", "sdf", "sdf"));
            Shop shop2 = new Shop("shopB", "이곳은 수선집2", mainEvent, new Address("city", "sdf", "sdf"));
            Shop shop3 = new Shop("shopC", "이곳은 수선집3", mainEvent, new Address("city", "sdf", "sdf"));
            Shop shop4 = new Shop("shopD", "이곳은 수선집4", mainEvent, new Address("city", "sdf", "sdf"));
            Shop shop5 = new Shop("shopE", "이곳은 수선집5", mainEvent, new Address("city", "sdf", "sdf"));

            shop1.averageGrade(4);
            shop2.averageGrade(4.7);
            shop3.averageGrade(3.9);
            shop4.averageGrade(2.4);
            shop5.averageGrade(3.3);

            em.persist(shop1);
            em.persist(shop2);
            em.persist(shop3);
            em.persist(shop4);
            em.persist(shop5);

            em.flush();
            em.clear();

            MultipartFile file = new MultipartFile() {
                @Override
                public String getName() {
                    return "";
                }

                @Override
                public String getOriginalFilename() {
                    return "";
                }

                @Override
                public String getContentType() {
                    return "";
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public long getSize() {
                    return 0;
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return new byte[0];
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return null;
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {

                }
            };*/

            /*em.persist(new ShopCategory("TOP"));
            em.persist(new ShopCategory("BOTTOM"));
            em.persist(new ShopCategory("SHOES"));
            em.persist(new ShopCategory("OUTER"));
            em.persist(new ShopCategory("BAG"));
            em.persist(new ShopCategory("ACCESSORY"));
            em.persist(new ShopCategory("GRADE"));
            em.persist(new ShopCategory("REVIEWS"));*/

            /*ItemCategory top = new ItemCategory("TOP");
            ItemCategory bottom = new ItemCategory("BOTTOM");
            ItemCategory shoes = new ItemCategory("SHOES");
            ItemCategory outer = new ItemCategory("OUTER");
            ItemCategory bag = new ItemCategory("BAG");
            ItemCategory accessory = new ItemCategory("ACCESSORY");
            ItemCategory etc = new ItemCategory("ETC");

            em.persist(top);
            em.persist(bottom);
            em.persist(shoes);
            em.persist(outer);
            em.persist(bag);
            em.persist(accessory);
            em.persist(etc);*/

            /*ItemSaveDto item1 = new ItemSaveDto("itemA", "A", 110, 1, "designA", "descriptionA", "A", ItemValue.TOP);
            ItemSaveDto item2 = new ItemSaveDto("itemB", "B", 100, 1, "designB", "descriptionB", "B", ItemValue.TOP);
            ItemSaveDto item3 = new ItemSaveDto("itemC", "C", 110, 3, "designC", "descriptionC", "C", ItemValue.BOTTOM);
            ItemSaveDto item4 = new ItemSaveDto("itemD", "D", 100, 10, "designD", "descriptionD", "D", ItemValue.TOP);
            ItemSaveDto item5 = new ItemSaveDto("itemE", "E", 110, 1, "designE", "descriptionE", "E", ItemValue.BOTTOM);

            Item itemEntity1 = ToItem.toItemEntity(item1);
            Item itemEntity2 = ToItem.toItemEntity(item2);
            Item itemEntity3 = ToItem.toItemEntity(item3);
            Item itemEntity4 = ToItem.toItemEntity(item4);
            Item itemEntity5 = ToItem.toItemEntity(item5);

            itemEntity1.addItemCategory(top);
            itemEntity2.addItemCategory(top);
            itemEntity3.addItemCategory(bottom);
            itemEntity4.addItemCategory(top);
            itemEntity5.addItemCategory(bottom);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, 1);

            itemEntity1.setMember(findMember);
            itemEntity2.setMember(findMember);
            itemEntity3.setMember(findMember);
            itemEntity4.setMember(findMember);
            itemEntity5.setMember(findMember);

            em.persist(itemEntity1);
            em.persist(itemEntity2);
            em.persist(itemEntity3);
            em.persist(itemEntity4);
            em.persist(itemEntity5);*/

        }
    }
}
