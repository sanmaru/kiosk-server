package net.monki.data.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public List<MemberEntity> getMembersAll() {
        return memberRepository.findAll();  // JpaRepository에서 제공하는 findAll() 함수
    }
}
