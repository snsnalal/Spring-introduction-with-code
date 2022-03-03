package hello.hellospring.domain;


import javax.persistence.*;

@Entity //jpa가 관리하는 엔티티로 설정
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //값을 db가 알아서 생성해줌 = IDENTITY
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
