package net.monki.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public List<Member> getMembers() {
        return memberRepository.findAll();  // JpaRepository에서 제공하는 findAll() 함수
    }
}
