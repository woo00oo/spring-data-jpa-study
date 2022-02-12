package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }

}

/**
 * 1. command 와 Query의 분리.
 * command : 결과를 반환하지 않고, 대신 시스템의 상태를 변화
 * Query : 결과를 반환하고, 시스템의 상태 변화 X
 *
 * 2. 핵심 비즈니스 로직과 복잡한 화면에 관한 로직 분리
 */