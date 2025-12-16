package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discoutPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discoutPrice);
    }

//    // setter 생성자 주입 - "선택", "변경" 가능성이 있는 의존관계에 사용
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    // setter 생성자 주입 - "선택", "변경" 가능성이 있는 의존관계에 사용
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

//     생성자 주입할때 "불변"과 "필수" 의존관계 사용


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    ////    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();



//    // 싱글톤 테스트 용도
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }
}
