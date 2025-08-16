package song.sj.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.member.MemberJoinDto;
import song.sj.entity.Address;
import song.sj.entity.Member;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    Validator validator;

    Member member;
    MemberJoinDto memberJoinDto;
    MemberJoinDto memberJoinDto2;

    String memberEmail = "sooong@nnnn.com";
    String password = "password";


    @BeforeEach
    void testValidEmail() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void save() {

        // given
        Set<ConstraintViolation<MemberJoinDto>> validate = validator.validate(memberJoinDto);

        // when
        memberService.memberSave(memberJoinDto);
        Member findMember = memberRepository.findByEmail(memberJoinDto.getEmail());

        // then
        Assertions.assertThat(memberJoinDto.getEmail()).isEqualTo(findMember.getEmail());

    }

    @Test
    void checkDuplicateEmail() {

        Member testMember = Member.builder()
                .username("song")
                .email(memberEmail)
                .password(password)
                .address(new Address("city", "street", "zipcode"))
                .role(Role.ROLE_MEMBER)
                .build();

        memberRepository.save(testMember);

        Member member = Member.builder()
                .username("song")
                .email(memberEmail)
                .password(password)
                .address(new Address("city", "street", "zipcode"))
                .role(Role.ROLE_MEMBER)
                .build();

        Assertions.assertThatThrownBy(() -> memberRepository.save(member)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 email 입니다.");
    }

    @Test
    void updateMember() {

        // given
        UpdateMemberDto updateMemberDto = new UpdateMemberDto();
        updateMemberDto.setUsername("update");
        updateMemberDto.setNewPassword("updatePassword");
        updateMemberDto.setAddress(new Address("updateCity", "updateStreet", "updateZipcode"));

        // when

        Member findMember = memberService.findMember(memberJoinDto2.getEmail());

        findMember.changeUsername(updateMemberDto.getUsername());
        findMember.changePassword(updateMemberDto.getNewPassword());
        findMember.changeAddress(updateMemberDto.getAddress());

        Member updateMember = memberService.findMember(memberJoinDto2.getEmail());
        // then
        assertEquals(updateMember.getUsername(), updateMemberDto.getUsername());
        assertEquals(updateMember.getPassword(), updateMemberDto.getNewPassword());
        assertEquals(updateMember.getAddress(), updateMemberDto.getAddress());
    }

    @Test
    void deleteMemberSuccess() {

        // given

        // when
        memberService.deleteMember(password);

        // then
        assertEquals(memberService.findMember(memberEmail), null);
    }

    @Test
    void deleteMemberWrongPassword() {

        // given
        String wrongPassword = "wrongPassword";

        // when

        // then
        Assertions.assertThatThrownBy(() -> memberService.deleteMember(wrongPassword))
                .isInstanceOf(RuntimeException.class).hasMessage("비밀번호가 일치하지 않습니다.");
    }
}

