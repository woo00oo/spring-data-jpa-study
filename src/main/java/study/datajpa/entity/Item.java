package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }

    /**
     * PK에 @GeneratedValue 사용하지 못할 경우(레거시 DB)
     * JPA에서 새로운 엔티티로 판단하지 못한다. Id 값이 Null 아니기 때문
     * 결과, persist가 호출되는 것이 아닌 비효율적인 merge 메소드가 호출
     *
     * 이를 해결하기 위해 Persistable 인터페이스의 isNew 메소드 오버라이딩
     * JPA의 Auditing 기능을 활용하여 새로운 엔티티인지 판단 가능.
     */

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
