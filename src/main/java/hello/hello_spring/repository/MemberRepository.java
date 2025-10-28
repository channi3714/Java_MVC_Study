package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장소에 저장
    Optional<Member> findById(Long id); //null을 처리하는 방법 중 하나
    Optional<Member> findByName(String name); //찾기
    List<Member> findAll(); //저장된 모든 리스트 반환
}
